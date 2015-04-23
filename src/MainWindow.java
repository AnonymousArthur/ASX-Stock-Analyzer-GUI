import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jfree.ui.RefineryUtilities;

public class MainWindow extends JFrame implements ItemListener, ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel cards;
    final static String MODULE1 = "Module1";
    final static String MODULE2 = "Module2";
    final static JLabel L_input_file_name = new JLabel();
	public static String inpuFilePath;
    private ArrayList<JTextField> arguments;
    
	public MainWindow (){
		this.setTitle("ALGORITHMIC TRADING");
		this.setSize(800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.arguments = new ArrayList<JTextField>();
        
		createUI(this.getContentPane());
	}
	
	public void createUI (Container pane) {
		//Put the JComboBox in a JPanel to get a nicer look.
	    JPanel comboBoxPane = new JPanel(); //use FlowLayout
	    String comboBoxItems[] = { MODULE1, MODULE2 };
	    
	    JComboBox cb = new JComboBox(comboBoxItems);
	    cb.setEditable(false);
	    cb.addItemListener(this);
	    comboBoxPane.add(cb);
	     
	    //Create the "cards".
	    JPanel card1 = new JPanel();
	    
	    JButton B_Input_Data = new JButton("Input Data");
	    JButton B_Compute = new JButton("Compute");
	    B_Compute.addActionListener(this);
	    
	    //JLabel L_input_file_name = new JLabel();
	    L_input_file_name.setText("Select file");
	    card1.add(arguments_form());
	    card1.add(B_Input_Data);
	    card1.add(L_input_file_name);
	    card1.add(B_Compute);
	    
	    B_Input_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileChooser fileChooser = new FileChooser();
					inpuFilePath = fileChooser.getPath();
					//System.out.println(inpuFilePath);
					L_input_file_name.setText(fileChooser.getName()+" loaded");
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}		
		});
	    
	    
	    JPanel card2 = new JPanel();
	    card2.add(arguments_form());
	    card2.add(new JButton("Input Data"));
	    card2.add(new JButton("Compute"));
	     
	    //Create the panel that contains the "cards".
	    cards = new JPanel(new CardLayout());
	    cards.add(card1, MODULE1);
	    cards.add(card2, MODULE2);
	     
	    pane.add(comboBoxPane, BorderLayout.PAGE_START);
	    pane.add(cards, BorderLayout.CENTER);
		}
	
	private JPanel arguments_form() {
	    String[] labels = {"Window: ", "Threshold: "};
	    int numPairs = labels.length;
	
	    //Create and populate the panel.
	    JPanel p = new JPanel(new SpringLayout());
	    for (int i = 0; i < numPairs; i++) {
	        JLabel l = new JLabel(labels[i], JLabel.TRAILING);
	        p.add(l);
	        JTextField textField = new JTextField(10);
	        this.arguments.add(textField);
	        l.setLabelFor(textField);
	        p.add(textField);
	    }
	    //Lay out the panel.
	    SpringUtilities.makeCompactGrid(p,
	                                    numPairs, 2, //rows, cols
	                                    6, 6,        //initX, initY
	                                    6, 6);       //xPad, yPad
	    return p;
	}
	
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, (String)evt.getItem()); 
	}
	//COMPUTE BUTTON PRESS
	public void actionPerformed(ActionEvent e) {
	    Toolkit.getDefaultToolkit().beep();
	    
	    
	    //MAKE THIS VARIABLE
	    //Build string to run the jar
	    String filepath = "Awesome-MSM-1.2b.jar";
	    Process proc;
	    //Add input file parameter
	    String execCommand = "java -jar " + filepath;
	    //Add filename of parameters file
	    execCommand = execCommand + " " + inpuFilePath + " parameters.txt";
	    //Create parameters.txt file
	    int window = 3;
	    double threshold = 0.001;
	    //Add window, default value is 3 if empty
	    if (!arguments.get(0).getText().isEmpty()){
	    	window = Integer.parseInt(arguments.get(0).getText());
	    }
	    //Add threshold, default value is 0.001 if empty
	    if (!arguments.get(1).getText().isEmpty()){
	    	threshold = Double.parseDouble(arguments.get(1).getText());
	    }
	    try (PrintWriter out = new PrintWriter(new BufferedWriter(
			new FileWriter("parameters.txt", true)))) {
			out.println("window = " + window);
			out.println("threshold = " + threshold);
			out.println("output = summary.cvs");
		} catch (IOException e1) {
		}
	    //System.out.println(execCommand);
	    //Run module
		try {
			proc = Runtime.getRuntime().exec(execCommand);
		    proc.waitFor();
		    InputStream in = proc.getInputStream();
		    InputStream err = proc.getErrorStream();

		    byte b[]=new byte[in.available()];
		    in.read(b,0,b.length);
		    //System.out.println(new String(b));

		    byte c[]=new byte[err.available()];
		    err.read(c,0,c.length);
		    //System.out.println(new String(c));
		    //System.out.println("DONE");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//create graph
		 Chart chart = new Chart(
			      "Price" ,
			      "Price over Time",
			      inpuFilePath);

		 chart.pack( );
		 RefineryUtilities.centerFrameOnScreen( chart );
		 chart.setVisible( true );
	}
}

