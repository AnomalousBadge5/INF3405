import java.net.*;

public class TCPThreadManager extends Thread
{
	public final int TCP_PORT = 5033;
	public void run()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(this.TCP_PORT);

			boolean done = false;
			while (!done)
			{
				TCPConnectionManager connection = new TCPConnectionManager(serverSocket);
				String clientSentence = connection.receive();
				System.out.println("Received: " + clientSentence);
				if(clientSentence.contentEquals("back"))
				{
					done = true;
				}
				String capitalizedSentence = clientSentence.toUpperCase();
				connection.send(capitalizedSentence);
			}
			serverSocket.close();
		} catch(Exception e)
		{
			System.out.println("TCP server has crashed");
		}
	}
}
