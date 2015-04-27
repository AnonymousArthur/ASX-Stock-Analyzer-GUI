import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Trade {

	public Date date;
	public double price;
	public char signal;
	
	public Trade(String d,double price,char signal){
		DateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
		try {
			this.date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.price = price;
		this.signal = signal;
	}
}
