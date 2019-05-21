
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
		if(action.contentEquals("ls"))
		{
			byte[] receivedBytes = this.receiveData();
			String dataStr = new String(receivedBytes, 0, receivedBytes.length);
			dataStr = dataStr.replace("\0", "");
			System.out.println(dataStr);
		} else // download
		{
			UDPDownloadManager uploader = new UDPDownloadManager(this.serverAddress, this.datagramSocket);
			byte[] receivedBytes = uploader.manageDownload();
			FileManager fileManager = new FileManager();
			String message = "File transfer ";
			String[] list = action.split(" ");
			String fileName = ".\\udp\\" + list[1]; // Add
			message += fileManager.writeByteArray(receivedBytes, fileName) ? "successful" : "failed";
			System.out.println(message);
		}
	}
	
	public void sendData(byte[] data) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, this.serverAddress, this.UDP_PORT);
		this.datagramSocket.send(packet);
	}
	
	public byte[] receiveData(int length) throws Exception
	{
		byte[] data = new byte[length];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		this.datagramSocket.receive(packet);
		return packet.getData();
	}
	
	public byte[] receiveData() throws Exception
	{
		return this.receiveData(2000);
	}
}
