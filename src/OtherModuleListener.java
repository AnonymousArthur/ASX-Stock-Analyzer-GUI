import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class OtherModuleListener {
	ActionListener listener;
	static String inpuFilePath;
	static String fileName;
	//private ArrayList<JTabbedPane> company_tabs = new ArrayList<>();
	
	
	public OtherModuleListener(	final JTabbedPane jtp_companies) {
		listener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().beep();

				// MAKE THIS VARIABLE
				// Build string to run the jar
				String filepath = "aurora.jar";
				Process proc;
				// Add input file parameter
				String execCommand = "java -jar " + filepath;
				// Add fileName of parameters file
				execCommand = execCommand + " " + inpuFilePath
						+ " aurora_params.txt";
				// System.out.println(execCommand);
				// Run module
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
					
					LinkedHashMap<String, ArrayList<TradeRec>> dataHashMap = CSVParser.CSVParse(inpuFilePath);
					LinkedHashMap<String, ArrayList<Trade2>> trades = CSVParser2.SummaryParse("output.csv");
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
						Price2 price = new Price2("Price - ", "Price over Time",
							dataHashMap.get(company),trades.get(company));
						company_tab.add("Price", price.get_chartPanel());
					// return graph
						Return2 return_ = new Return2("Return - company", 
		 					 "Return over Time", 
		 					 trades.get(company), 
		 					 company);
						company_tab.add("Return", return_.get_chartPanel());
					// profit graph
						Profit2 profit = new Profit2(
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
