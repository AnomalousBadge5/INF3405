import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UDPDownloadManager {
	public final int UDP_PORT = 5022;
	public InetAddress serverAddress;
	public DatagramSocket datagramSocket;
	public int downloadLength;
	public int packageCount;
	
	public UDPDownloadManager(InetAddress serverAddress, DatagramSocket datagramSocket)
	{
		this.serverAddress = serverAddress;
		this.datagramSocket = datagramSocket;
	}
	
	public byte[] manageDownload() throws Exception
	{
		ByteBuffer wrapped = ByteBuffer.wrap(this.receiveData(4));
		this.downloadLength = wrapped.getInt();
		this.packageCount = (this.downloadLength / 1000) + 1;
		byte[] data = new byte[this.downloadLength];
		final int MAX_PACKET_LENGTH = 1000;
		while(this.packageCount > 0)
		{
			byte[] reception = this.receiveData(MAX_PACKET_LENGTH + 2 * 4);
			ByteBuffer packageNumberB = ByteBuffer.wrap(Arrays.copyOfRange(reception, 0, 4));
			int packageNumber = packageNumberB.getInt();
			ByteBuffer packageLengthB = ByteBuffer.wrap(Arrays.copyOfRange(reception, 4, 8));
			int packageLength = packageLengthB.getInt();
			ByteBuffer packageData = ByteBuffer.wrap(Arrays.copyOfRange(reception, 8, 8 + packageLength));
			System.arraycopy(packageData, 0, data, packageNumber * 1000, packageLength);
			packageCount--;
		}
		return data;
	}
	
	public byte[] receiveData(int length) throws Exception
	{
		byte[] data = new byte[length];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		this.datagramSocket.receive(packet);
		return packet.getData();
	}
}
