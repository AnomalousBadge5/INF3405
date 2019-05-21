import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class ListFolder {
	public String send;
	public ListFolder()
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
	
	public String getListFolderTCP()
	{
		File repertory = new File("./tcp");
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
	
	
	public byte[] getBytesFromFileUDP(String fileName) throws Exception
	{
		String filePath = "./udp/" + fileName;
		File file = new File(filePath);
		byte[] data = Files.readAllBytes(file.toPath());
		return data;
	}
	
	public byte[] getBytesFromFileTCP(String fileName) throws Exception
	{
		String filePath = "./tcp/" + fileName;
		File file = new File(filePath);
		byte[] data = Files.readAllBytes(file.toPath());
		return data;
	}
}
