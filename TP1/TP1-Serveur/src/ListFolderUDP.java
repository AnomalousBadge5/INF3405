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
		 FileInputStream file = new FileInputStream(fileName);
		 byte[] data = new byte[1024];
		 int i = 0;
		 while(file.available() != 0)
		 {
			 data[i++] = (byte)file.read();
		 }
		 file.close();
		 return data;
	}
}
