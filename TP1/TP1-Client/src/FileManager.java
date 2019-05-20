
import java.io.FileOutputStream; 

public class FileManager {
	
	public boolean writeByteArray(byte[] data, String nameFile)
	{
		// Changes for file's name
		try (FileOutputStream stream = new FileOutputStream(nameFile)) {
		    stream.write(data);
		} catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
