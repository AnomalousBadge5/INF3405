
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.DatagramSocket;


public class UDPManager
{
	public final int UDP_PORT = 5022;
	public InetAddress serverAddress;
	public DatagramSocket datagramSocket;
	
	public UDPManager(InetAddress serverAddress) throws Exception
	{
		this.serverAddress = serverAddress;
		this.datagramSocket = new DatagramSocket();
	}
	
	public void manageAction(String action) throws Exception
	{
		byte[] data = action.getBytes();
		this.sendData(data);
		
		byte[] receivedBytes = this.receiveData();
		if(action.contentEquals("ls"))
		{
			String dataStr = new String(receivedBytes, 0, receivedBytes.length);
			dataStr = dataStr.replace("\0", "");
			System.out.println(dataStr);
		} else
		{
			FileManager fileManager = new FileManager();
			fileManager.writeByteArray(data);
		}
	}
	
	public void sendData(byte[] data) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, this.serverAddress, this.UDP_PORT);
		this.datagramSocket.send(packet);
	}
	
	public byte[] receiveData() throws Exception
	{
		byte[] data = new byte[2000];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		this.datagramSocket.receive(packet);
		return packet.getData();
	}
}
