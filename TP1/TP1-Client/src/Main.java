
import java.net.InetAddress; 
import java.util.Scanner;

// Port UDP 5022
public class Main {

	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		InputManager inputManager = new InputManager(scanner);
		String serverAddressStr = inputManager.inputIpAdress();
		IpAddress ip = new IpAddress(serverAddressStr, InetAddress.getByName(serverAddressStr));
		while (true)
		{
			String protocol = inputManager.chooseProtocol();
			if(!protocol.toLowerCase().contentEquals("tcp") && !protocol.toLowerCase().contentEquals("udp"))
			{
				return;
			}
			ProtocolInterface protocolManager = (protocol == "tcp") ? protocolManager = new TCPManager(ip.ipAdressInet)
				: new UDPManager(ip.ipAdressInet);
			while(true)
			{
				String action = inputManager.chooseAction();
				if(action.contentEquals("back"))
				{
					break;
				}
				protocolManager.manageAction(action);
			}
		}
	}
}
