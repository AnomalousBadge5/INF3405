import java.io.*;
import java.net.*;

public class TCPManager {
	public final int TCP_PORT = 5033;
	public InetAddress serverAddress;

	public TCPManager(InetAddress serverAddress)
	{
		this.serverAddress = serverAddress;
	}
	
	public void manageAction(String action) throws Exception
	{
		  String modifiedSentence;
		  Socket clientSocket = new Socket(this.serverAddress.getHostAddress(), this.TCP_PORT);
		  PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  out.println(action);
		  modifiedSentence = in.readLine();
		  System.out.print("FROM SERVER: " + modifiedSentence);
		  clientSocket.close();

	}
}
