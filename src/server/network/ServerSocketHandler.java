package server.network;

import shared.Request;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;
  private model.DataModel dataModel;
  private ObjectInputStream inFromClient;
  private ObjectOutputStream outToClient;

  public ServerSocketHandler(Socket socket, model.DataModel dataModel)
  {
    this.socket = socket;
    this.dataModel = dataModel;
    try
    {
      inFromClient = new ObjectInputStream(socket.getInputStream());
      outToClient = new ObjectOutputStream(socket.getOutputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    try
    {
        Request request = (Request) inFromClient.readObject();

        if (request.getType().equals("Listener"))
        {
          dataModel.addPropertyChangeListener("updated", this::onUpdated);
        }
        else if (request.getType().equals("LastUpdate"))
        {
          String result = dataModel.getLastUpdateTimeStamp();
          outToClient.writeObject(new Request("LastUpdate", result));
        }
        else if (request.getType().equals("NumberOfUpdates"))
        {
          int result = dataModel.getNumberOfUpdates();
          outToClient.writeObject(new Request("NumberOfUpdates", result));
        }
        else if (request.getType().equals("SetTimestamp"))
        {
          Date timestamp = new Date();
          outToClient.writeObject(new Request("SetTimestamp", timestamp));
        }

    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private void onUpdated(PropertyChangeEvent evt)
  {
    try
    {
      outToClient.writeObject(new Request(evt.getPropertyName(), evt.getNewValue()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
