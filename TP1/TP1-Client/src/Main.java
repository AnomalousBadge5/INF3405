
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
		String ipString = input.getIpAdress(scanner);
		IpAddress ip = new IpAddress(ipString, InetAddress.getByName(ipString));
		ChoiceProtocol choiceProtocol = new ChoiceProtocol();
		ChoiceAction choiceAction = new ChoiceAction();
		boolean end = false;
		// menu principal
		Socket socket;
		DatagramSocket ds;
		DatagramPacket packet;
		while (!end)
		{
			choiceProtocol.Choose(scanner);
			switch (choiceProtocol.choice)
			{
			case "UDP" :
				// Initilisation connexion client
				// create datagramSocket and datagramPacket
				ds = new DatagramSocket(5022, ip.ipAdressInet);
				choiceAction.Choose();
				packet = new DatagramPacket(choiceAction.choice.getBytes(), choiceAction.choice.getBytes().length, ip.ipAdressInet, 5022);
				packet.setData(choiceAction.choice.getBytes());
				ds.send(packet);
				
				
				break;
			case "TCP" :
				socket = new Socket(ip.ipAdressInet, 5023);
				choiceAction.Choose();
				
				break;
			case "exit" :
				end = true;
				break;
			}
		}
		System.out.print("\nYour IP adress: ");
		Socket s = new Socket("www.google.com", 80);
		System.out.println(s.getLocalAddress().getHostAddress());
		System.out.print("\n");		System.out.println(s.getLocalAddress().getHostName());
		s.close();
	}

}
