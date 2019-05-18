
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.OutputStream;

public class FileManager {
	static File file = new File("./testFile");
	
	public boolean writeByteArray(byte[] data)
	{
		try
		{
			OutputStream output = new FileOutputStream(file);
			output.write(data);
			output.close();
		} catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
