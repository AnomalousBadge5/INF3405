
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress; 
// Port UDP 5022
public class Main {

	public static void main(String[] args) throws Exception
	{
		IpAdressInput input = new IpAdressInput();
		// todo: mettre ca dans une classe -- complete
		IpAddress ip = new IpAddress(input.getIpAdress(), InetAddress.getByName(input.getIpAdress()));
		ChoiceProtocol choiceProtocol = new ChoiceProtocol();
		ChoiceAction choiceAction = new ChoiceAction();
		boolean end = false;
		// menu principal
		Socket socket;
		DatagramSocket ds;
		DatagramPacket packet;
		while (!end)
		{
			choiceProtocol.Choose();
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
		System.out.print("Your IP adress is valid: ");
		System.out.print(ip.ipAdressString);
		System.out.print("\nYour IP adress: ");
		Socket s = new Socket("www.google.com", 80);
		System.out.println(s.getLocalAddress().getHostAddress());
		System.out.print("\n");
		System.out.println(s.getLocalAddress().getHostName());
		s.close();
	}

}
