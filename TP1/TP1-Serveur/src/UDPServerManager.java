import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerManager {
	private DatagramSocket server;
	
	public UDPServerManager() throws Exception
	{
		server = new DatagramSocket(5022);
	}
	
	public void startServer() throws Exception
	{
		boolean continuer = true;
		while (continuer)
		{
			DatagramPacket packetRecieve = null;
			byte[] receive = new byte [65535];
			packetRecieve = new DatagramPacket(receive, receive.length);
			this.server.receive(packetRecieve);
			String dataStr = this.getDataFromPacket(packetRecieve);
			Client client = new Client(packetRecieve.getAddress(), packetRecieve.getPort());
			this.printClientDemand(client, dataStr);
			this.manageAction(client, dataStr);
		}
	}
	
	public void manageAction(Client client, String action) throws Exception
	{
		ListFolder listFolderUDP = new ListFolder();
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
			UploadManager uploader = new UploadManager(client, data, this.server);
			uploader.upload();
		}
	}
	
	public void sendData(Client client, byte[] data) throws Exception
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, client.address, client.port);
		this.server.send(packet);
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
