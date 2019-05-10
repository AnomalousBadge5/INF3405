import java.io.File;

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
}
