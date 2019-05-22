import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerManager {
	private DatagramSocket server;
	private static final int PORT = 5022;
	
	public UDPServerManager() throws Exception
	{
		server = new DatagramSocket(UDPServerManager.PORT);
	}
	
	public void startServer() throws Exception
	{
		while (true)
		{
			DatagramPacket packetRecieve = null;
			byte[] receive = new byte [65535];
			packetRecieve = new DatagramPacket(receive, receive.length);
			this.server.receive(packetRecieve);
			String dataStr = this.getStringFromPacket(packetRecieve);
			Client client = new Client(packetRecieve.getAddress(), packetRecieve.getPort());
			DemandPrinter.printDemand(client, dataStr);
			this.manageAction(client, dataStr);
		}
	}
	
	public void manageAction(Client client, String action) throws Exception
	{
		ListFolder listFolderUDP = new ListFolder();
		if (action.contentEquals("ls"))
		{
			byte[] data = new String(listFolderUDP.getListFolder("udp")).getBytes();
			this.sendData(client, data);
		}
		else if(action.contains("download") && action.split(" ").length == 2)
		{
			String[] list = action.split(" ");
			String fileName = list[1];
			byte[] data = listFolderUDP.getBytesFromFile(fileName, "udp");
			UploadManager uploader = new UploadManager(client, data, this.server);
			uploader.upload();
		}
	}
	
	public void sendData(Client client, byte[] data) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, client.address, client.port);
		this.server.send(packet);
	}
		
	private String getStringFromPacket(DatagramPacket packet)
	{
		String datastr = new String(packet.getData(), 0, packet.getData().length);
		return datastr.replace("\0", "");
	}
	
	public void closeServer()
	{
		server.close();
	}
}
