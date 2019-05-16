
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress; 
import java.util.Scanner;

// Port UDP 5022
public class Main {

	public static void main(String[] args) throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		IpAdressInput input = new IpAdressInput();
		// todo: mettre ca dans une classe -- complete
		String ipString = "132.207.29.110";// input.getIpAdress(scanner);
		IpAddress ip = new IpAddress(ipString, InetAddress.getByName(ipString));
		ChoiceProtocol choiceProtocol = new ChoiceProtocol();
		ChoiceAction choiceAction = new ChoiceAction();
		boolean end = false;
		// menu principal
		Socket socket;
		DatagramSocket ds;
		DatagramPacket sendPacket;
		DatagramPacket receivePacket;
		choiceProtocol.Choose(scanner);
		while (!end)
		{
			switch (choiceProtocol.choice)
			{
			case "UDP" :
				// Initilisation connexion client
				// create datagramSocket and datagramPacket
				System.out.println("Connecting to server");
				ds = new DatagramSocket();
				while(true)
				{
					choiceAction.Choose(scanner);
					sendPacket = new DatagramPacket(choiceAction.choice.getBytes(), choiceAction.choice.getBytes().length, ip.ipAdressInet, 5022);
					sendPacket.setData(choiceAction.choice.getBytes());
					ds.send(sendPacket);
					byte[] receiveStr = new byte[65535];
					receivePacket = new DatagramPacket(receiveStr, receiveStr.length);
					ds.receive(receivePacket);
					String dataStr = new String(receivePacket.getData(), 0, receivePacket.getData().length);
					System.out.println(dataStr);
				}
				
			case "TCP" :
				System.out.println("Connecting to server");
				socket = new Socket(ip.ipAdressInet, 5023);
				choiceAction.Choose(scanner);
				
				break;
			case "EXIT" :
				end = true;
				break;
			}
		}
	}
}
