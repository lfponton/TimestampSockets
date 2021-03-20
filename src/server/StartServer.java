package server;

import server.model.DataModelManager;
import server.network.SocketServer;

public class StartServer
{
  public static void main(String[] args)
  {
    model.DataModel dataModel = new DataModelManager();
    SocketServer server = new SocketServer(dataModel);
    server.startServer();
  }
}
