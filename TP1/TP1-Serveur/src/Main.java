
public class Main {
	public static void main(String[] args) throws Exception
	{
		AddressFinder.findOwnAddress();
		UDPThread udp = new UDPThread();
		udp.start();
		TCPThreadManager tcp = new TCPThreadManager();
		tcp.start();
	}
}
