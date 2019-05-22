
public class DemandPrinter {
	public static void printDemand(Client client, String demand)
	{
		System.out.printf("[" + client.address + ":" + client.port + " - " + GetDate.getDate() + "]: " + demand + "\n");
	}
}
