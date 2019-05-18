
public class Main {
	public static void main(String[] args) throws Exception
	{
		AddressFinder.findOwnAddress();
		UDPServerManager udpServer = new UDPServerManager();
		udpServer.startServer();
	}
}
