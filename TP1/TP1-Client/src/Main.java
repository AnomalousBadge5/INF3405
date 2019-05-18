
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
				// Initilisation connexion client
				// create datagramSocket and datagramPacket
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
				System.out.println("Connecting to server");
				socket = new Socket(ip.ipAdressInet, 5023);
				inputManager.chooseAction();;
				
				break;
			case "EXIT" :
				end = true;
				break;
			}
		}
	}
}
