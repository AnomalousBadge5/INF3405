
import java.net.Socket;
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
		// menu principal
		Socket socket;
		boolean end = false;
		while (!end)
		{
			String protocol = inputManager.chooseProtocol();
			switch (protocol)
			{
			case "UDP" :
				UDPManager udpManager = new UDPManager(ip.ipAdressInet);
				while(true)
				{
					String action = inputManager.chooseAction();
					if(action.contentEquals("back"))
					{
						break;
					}
					udpManager.manageAction(action);
				}
				break;
			case "TCP" :
				TCPManager tcpManager = new TCPManager(ip.ipAdressInet);
				while(true)
				{
					String action = inputManager.chooseAction();
					if(action.contentEquals("back"))
					{
						break;
					}
					tcpManager.manageAction(action);
				}
				break;
			case "EXIT" :
				end = true;
				break;
			}
		}
	}
}
