import java.net.*;

public class TCPThreadManager extends Thread
{
	public final int TCP_PORT = 5033;
	public void run()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(this.TCP_PORT);
			ListFolder listFolderTCP = new ListFolder();
			boolean done = false;
			while (!done)
			{
				TCPConnectionManager connection = new TCPConnectionManager(serverSocket);
				String clientSentence = connection.receive();
				System.out.println("Received: " + clientSentence);
				serverSocket.getLocalPort();
				if(clientSentence.contentEquals("back"))
				{
					done = true;
				}
				else if ((clientSentence.contentEquals("ls")))
				{			
					String listFolder = listFolderTCP.getListFolderTCP();
					String[] list = listFolder.split("\n");
					connection.send(Integer.toString(list.length));
					connection.send(listFolder);
				}
				else if (clientSentence.contains("download"))
				{
					String[] list = clientSentence.split(" ");
					String fileName = list[1];
					byte[] dataStr = listFolderTCP.getBytesFromFileTCP(fileName);
					connection.out.println(dataStr);
				}
				
			}
			serverSocket.close();
		} catch(Exception e)
		{
			System.out.println("TCP server has crashed");
		}
	}
}
