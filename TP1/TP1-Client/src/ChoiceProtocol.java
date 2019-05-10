import java.util.Scanner;

public class ChoiceProtocol {
	public String choice = new String();
	
	public ChoiceProtocol()
	{
		choice = "";
	}
	
	public void Choose()
	{
		Scanner scanner = new Scanner(System.in);
		boolean choiceIsOk = false;
		while(!choiceIsOk)
		{
			System.out.printf("Please choose the communication protocol:\n");
			choice = scanner.nextLine();
			if (choice != "UDP" && choice != "TCP" && choice != "exit")
			{
				System.out.printf("Incorrect entry\nPlease choose between TCP, UDP and exit\n");
			}
			else
			{
				choiceIsOk = true;
			}
		}
		scanner.close();
	}
}

