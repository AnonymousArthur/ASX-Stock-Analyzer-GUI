import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Overview extends JFrame{

	final static JPanel everything = new JPanel();
	GridLayout layout1 = new GridLayout(10,2);
	
	public Overview(ComputeListsenr module1){
		this.setTitle("Overview");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	void display() {		
		this.pack();
		this.setSize(500, 500);
        this.setVisible(true);
        JLabel label;
        String send = ComputeListsenr.getData();
        String sdate = ComputeListsenr.getsDate();
        String edate = ComputeListsenr.geteDate();
        everything.setLayout(layout1);
        String parts[] = send.split("\\|");
        label = new JLabel("Date: " + sdate + " - " + edate);
        System.out.println(send);
        everything.add(label);
        for(int i = 0; i < parts.length; i = i + 2){
        	everything.add(new JLabel("Company: " + parts[i]));
        	everything.add(new JLabel("Return: " + parts[i + 1] + "%"));
        }
        this.add(everything);
	}
}
