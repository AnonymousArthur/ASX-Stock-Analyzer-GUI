import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
 
public class Trade {

	public Date date;
	public double price;
	public double volume;
	public char signal;
	
	public Trade(String d,double price,double volume,char signal,String module){
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		if(module == "aurora.jar"){
			format = new SimpleDateFormat("yyyy-MM-dd",
					Locale.ENGLISH);
		}
		try {
			this.date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.price = price;
		this.signal = signal;
		this.volume = volume;
	}
}
