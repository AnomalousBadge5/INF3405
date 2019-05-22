import java.net.*;

public class TCPThreadManager extends Thread
{
	public static final int PORT = 5033;
	public void run()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(TCPThreadManager.PORT);
			ListFolder listFolderTCP = new ListFolder();
			boolean done = false;
			while (!done)
			{
				TCPConnectionManager connection = new TCPConnectionManager(serverSocket);
				String action = connection.receive();
				DemandPrinter.printDemand(new Client(connection.socket.getInetAddress(), connection.socket.getPort()), action);
				serverSocket.getLocalPort();
				if(action.contentEquals("back"))
				{
					done = true;
				}
				else if ((action.contentEquals("ls")))
				{			
					String listFolder = listFolderTCP.getListFolder("tcp");
					String[] list = listFolder.split("\n");
					connection.send(Integer.toString(list.length));
					connection.send(listFolder);
				}
				else if (action.contains("download") && action.split(" ").length == 2)
				{
					String[] list = action.split(" ");
					String fileName = list[1];
					byte[] data = listFolderTCP.getBytesFromFile(fileName, "tcp");
					connection.dOut.writeInt(data.length);
					connection.dOut.write(data);
				}
				
			}
			serverSocket.close();
		} catch(Exception e)
		{
			System.out.println("TCP server has crashed");
		}
	}
}
