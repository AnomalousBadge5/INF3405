import java.io.*;
import java.net.*;

public class TCPManager implements ProtocolInterface {
	public final int TCP_PORT = 5033;
	public InetAddress serverAddress;

	public TCPManager(InetAddress serverAddress)
	{
		this.serverAddress = serverAddress;
	}
	
	public void manageAction(String action) throws Exception
	{
		  Socket clientSocket = new Socket(this.serverAddress.getHostAddress(), this.TCP_PORT);
		  PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  DataInputStream dIn = new DataInputStream(clientSocket.getInputStream());
		  out.println(action);
		  if(action.contentEquals("ls"))
		  {
			  int lines = Integer.parseInt(in.readLine());
			  for(int i = 0; i < lines; i++)
			  {
				  System.out.println(in.readLine());
			  }
		  }
		  else if(action.contains("download"))
		  {
			  TCPDownloadManager downloadManager = new TCPDownloadManager(out, dIn);
			  String fileName = action.split(" ")[1];
			  out.println(action);
			  downloadManager.manageDownload(fileName);
		  }
		  clientSocket.close();

	}
}
