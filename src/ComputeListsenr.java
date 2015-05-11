import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ComputeListsenr {
	ActionListener listener;
	static String inpuFilePath;
	static String fileName;
	//private ArrayList<JTabbedPane> company_tabs = new ArrayList<>();
	
	
	public ComputeListsenr(final Arguments_form arguments_form_module1, final JTabbedPane jtp_companies, String module) {
		listener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().beep();

				// MAKE THIS VARIABLE
				// Build string to run the jar
				String filepath = module;
				Process proc;
				// Add input file parameter
				String execCommand = "java -jar " + filepath;
				if(module == "Awesome-MSM-1.8.1.jar"){
					// Add fileName of parameters file
					execCommand = execCommand + " " + inpuFilePath
							+ " parameters.txt";
					// Create parameters.txt file
					int window = 3;
					double threshold = 0.001;
					String sdate;
					String edate;
					// Add window, default value is 3 if empty
					window = arguments_form_module1.getWindow();
					// Add threshold, default value is 0.001 if empty
					threshold = arguments_form_module1.getThreshold();
                     			sdate = arguments_form_module1.getsdate();
                    			LocalDate sdate1 = arguments_form_module1.startDatePicker.getValue();
                    			edate = arguments_form_module1.getedate();
                			 LocalDate edate1 = arguments_form_module1.endDatePicker.getValue();
                    			String sdate2 = "01-JAN-2000";
                			String edate2 = "01-JAN-2016";
                			 if(!(sdate1 == null || edate1 == null)){
                    				sdate2 = sdate1.toString();
                    				edate2 = edate1.toString();
                    				Date date = null;
                    				Date date2 = null;
                    				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						DateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
						try {
							date = format.parse(sdate2);
							date2 = format.parse(edate2);
						} catch (ParseException e2) {
							e2.printStackTrace();
						}
						sdate2 = format2.format(date);
						edate2 = format2.format(date2);	
                			}
					File fileTemp = new File("parameters.txt");
					if (fileTemp.exists()) {
						fileTemp.delete();
					}
					try (PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter("parameters.txt", true)))) {
						out.println("window = " + window);
						out.println("threshold = " + threshold);						
						out.println("startDate = " + sdate2);
						out.println("endDate = " + edate2);
						out.println("output = summary.csv");
					} catch (IOException e1) {
					}
				// System.out.println(execCommand);
				// Run module
				}
				if(module =="aurora.jar"){
					execCommand = execCommand + " " + inpuFilePath
							+ " aurora_params.txt";
					int window = 3;
					double threshold = 0.001;
					String sdate;
					String edate;
					// Add window, default value is 3 if empty
					window = arguments_form_module1.getWindow();
					// Add threshold, default value is 0.001 if empty
					threshold = arguments_form_module1.getThreshold();
					
					
					sdate = arguments_form_module1.getsdate();
                    LocalDate sdate1 = arguments_form_module1.startDatePicker.getValue();
                    edate = arguments_form_module1.getedate();
                    LocalDate edate1 = arguments_form_module1.endDatePicker.getValue();
				//	System.out.println(sdate1 + " " + edate1);

					String sdate2 = sdate1.toString();
					String edate2 = edate1.toString();
					Date date = null;
					Date date2 = null;
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
					DateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
					try {
						date = format.parse(sdate2);
						date2 = format.parse(edate2);
					} catch (ParseException e2) {
  						e2.printStackTrace();
					}
					sdate2 = format2.format(date);
					edate2 = format2.format(date2);		
					
				//	System.out.println(sdate2 + " " + edate2);
                  
           
					File fileTemp = new File("aurora_params.txt");
					if (fileTemp.exists()) {
						fileTemp.delete();
					}
					try (PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter("aurora_params.txt", true)))) {
						out.println("start_date = " + sdate2);
						out.println("end_date = " + edate2);
						out.println("moving_average_window = " + window);
						out.println("threshold = " + threshold);
						out.println("output_dir = aurora_output.csv");
					} catch (IOException e1) {
					}
				}
				
				if(module =="trockAT"){
					execCommand = "./trockAT" +   " trock_paramaters.txt";
					int window = 3;
					double threshold = 0.001;
					String sdate;
					String edate;
					// Add window, default value is 3 if empty
					window = arguments_form_module1.getWindow();
					// Add threshold, default value is 0.001 if empty
					threshold = arguments_form_module1.getThreshold();
                    sdate = arguments_form_module1.getsdate();
                    edate = arguments_form_module1.getedate();
                    
					File fileTemp = new File("trock_paramaters.txt");
					if (fileTemp.exists()) {
						fileTemp.delete();
					}
					try (PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter("trock_paramaters.txt", true)))) {
						out.println(":input_csvFile:" + inpuFilePath + "\\");
						out.println(":output_csvFile:trock_output.csv\\");
						out.println(":output_logFile:TrockAT.log\\");
						out.println(":returnsInCalculation:" + window + "\\");
						out.println(":threshold:" + threshold + "\\");
						out.println(":startDate:" + sdate + "\\");
						out.println(":endDate:" + edate + "\\");
					} catch (IOException e1) {
					}
				}
				
				try {

					// System.out.println("FSDFDSFds");
					proc = Runtime.getRuntime().exec(execCommand);
					proc.waitFor();
					InputStream in = proc.getInputStream();
					InputStream err = proc.getErrorStream();

					byte b[] = new byte[in.available()];
					in.read(b, 0, b.length);
					// System.out.println(new String(b));

					byte c[] = new byte[err.available()];
					err.read(c, 0, c.length);
					// System.out.println(new String(c));
					// System.out.println("DONE");
					// create graph
					System.out.println(module);
					LinkedHashMap<String, ArrayList<TradeRec>> dataHashMap = new LinkedHashMap<String, ArrayList<TradeRec>>();
					LinkedHashMap<String, ArrayList<Trade>> trades = new LinkedHashMap<String, ArrayList<Trade>>(); 
					if(module == "Awesome-MSM-1.8.1.jar"){
						dataHashMap = CSVParser.CSVParse(inpuFilePath, module);
						trades = CSVParser.SummaryParse("summary.csv", module);
					}
					else if(module == "aurora.jar")
						//(module == "aurora.jar")
						{
						dataHashMap = CSVParser.CSVParse(inpuFilePath, module);
						trades = CSVParser.SummaryParse("aurora_output.csv", module);
					}
					else{
						dataHashMap = CSVParser.CSVParse(inpuFilePath, module);
						trades = CSVParser.SummaryParse("trock_output.csv", module);
					
					}
					/*
					for(String company: dataHashMap.keySet()){
						 JTabbedPane company_tab = new JTabbedPane();
						 company_tabs.add(company_tab);
						 //jtp_companies.add(company + " from " + fileName,company_tab);
					}*/
					
					
					for(String company: dataHashMap.keySet()){
						JTabbedPane company_tab = new JTabbedPane();
						jtp_companies.add(company + " from " + fileName,company_tab);
					// price graph

						Date sDate;
						Date eDate;
					if(arguments_form_module1.startDatePicker.getValue()!=null && arguments_form_module1.endDatePicker.getValue()!=null){
						 sDate = Date.from(arguments_form_module1.startDatePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
						 eDate = Date.from(arguments_form_module1.endDatePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
					}else{
						sDate = null;
						eDate = null;
					}
					Price price = new Price("Price - ", "Price over Time",
							dataHashMap.get(company),trades.get(company),sDate,eDate);

						company_tab.add("Price", price.get_chartPanel());
					// return graph
						Return return_ = new Return("Return - company", 
		 					 "Return over Time", 
		 					 trades.get(company), 
		 					 company);
						company_tab.add("Return", return_.get_chartPanel());
					// profit graph
						Profit profit = new Profit(
							      "Profit - " + company ,
							      "Profit over Time",
							      trades.get(company),
							      company);
						company_tab.add("Profit", profit.get_chartPanel());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
	}

	public ActionListener returnListener() {
		return listener;
	}

	public void setFileName(String fileN) {
		this.fileName = fileN;
	}

	public void setFilePath(String fileP) {
		this.inpuFilePath = fileP;
	}

}

