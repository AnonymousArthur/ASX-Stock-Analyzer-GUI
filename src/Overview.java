import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Overview extends JFrame{

	final static JPanel awesome = new JPanel();
	final static JPanel aurora = new JPanel();
	final static JPanel trock = new JPanel();

	GridLayout layout1 = new GridLayout(10,2);
	Record module1;
	Record module2;
	Record module3;
	JTabbedPane tabs = new JTabbedPane();

	
	public Overview(Record module1, Record module2, Record module3){
		this.setTitle("Overview");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.module1 = module1;
		this.module2 = module2;
		this.module3 = module3;

	}
	
	void display() {		
		this.pack();
		this.setSize(700, 500);
        this.setVisible(true);
        awesome.removeAll();
        aurora.removeAll();
        trock.removeAll();
        
        Object[] columnTitle = {"Record No.", "Company Name" ,"Module Name", "Data Used", "Total Return", "From Date" ,"To Date"};
        Object[][] T_data_1 = new Object[module1.get_companys().size()][7];
        if(module1.get_companys().size() != 0){
	        for(int i = 0; i<module1.get_companys().size();i++){
	        	T_data_1[i][0] = i;
	        	T_data_1[i][1] = module1.get_companys().get(i);
	        	T_data_1[i][2] = module1.get_modules().get(i);
	        	T_data_1[i][3] = module1.get_file_names().get(i);
	        	T_data_1[i][4] = module1.get_returns().get(i);
	        	T_data_1[i][5] = module1.get_from_dates().get(i);
	        	T_data_1[i][6] = module1.get_to_dates().get(i);
	        }

	        JTable table_1 = new JTable(T_data_1 , columnTitle);
	        
	        awesome.add(new JScrollPane(table_1),BorderLayout.SOUTH);
        }

        
        if(module2.get_companys().size() != 0){
	        Object[][] T_data_2 = new Object[module2.get_companys().size()][7];
	        System.out.println(module2.get_companys().size());
	        for(int i = 0; i<module1.get_companys().size();i++){
	        	T_data_2[i][0] = i;
	        	T_data_2[i][1] = module2.get_companys().get(i);
	        	T_data_2[i][2] = module2.get_modules().get(i);
	        	T_data_2[i][3] = module2.get_file_names().get(i);
	        	T_data_2[i][4] = module2.get_returns().get(i);
	        	T_data_2[i][5] = module2.get_from_dates().get(i);
	        	T_data_2[i][6] = module2.get_to_dates().get(i);
	        }
            JTable table_2 = new JTable(T_data_2 , columnTitle);
            
            aurora.add(new JScrollPane(table_2),BorderLayout.SOUTH);
        }


        
        if(module3.get_companys().size() != 0){
	        Object[][] T_data_3 = new Object[module3.get_companys().size()][7];
	        for(int i = 0; i<module1.get_companys().size();i++){
	        	T_data_3[i][0] = i;
	        	T_data_3[i][1] = module3.get_companys().get(i);
	        	T_data_3[i][2] = module3.get_modules().get(i);
	        	T_data_3[i][3] = module3.get_file_names().get(i);
	        	T_data_3[i][4] = module3.get_returns().get(i);
	        	T_data_3[i][5] = module3.get_from_dates().get(i);
	        	T_data_3[i][6] = module3.get_to_dates().get(i);
	        }
            JTable table_3 = new JTable(T_data_3 , columnTitle);
            trock.add(new JScrollPane(table_3),BorderLayout.SOUTH);
        }

        
        
        /*
        String send = module1.getData();
        String sdate = ComputeListsenr.getsDate();
        String edate = ComputeListsenr.geteDate();
        
        awesome.setLayout(layout1);
        String parts[] = send.split("\\|");
        label = new JLabel("Date: " + sdate + " - " + edate);
        System.out.println(send);
        awesome.add(new JLabel(module1.getModule()));
        awesome.add(label);
        for(int i = 0; i < parts.length; i = i + 2){
        	awesome.add(new JLabel("Company: " + parts[i]));
        	awesome.add(new JLabel("Return: " + parts[i + 1] + "%"));
        }
        
        send = module2.getData();
        aurora.setLayout(layout1);
        String parts2[] = send.split("\\|");
        label = new JLabel("Date: " + sdate + " - " + edate);
        System.out.println(send);
        aurora.add(new JLabel(module2.getModule()));
        aurora.add(label);
        for(int i = 0; i < parts2.length; i = i + 2){
        	aurora.add(new JLabel("Company: " + parts[i]));
        	aurora.add(new JLabel("Return: " + parts[i + 1] + "%"));
        }
        
        send = module3.getData();
        trock.setLayout(layout1);
        String parts3[] = send.split("\\|");
        label = new JLabel("Date: " + sdate + " - " + edate);
        System.out.println(send);
        trock.add(new JLabel(module3.getModule()));
        trock.add(label);
        for(int i = 0; i < parts3.length; i = i + 2){
        	trock.add(new JLabel("Company: " + parts[i]));
        	trock.add(new JLabel("Return: " + parts[i + 1] + "%"));
        }
        */
        tabs.addTab("Awesome", awesome);
        tabs.addTab("Aurora", aurora);
        tabs.addTab("Trock", trock);
        this.add(tabs);
	}
}
