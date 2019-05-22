import java.io.File;
import java.nio.file.Files;

public class ListFolder {
	
	public String getListFolder(String folder)
	{
		File repertory = new File("." + File.separator + folder);
		String list[] = repertory.list();
		String send = "";
		if (list != null)
		{
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
	
	public byte[] getBytesFromFile(String fileName, String folder) throws Exception
	{
		String filePath = "." + File.separator + folder + File.separator + fileName;
		File file = new File(filePath);
		byte[] data = Files.readAllBytes(file.toPath());
		return data;
	}
}
