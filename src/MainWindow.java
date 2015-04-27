import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jfree.ui.RefineryUtilities;

public class MainWindow extends JFrame implements ItemListener{
    /**
	 * 
	 */
	private String filename;
	public static String inpuFilePath;
	private static final long serialVersionUID = 1L;
	private static JPanel cards = null;
    final static String MODULE1 = "Module1";
    final static String MODULE2 = "Module2";
    final static JLabel L_input_file_name = new JLabel();
	//arguments for module1
	final static JPanel card1 = new JPanel();
	private Arguments_form arguments_form_module1;
	private ComputeListsenr compute_listsener_module1;
	final JTabbedPane jtp_module1 = new JTabbedPane();
	
	//arguments for module2
	final static JPanel card2 = new JPanel();
	
	public MainWindow (){
		this.setTitle("ALGORITHMIC TRADING");
		this.setSize(900, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
	    
	    JButton B_Input_Data = new JButton("Input Data");
	    JButton B_Compute = new JButton("Compute");
	    

	    arguments_form_module1 = new Arguments_form();
	    

	    
	    //JLabel L_input_file_name = new JLabel();
	    L_input_file_name.setText("Select file");
	    card1.add(arguments_form_module1.get_Panel());
	    card1.add(B_Input_Data);
	    card1.add(L_input_file_name);
	    card1.add(B_Compute);
	    
	    B_Input_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileChooser fileChooser = new FileChooser();
					inpuFilePath = fileChooser.getPath();
					filename = fileChooser.getName();
					L_input_file_name.setText(fileChooser.getName()+" loaded");
					compute_listsener_module1.setFileName(filename);
					compute_listsener_module1.setFilePath(inpuFilePath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}		
		});
	    compute_listsener_module1 = new ComputeListsenr( arguments_form_module1, card1, jtp_module1);
	    B_Compute.addActionListener(compute_listsener_module1.returnListener());
	    
	    
	    JPanel card2 = new JPanel();
	    card2.add(new JButton("Input Data"));
	    card2.add(new JButton("Compute"));
	     
	    //Create the panel that contains the "cards".
	    cards = new JPanel(new CardLayout());
	    cards.add(card1, MODULE1);
	    cards.add(card2, MODULE2);
	     
	    pane.add(comboBoxPane, BorderLayout.PAGE_START);
	    pane.add(cards, BorderLayout.CENTER);
		}
	

	
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, (String)evt.getItem()); 
	}

}

