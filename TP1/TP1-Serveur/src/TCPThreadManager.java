import java.io.*;
import java.net.*;

public class TCPThreadManager extends Thread
{
	public void run()
	{
		try
		{
			ServerSocket welcomeSocket = new ServerSocket(6789);

			boolean done = false;
			while (!done)
			{
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				String clientSentence = inFromClient.readLine();
				System.out.println("Received: " + clientSentence);
				if(clientSentence.contentEquals("back"))
				{
					done = true;
				}
				String capitalizedSentence = clientSentence.toUpperCase() + 'n';
				outToClient.writeBytes(capitalizedSentence);
			}
			welcomeSocket.close();
		} catch(Exception e)
		{
			System.out.println("TCP server has crashed");
		}
	}
}
