import java.util.Scanner;
public class ChoiceAction {
	public String choice = new String();

	public ChoiceAction() {}
	private boolean checkIfCorrect()
	{
		boolean isCorrect = false;
		String download = new String("download");
		if (choice.contentEquals("ls") || choice.contentEquals("back"))
		{
			isCorrect = true;
		}
		else if (download.length() == choice.length())
		{
			isCorrect = true;
			for (int i = 0; i < download.length(); i++)
			{
				if (download.getBytes()[i] != choice.getBytes()[i])
				{
					isCorrect = false;
				}
			}
		}
		return isCorrect;
	}
	public void Choose(Scanner scanner)
	{
		boolean choiceIsOk = false;
		while(!choiceIsOk)
		{
			System.out.printf("Please choose what you want to do:\n");
			choice = scanner.nextLine();
			if (!checkIfCorrect())
			{
				System.out.printf("Incorrect entry\nPlease choose between ls, download <file> and back\n");
			}
			else
			{
				choiceIsOk = true;
			}
		}
	}
}
