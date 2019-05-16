import java.util.Scanner;
import java.net.InetAddress; 
import java.net.Socket;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//Port UDP 5022
public class Main {
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("www.google.com", 80);
		System.out.printf("Ip address :");
		System.out.println(s.getLocalAddress().getHostAddress());
		s.close();
		DatagramSocket server = new DatagramSocket(5022);
		boolean continuer = true;
		// protocol udp
		while (continuer)
		{
			ListFolderUDP listFolderUDP = new ListFolderUDP();
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
			GetDate date = new GetDate();
			String datastr = new String(packetRecieve.getData(),0,packetRecieve.getData().length);
			datastr = datastr.replace("\0", "");
			System.out.printf("[" + packetRecieve.getAddress() + ":" + packetRecieve.getPort() + " - " + date.getDate() + "]: " + datastr + "\n");
			if (datastr.contentEquals("ls"))
			{
				listFolder = new String(listFolderUDP.getListFolderUDP()).getBytes();
				packetSend = new DatagramPacket(listFolder, listFolder.length, packetRecieve.getAddress(), packetRecieve.getPort());
				server.send(packetSend);
				packetSend.setLength(listFolder.length);
			}
			else if (datastr.contentEquals("back")) // Case back
			{
				
			}
			else // case download
			{
				
			}
		}
		server.close();
	}
}
