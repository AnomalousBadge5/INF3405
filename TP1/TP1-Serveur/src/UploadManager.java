import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UploadManager {
	public Client client;
	public byte[] data;
	public DatagramSocket server;
	
	public UploadManager(Client client, byte[] data, DatagramSocket server)
	{
		this.client = client;
		this.data = data;
		this.server = server;
	}
	
	public void upload() throws Exception
	{
		byte[] downloadLength = ByteBuffer.allocate(4).putInt(data.length).array();
		this.sendData(downloadLength);
		byte[] reception = this.receiveData();
		String receptionStr = new String(reception, 0, reception.length);
		receptionStr = receptionStr.replace("\0", "");
		if(!receptionStr.contentEquals("start"))
		{
			return;
		}
		this.sendMultiplePackages();
	}
	
	public void sendData(byte[] data) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, client.address, client.port);
		this.server.send(packet);
	}
	
	public byte[] receiveData() throws Exception
	{
		byte[] receive = new byte [1000];
		DatagramPacket packet = new DatagramPacket(receive, receive.length);
		this.server.receive(packet);
		return packet.getData();
	}
	
	public void sendMultiplePackages() throws Exception
	{
		final int MAX_PACKET_SIZE = 1000;
		int packetSize = MAX_PACKET_SIZE;
		for(int i = 0; i * MAX_PACKET_SIZE < this.data.length; i++)
		{
			if((i + 1) * MAX_PACKET_SIZE > this.data.length)
			{
				packetSize = this.data.length % MAX_PACKET_SIZE;
			}
			byte[] dataToSend = this.makePackage(i, packetSize, MAX_PACKET_SIZE);
			this.sendData(dataToSend);
		}
	}
	
	private byte[] makePackage(int i, int packetSize, int MAX_PACKET_SIZE)
	{
		final int INT_SIZE = 4;
		byte[] dataToSend = new byte[INT_SIZE + INT_SIZE + packetSize];
		byte[] position = ByteBuffer.allocate(INT_SIZE).putInt(i).array();
		byte[] messageLength = ByteBuffer.allocate(INT_SIZE).putInt(packetSize).array();
		System.arraycopy(position, 0, dataToSend, 0, INT_SIZE);
		System.arraycopy(messageLength, 0, dataToSend, INT_SIZE, INT_SIZE);
		System.arraycopy(Arrays.copyOfRange(this.data, i * MAX_PACKET_SIZE, i * MAX_PACKET_SIZE + packetSize), 0, dataToSend, INT_SIZE * 2, packetSize);
		return dataToSend;
	}

}
