import java.net.Socket;

public class AddressFinder {
	public static void findOwnAddress() throws Exception
	{
		Socket s = new Socket("www.google.com", 80);
		System.out.printf("Ip address :");
		System.out.println(s.getLocalAddress().getHostAddress());
		s.close();
	}
}
