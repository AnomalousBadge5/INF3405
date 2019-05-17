
import java.util.Scanner;

public class InputManager {
	public IpAdressInput ipAdressInput = new IpAdressInput();
	public ChoiceProtocol choiceProtocol = new ChoiceProtocol();
	public ChoiceAction choiceAction = new ChoiceAction();
	public Scanner scanner;
	
	public InputManager(Scanner scanner)
	{
		this.scanner = scanner;
	}
	
	public String inputIpAdress()
	{
		return this.ipAdressInput.getIpAdress(scanner);
	}
	
	public String chooseProtocol()
	{
		this.choiceProtocol.Choose(scanner);
		return this.choiceProtocol.choice;
	}
	
	public String chooseAction()
	{
		this.choiceAction.Choose(scanner);
		return this.choiceAction.choice;
	}
	
}
