import java.util.Scanner;

public class ChoiceProtocol {
	public String choice = new String();
	
	public ChoiceProtocol()
	{
		choice = "";
	}
	
	public void Choose(Scanner scanner)
	{
		boolean choiceIsOk = false;
		while(!choiceIsOk)
		{
			System.out.printf("Please choose the communication protocol:\n");
			choice = scanner.nextLine();
			System.out.print(choice);
			if (choice.contentEquals("UDP") || choice.contentEquals("TCP") || choice.contentEquals("exit"))
			{
				choiceIsOk = true;
			}
			else
			{
				System.out.printf("Incorrect entry\nPlease choose between TCP, UDP and exit\n");	
			}
		}
	}
}

