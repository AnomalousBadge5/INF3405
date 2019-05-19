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
		  Socket clientSocket = new Socket("localhost", 6789);
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  outToServer.writeBytes(action);
		  modifiedSentence = inFromServer.readLine();
		  System.out.println("FROM SERVER: " + modifiedSentence);
		  clientSocket.close();

	}
}
