import java.text.SimpleDateFormat;
import java.util.Date;
public class GetDate {
	public String getDate()
	{
		String date2 = new SimpleDateFormat("yyyy-MM-dd@hh:mm:ss").format(new Date());
		return  date2;
	}
}
