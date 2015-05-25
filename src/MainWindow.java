import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

import javafx.stage.Stage;

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
	public static String inpuFilePath;
	private static final long serialVersionUID = 1L;
	private static JPanel cards = null;
    final static String MODULE1 = "Awesome MSM";
    final static String MODULE2 = "Aurora";
    final static String MODULE3 = "Trock";
    final static JLabel L_input_file_name_card1 = new JLabel();
    final static JLabel L_input_file_name_card2 = new JLabel();
    final static JLabel L_input_file_name_card3 = new JLabel();
    
	//arguments for module1
	final static JPanel card1 = new JPanel();
	private Arguments_form arguments_form_module1;
	private Arguments_form arguments_form_module2;
	private Arguments_form arguments_form_module3;
	private ComputeListsenr compute_listsener_module2;
	private ComputeListsenr compute_listsener_module3;
	public static ComputeListsenr compute_listsener_module1;
	final JTabbedPane jtp_module1_companies = new JTabbedPane();
	final JTabbedPane jtp_module2_companies = new JTabbedPane();
	final JTabbedPane jtp_module3_companies = new JTabbedPane();
    public static String module;
    public static String filename;
	
	//arguments for module2
	final static JPanel card2 = new JPanel();
	final static JPanel card3 = new JPanel();
	
	public MainWindow (){
		this.setTitle("ALGORITHMIC TRADING");
		this.setSize(1100, 760);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		createUI(this.getContentPane());
	}
	
	public void createUI (Container pane) {
		//Put the JComboBox in a JPanel to get a nicer look.
	    JPanel comboBoxPane = new JPanel(); //use FlowLayout
	    String comboBoxItems[] = { MODULE1, MODULE2,MODULE3 };
	    
	    JComboBox cb = new JComboBox(comboBoxItems);
	    cb.setEditable(false);
	    cb.addItemListener(this);
	    comboBoxPane.add(cb);
	     
	    //Create the "cards".
	    
	    JButton B_Input_Data = new JButton("Input Data");
	    JButton B_Compute = new JButton("Compute");
	    /*
	    
	    Awesome
	    
	    */

	    arguments_form_module1 = new Arguments_form(card1);
	    
	    //JLabel L_input_file_name = new JLabel();
	    L_input_file_name_card1.setText("Select file");
	    card1.add(arguments_form_module1.get_Panel());
	    card1.add(B_Input_Data);
	    card1.add(L_input_file_name_card1);
	    card1.add(B_Compute);
	    
	    B_Input_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FilePicker fileChooser = new FilePicker();
					inpuFilePath = fileChooser.getPath();				
					filename = fileChooser.getName();
					//L_input_file_name_card1.setText(fileChooser.getName()+" loaded");
					compute_listsener_module1.setFileName(filename);
					compute_listsener_module1.setFilePath(inpuFilePath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}		
		});
	    compute_listsener_module1 = new ComputeListsenr( arguments_form_module1, jtp_module1_companies, "Awesome-MSM-2.0.0.jar");
	    B_Compute.addActionListener(compute_listsener_module1.returnListener());
	    
	    
	    
	    /*
	    
	    Aurora
	    
	    */
	    JPanel card2 = new JPanel();
	    L_input_file_name_card2.setText("Select file");
	    arguments_form_module2 = new Arguments_form(card2);
	    JButton C_Input_Data = new JButton("Input CSV");
	    JButton C_Compute = new JButton("Compute");
	    
	    C_Input_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FilePicker fileChooser = new FilePicker();
					inpuFilePath = fileChooser.getPath();
					filename = fileChooser.getName();
					//L_input_file_name_card2.setText(fileChooser.getName()+" loaded");
					compute_listsener_module2.setFileName(filename);
					compute_listsener_module2.setFilePath(inpuFilePath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}		
		});

	    compute_listsener_module2 = new ComputeListsenr(arguments_form_module2, jtp_module2_companies, "aurora.jar");
	    C_Compute.addActionListener(compute_listsener_module2.returnListener());
	    card2.add(arguments_form_module2.get_Panel());
	    card2.add(C_Input_Data);
	    card2.add(L_input_file_name_card2);
	    card2.add(C_Compute);
	    
	    /*
	    
	   	Trock
	    
	    */
	    
	    JPanel card3 = new JPanel();
	    L_input_file_name_card3.setText("Select file");
	    arguments_form_module3 = new Arguments_form(card3);
	    JButton D_Input_Data = new JButton("Input CSV");
	    JButton D_Compute = new JButton("Compute");
	    
	    D_Input_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FilePicker fileChooser = new FilePicker();
					inpuFilePath = fileChooser.getPath();
					filename = fileChooser.getName();
					//L_input_file_name_card3.setText(fileChooser.getName()+" loaded");
					compute_listsener_module3.setFileName(filename);
					compute_listsener_module3.setFilePath(inpuFilePath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}		
		});

	    compute_listsener_module3 = new ComputeListsenr(arguments_form_module3, jtp_module3_companies, "trockAT");
	    D_Compute.addActionListener(compute_listsener_module3.returnListener());
	    card3.add(arguments_form_module3.get_Panel());
	    card3.add(D_Input_Data);
	    card3.add(L_input_file_name_card3);
	    card3.add(D_Compute);
	    
	    
	    
	    
	    //Create the panel that contains the "cards".
	    cards = new JPanel(new CardLayout());
	    cards.add(card1, MODULE1);
	    cards.add(card2, MODULE2);
	    cards.add(card3, MODULE3);
	    
	    jtp_module1_companies.setPreferredSize(new Dimension( 1100, 600 ));
	    jtp_module2_companies.setPreferredSize(new Dimension( 1100, 600 ));
	    jtp_module3_companies.setPreferredSize(new Dimension( 1100, 600 ));
	    
		card1.add(jtp_module1_companies, BorderLayout.CENTER);
		card2.add(jtp_module2_companies, BorderLayout.CENTER);
		card3.add(jtp_module3_companies, BorderLayout.CENTER);

	    
	    pane.add(comboBoxPane, BorderLayout.PAGE_START);
	    pane.add(cards, BorderLayout.CENTER);
		}
	

	
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, (String)evt.getItem()); 
	}

}
