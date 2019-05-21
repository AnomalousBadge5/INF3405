import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnectionManager {
	public BufferedReader in;
	public DataOutputStream dOut;
	public PrintWriter out;
	public Socket socket;
	public TCPConnectionManager(ServerSocket server) throws Exception
	{
		this.socket = server.accept();
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new PrintWriter(socket.getOutputStream(), true);
		this.dOut = new DataOutputStream(socket.getOutputStream());

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
