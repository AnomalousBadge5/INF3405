
import java.util.Scanner;

public class IpAdressInput
{
	String ipAdress = "";
	public static final int ipAdressLength = 4;

	public String getIpAdress()
	{
		Scanner scanner = new Scanner(System.in);
		boolean ipIsOk = false;
		while(!ipIsOk)
		{
			System.out.printf("Please enter the server IP adress:\n");
			ipAdress = scanner.nextLine();
			String[] strs = ipAdress.split("\\.");
			ipIsOk = (strs.length == ipAdressLength);
			if (ipIsOk)
			{
				for (String each : strs)
				{
					int number = Integer.parseInt(each);
					if(number > 255 || number < 0)
					{
						ipIsOk = false;
					}
				}
			}
		}
		scanner.close();
		return ipAdress;
	}
}
