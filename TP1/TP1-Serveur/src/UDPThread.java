
public class UDPThread extends Thread
{
	public void run()
	{
		try
		{
			UDPServerManager udpServer = new UDPServerManager();
			udpServer.startServer();
		} catch(Exception e) {
			System.out.println("UDP server has crashed");
		}
	}
}
