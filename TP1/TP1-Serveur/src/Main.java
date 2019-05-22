
public class Main {
	public static void main(String[] args) throws Exception
	{
		try
		{
			AddressFinder.findOwnAddress();
		} catch(Exception e)
		{
			System.out.println("Cannot connect to internet");
			return;
		}
		UDPThread udp = new UDPThread();
		udp.start();
		TCPThreadManager tcp = new TCPThreadManager();
		tcp.start();
	}
}
