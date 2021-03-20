package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  private model.DataModel dataModel;

  public SocketServer(model.DataModel dataModel)
  {
    this.dataModel = dataModel;
  }

  public void startServer() {
    try
    {
      ServerSocket serverSocket = new ServerSocket(1234);

      System.out.println("Server ready.");
      Socket socket = serverSocket.accept();
      System.out.println("Connected to server.");
      new Thread(new ServerSocketHandler(socket, dataModel)).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
