import java.io.BufferedReader;
import java.io.PrintWriter;

public class TCPDownloadManager
{
	public PrintWriter out;
	public BufferedReader in;
	
	public TCPDownloadManager(PrintWriter out, BufferedReader in)
	{
		this.in = in;
		this.out = out;
	}
	public void manageDownload(String fileName) throws Exception
	{
		int lines = Integer.parseInt(in.readLine());
		String response = "";
		for(int i = 0; i < lines; i++)
		{
			response += in.readLine();
		}
		byte[] data = response.getBytes();
		System.out.println(data.length);
		FileManager fileManager = new FileManager();
		String message = "File transfer ";
		fileName = "./tcp/" + fileName;
		message += fileManager.writeByteArray(data, fileName) ? "successful" : "failed";
		System.out.println(message);
	}
}
