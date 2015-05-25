import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

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
	        	T_data_1[i][4] = module1.get_returns().get(i)+"%";
	        	T_data_1[i][5] = module1.get_from_dates().get(i);
	        	T_data_1[i][6] = module1.get_to_dates().get(i);
	        }

	        JTable table_1 = new JTable(T_data_1 , columnTitle);
	        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table_1.getColumn("Record No.").setCellRenderer(render);
		    table_1.getColumn("Company Name").setCellRenderer(render);
		    table_1.getColumn("Module Name").setCellRenderer(render);
		    table_1.getColumn("Data Used").setCellRenderer(render);
		    table_1.getColumn("Total Return").setCellRenderer(render);
		    table_1.getColumn("From Date").setCellRenderer(render);
		    table_1.getColumn("To Date").setCellRenderer(render);
	        table_1.setPreferredScrollableViewportSize(new Dimension(680,500));
	        awesome.add(new JScrollPane(table_1),BorderLayout.SOUTH);
        }

        
        if(module2.get_companys().size() != 0){
	        Object[][] T_data_2 = new Object[module2.get_companys().size()][7];
	        //System.out.println(module2.get_companys().size());
	        for(int i = 0; i<module2.get_companys().size();i++){
	        	T_data_2[i][0] = i;
	        	T_data_2[i][1] = module2.get_companys().get(i);
	        	T_data_2[i][2] = module2.get_modules().get(i);
	        	T_data_2[i][3] = module2.get_file_names().get(i);
	        	T_data_2[i][4] = module2.get_returns().get(i)+"%";
	        	T_data_2[i][5] = module2.get_from_dates().get(i);
	        	T_data_2[i][6] = module2.get_to_dates().get(i);
	        }
            JTable table_2 = new JTable(T_data_2 , columnTitle);
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    	    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table_2.getColumn("Record No.").setCellRenderer(render);
		    table_2.getColumn("Company Name").setCellRenderer(render);
		    table_2.getColumn("Module Name").setCellRenderer(render);
		    table_2.getColumn("Data Used").setCellRenderer(render);
		    table_2.getColumn("Total Return").setCellRenderer(render);
		    table_2.getColumn("From Date").setCellRenderer(render);
		    table_2.getColumn("To Date").setCellRenderer(render);
            table_2.setPreferredScrollableViewportSize(new Dimension(680,500));
            aurora.add(new JScrollPane(table_2),BorderLayout.SOUTH);
        }


        
        if(module3.get_companys().size() != 0){
	        Object[][] T_data_3 = new Object[module3.get_companys().size()][7];
	        for(int i = 0; i<module3.get_companys().size();i++){
	        	T_data_3[i][0] = i;
	        	T_data_3[i][1] = module3.get_companys().get(i);
	        	T_data_3[i][2] = module3.get_modules().get(i);
	        	T_data_3[i][3] = module3.get_file_names().get(i);
	        	T_data_3[i][4] = module3.get_returns().get(i)+"%";
	        	T_data_3[i][5] = module3.get_from_dates().get(i);
	        	T_data_3[i][6] = module3.get_to_dates().get(i);
	        }
            JTable table_3 = new JTable(T_data_3 , columnTitle);
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
    	    render.setHorizontalAlignment(SwingConstants.CENTER);
		    table_3.getColumn("Record No.").setCellRenderer(render);
		    table_3.getColumn("Company Name").setCellRenderer(render);
		    table_3.getColumn("Module Name").setCellRenderer(render);
		    table_3.getColumn("Data Used").setCellRenderer(render);
		    table_3.getColumn("Total Return").setCellRenderer(render);
		    table_3.getColumn("From Date").setCellRenderer(render);
		    table_3.getColumn("To Date").setCellRenderer(render);
            table_3.setPreferredScrollableViewportSize(new Dimension(680,500));
            trock.add(new JScrollPane(table_3),BorderLayout.SOUTH);
        }

        tabs.addTab("Awesome", awesome);
        tabs.addTab("Aurora", aurora);
        tabs.addTab("Trock", trock);
        this.add(tabs);
	}
}
