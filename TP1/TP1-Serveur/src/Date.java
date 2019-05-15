import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Date {
	public String getDate()
	{
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd@hh:mm:ss");
		Date date = new Date();
		return  format.format(date);
	}
}
