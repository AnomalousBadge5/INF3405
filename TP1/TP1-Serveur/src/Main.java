import java.util.Scanner;
import java.net.InetAddress; 
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//Port UDP 5022
public class Main {
	public static void main(String[] args) throws Exception
	{
		System.out.printf("Ip address :");
		System.out.println(InetAddress.getLocalHost());
		// protocol udp
		ListFolderUDP listFolderUDP = new ListFolderUDP();
		DatagramSocket server = new DatagramSocket(5022);
		DatagramPacket packetRecieve = null;
		DatagramPacket packetSend = null;
		byte[] receive = new byte [65535];
		byte[] listFolder = null;
		// creation of a datagramPacket to receive the data
		packetRecieve = new DatagramPacket(receive, receive.length);
		// receive data in byte buffer
		// blocking call
		server.receive(packetRecieve);
		// display
		Date date = new Date();
		System.out.printf("[" + packetRecieve.getAddress() + ":" + packetRecieve.getPort() + " - " + date.getDate() + "]: " + packetRecieve.getData());
		if (packetRecieve.getData().toString().equals("ls"))
		{
			listFolder = new String(listFolderUDP.getListFolderUDP()).getBytes();
			packetSend = new DatagramPacket(listFolder, listFolder.length, packetRecieve.getAddress(), packetRecieve.getPort());
			server.send(packetSend);
			packetSend.setLength(listFolder.length);
		}
		else if (true) // Case Download
		{
			
		}
	}
}
