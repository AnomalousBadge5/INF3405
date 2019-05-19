import java.io.*;
import java.net.*;

public class TCPThreadManager extends Thread
{
	public final int TCP_PORT = 5033;
	public void run()
	{
		try
		{
			ServerSocket welcomeSocket = new ServerSocket(this.TCP_PORT);

			boolean done = false;
			while (!done)
			{
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				PrintWriter out = new PrintWriter(connectionSocket.getOutputStream(), true);
				String clientSentence = in.readLine();
				System.out.println("Received: " + clientSentence);
				if(clientSentence.contentEquals("back"))
				{
					done = true;
				}
				String capitalizedSentence = clientSentence.toUpperCase();
				out.println(capitalizedSentence);
			}
			welcomeSocket.close();
		} catch(Exception e)
		{
			System.out.println("TCP server has crashed");
		}
	}
}
