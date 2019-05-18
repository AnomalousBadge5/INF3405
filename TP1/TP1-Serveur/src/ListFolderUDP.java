import java.io.File;
import java.io.FileInputStream;

public class ListFolderUDP {
	public String send;
	public ListFolderUDP()
	{
		send = "";
	}
	public String getListFolderUDP()
	{
		File repertory = new File("./udp");
		String list[] = repertory.list();
		send = new String();
		if (list != null)
		{
			send = "";
			for (String each : list)
			{
				send += "[File]" + each + "\n";
			}
		}
		else
		{
			send = "empty folder";
		}
		return send;
	}
	
	public byte[] getBytesFromFile(String fileName) throws Exception
	{
		String filePath = "./udp/" + fileName;
		File file = new File(filePath);
		FileInputStream stream = new FileInputStream(filePath);
		byte[] data = new byte[(int) file.length()];
		int i = 0;
		while(stream.available() != 0)
		{
				data[i++] = (byte)stream.read();
		}
		stream.close();
		return data;
	}
}
