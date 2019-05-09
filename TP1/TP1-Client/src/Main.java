
import java.net.Socket;


public class Main {

	public static void main(String[] args) throws Exception
	{
		IpAdressInput input = new IpAdressInput();
		String ipAdress = input.getIpAdress();
		// todo: mettre ca dans une classe
		System.out.print("Your IP adress is valid: ");
		System.out.print(ipAdress);
		System.out.print("\nYour IP adress: ");
		Socket s = new Socket("www.google.com", 80);
		System.out.println(s.getLocalAddress().getHostAddress());
		System.out.print("\n");
		System.out.println(s.getLocalAddress().getHostName());
		s.close();
	}

}
