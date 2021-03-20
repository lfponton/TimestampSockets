package client.network;

import shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;
  private Socket socket;
  private Client client;

  public ClientSocketHandler(Socket socket, Client client)
  {
    this.socket = socket;
    this.client = client;
  }

  @Override public void run()
  {
    try
    {
      inFromServer = new ObjectInputStream(socket.getInputStream());
      outToServer = new ObjectOutputStream(socket.getOutputStream());

      while (true)
      {
        outToServer.writeObject(new Request("Listener", null));
        client.sendRequest(receiveRequest());
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public Request receiveRequest()
  {
    Request request = null;
    try
    {
       request = (Request) inFromServer.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return request;
  }
}
