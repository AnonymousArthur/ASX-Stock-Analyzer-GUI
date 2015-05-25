import java.util.ArrayList;


public class Record {
	private int max_size = 100;
	private ArrayList<String> modules = new ArrayList<String>();
	ArrayList<String> companys = new ArrayList<String>();
	ArrayList<Double> returns = new ArrayList<Double>();
	ArrayList<String> from_dates = new ArrayList<String>();
	ArrayList<String> to_dates = new ArrayList<String>();
	ArrayList<String> file_names = new ArrayList<String>();
	
	public Record(){
	}
	
	public ArrayList<String> get_companys(){
		return companys;
	}
	public ArrayList<String> get_file_names(){
		return file_names;
	}
	
	public ArrayList<Double> get_returns(){
		return returns;
	}
	public ArrayList<String> get_from_dates(){
		return from_dates;
	}
	public ArrayList<String> get_to_dates(){
		return to_dates;
	}
	public ArrayList<String> get_modules(){
		return modules;
	}
	public void set_return(double returnV){
		returns.add(returnV);
	}
	public void set_from_date(String date){
		from_dates.add(date);
	}
	public void set_to_date(String date){
		to_dates.add(date);
	}
	public void set_company(String c){
		companys.add(c);
	}
	public void set_file_name(String f){
		file_names.add(f);
	}
	public void set_module(String module){
		this.modules.add(module);
	}
}
