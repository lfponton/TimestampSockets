package client.network;

import shared.Request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketClient implements Client
{
  private PropertyChangeSupport support;
  private String lastUpdate;
  private int numberOfUpdates;
  ClientSocketHandler handler;
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;

  public SocketClient()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public String getLastUpdateTimeStamp()
  {

    return lastUpdate;
  }

  @Override public int getNumberOfUpdates()
  {
    return numberOfUpdates;
  }

  @Override public void setTimeStamp(Date timeStamp)
  {
    SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
    String strDate = sdfDate.format(timeStamp);
    System.out.println(strDate);
    String last = lastUpdate;
    lastUpdate = strDate;
    numberOfUpdates++;
    support.firePropertyChange("updated", last, lastUpdate);
  }

  @Override public void startClient()
  {
    try
    {
      Socket socket = new Socket("localhost", 1234);

      handler = new ClientSocketHandler(socket, this);

      new Thread(handler).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void sendRequest(Request request)
  {
    support.firePropertyChange(request.getType(), null, request.getArgument());

  }

}

