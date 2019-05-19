import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UDPServerManager {
	private DatagramSocket server;
	
	public UDPServerManager() throws Exception
	{
		server = new DatagramSocket(5022);
	}
	
	public void startServer() throws Exception
	{
		boolean continuer = true;
		// protocol udp
		while (continuer)
		{
			DatagramPacket packetRecieve = null;
			byte[] receive = new byte [65535];
			// creation of a datagramPacket to receive the data
			packetRecieve = new DatagramPacket(receive, receive.length);
			// receive data in byte buffer
			// blocking call
			this.server.receive(packetRecieve);
			// display
			String dataStr = this.getDataFromPacket(packetRecieve);
			Client client = new Client(packetRecieve.getAddress(), packetRecieve.getPort());
			this.printClientDemand(client, dataStr);
			this.manageAction(client, dataStr);
		}
	}
	
	public void manageAction(Client client, String action) throws Exception
	{
		ListFolderUDP listFolderUDP = new ListFolderUDP();
		if (action.contentEquals("ls"))
		{
			byte[] data = new String(listFolderUDP.getListFolderUDP()).getBytes();
			this.sendData(client, data);
		}
		else if(action.contains("download"))
		{
			String[] list = action.split(" ");
			String fileName = list[1];
			byte[] data = listFolderUDP.getBytesFromFile(fileName);
			this.sendData(client, data);
		}
	}
	
	public void sendData(Client client, byte[] data) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, client.address, client.port);
		this.server.send(packet);
	}
	
	public void sendMultiplePackages(Client client, byte[] data) throws Exception
	{
		final int MAX_PACKET_SIZE = 1000;
		int packetSize = MAX_PACKET_SIZE;
		for(int i = 0; i * MAX_PACKET_SIZE <= data.length; i++)
		{
			if(i * MAX_PACKET_SIZE < data.length)
			{
				packetSize = data.length % MAX_PACKET_SIZE;
			}
			byte[] dataToSend = this.makePackage(i, packetSize, MAX_PACKET_SIZE, data);
			this.sendData(client, dataToSend);
		}
	}
	
	private byte[] makePackage(int i, int packetSize, int MAX_PACKET_SIZE, byte[] data)
	{
		final int INT_SIZE = 4;
		byte[] dataToSend = new byte[INT_SIZE + INT_SIZE + packetSize];
		byte[] position = ByteBuffer.allocate(INT_SIZE).putInt(i).array();
		byte[] messageLength = ByteBuffer.allocate(INT_SIZE).putInt(packetSize).array();
		System.arraycopy(position, 0, dataToSend, 0, INT_SIZE);
		System.arraycopy(messageLength, 0, dataToSend, INT_SIZE, INT_SIZE);
		System.arraycopy(Arrays.copyOfRange(data, i * MAX_PACKET_SIZE, i * MAX_PACKET_SIZE + packetSize), 0, dataToSend, INT_SIZE * 2, dataToSend.length);
		return dataToSend;
	}

	
	private void printClientDemand(Client client, String demand)
	{
		GetDate date = new GetDate();
		System.out.printf("[" + client.address + ":" + client.port + " - " + date.getDate() + "]: " + demand + "\n");
	}
	
	private String getDataFromPacket(DatagramPacket packet)
	{
		String datastr = new String(packet.getData(), 0, packet.getData().length);
		return datastr.replace("\0", "");
	}
	
	public void closeServer()
	{
		server.close();
	}
}
