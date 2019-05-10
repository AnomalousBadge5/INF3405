import java.net.InetAddress; 

public class IpAddress {
	public final String ipAdressString;
	public final InetAddress ipAdressInet;
	
	public IpAddress(String ipString, InetAddress ipInet)
	{
		ipAdressString = ipString;
		ipAdressInet = ipInet;
	}
}
