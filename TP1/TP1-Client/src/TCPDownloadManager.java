import java.io.DataInputStream;
import java.io.PrintWriter;

public class TCPDownloadManager
{
	public PrintWriter out;
	public DataInputStream in;
	
	public TCPDownloadManager(PrintWriter out, DataInputStream in)
	{
		this.in = in;
		this.out = out;
	}
	public void manageDownload(String fileName) throws Exception
	{
		int length = in.readInt();
		if(length > 0)
		{
			byte[] data = new byte[length];
			in.readFully(data, 0, length);
			System.out.println(data.length);
			FileManager fileManager = new FileManager();
			String message = "File transfer ";
			fileName = "./tcp/" + fileName;
			message += fileManager.writeByteArray(data, fileName) ? "successful" : "failed";
			System.out.println(message);
		}
		
	}
}
