
import java.io.FileOutputStream; 

public class FileManager {
	
	public boolean writeByteArray(byte[] data)
	{
		try (FileOutputStream stream = new FileOutputStream("./test.txt")) {
		    stream.write(data);
		} catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
