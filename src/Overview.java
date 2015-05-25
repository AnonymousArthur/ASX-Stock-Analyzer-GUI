import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Overview extends JFrame{

	final static JPanel awesome = new JPanel();
	final static JPanel aurora = new JPanel();
	final static JPanel trock = new JPanel();

	GridLayout layout1 = new GridLayout(10,2);
	ComputeListsenr module1;
	ComputeListsenr module2;
	ComputeListsenr module3;
	JTabbedPane tabs = new JTabbedPane();

	
	public Overview(ComputeListsenr module1, ComputeListsenr module2, ComputeListsenr module3){
		this.setTitle("Overview");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.module1 = module1;
		this.module2 = module2;
		this.module3 = module3;

	}
	
	void display() {		
		this.pack();
		this.setSize(500, 500);
        this.setVisible(true);
        JLabel label;
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
        tabs.addTab("Awesome", awesome);
        tabs.addTab("Aurora", aurora);
        tabs.addTab("Trock", trock);
        this.add(tabs);
	}
}
