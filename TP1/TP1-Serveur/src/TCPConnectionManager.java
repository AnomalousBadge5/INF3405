import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnectionManager {
	public BufferedReader in;
	public PrintWriter out;
	
	public TCPConnectionManager(ServerSocket server) throws Exception
	{
		// Le serveur ne recoit jamais la demande de socket
		Socket socket = server.accept();
		System.out.println("command recieve");
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream(), true);

	}
	
	public String receive() throws Exception
	{
		return in.readLine();
	}
	
	public void send(String message) throws Exception
	{
		out.println(message);
	}
}
