import java.util.Scanner;
public class ChoiceAction
{
	private boolean checkIfCorrect(String choice)
	{
		choice = choice.toLowerCase();
		if (choice.contentEquals("ls") || choice.contentEquals("back"))
		{
			return true;
		}
		String[] downloadList = choice.split(" ");
		return (downloadList.length > 1) ? (downloadList[0].contentEquals("download")) : false;
	}

	public String Choose(Scanner scanner)
	{
		System.out.println("Please choose what you want to do:");
		while(true)
		{
			String choice = scanner.nextLine();
			if(this.checkIfCorrect(choice))
			{
				return choice;
			}
			System.out.printf("Incorrect entry\nPlease choose between ls, download <file> and back\n");
		}
	}
}
