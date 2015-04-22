import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import jdk.internal.dynalink.beans.StaticClass;

public class MainWindow extends JFrame implements ItemListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel cards;
    final static String MODULE1 = "Module1";
    final static String MODULE2 = "Module2";
    final static JLabel L_input_file_name = new JLabel();
    public static String inpuFilePath;
	public MainWindow (){
		this.setTitle("ALGORITHMIC TRADING");
		this.setSize(800, 700);
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
    JPanel card1 = new JPanel();
    
    JButton B_Input_Data = new JButton("Input Data");
    JButton B_Compute = new JButton("Compute");
    
    //JLabel L_input_file_name = new JLabel();
    L_input_file_name.setText("FILE_NAME");
    card1.add(arguments_form());
    card1.add(B_Input_Data);
    card1.add(L_input_file_name);
    card1.add(B_Compute);
    
    B_Input_Data.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				FileChooser fileChooser = new FileChooser();
				inpuFilePath = fileChooser.getPath();
				System.out.println(inpuFilePath);
				L_input_file_name.setText(fileChooser.getName());
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

private static JPanel arguments_form() {
    String[] labels = {"Window: ", "Threshold: "};
    int numPairs = labels.length;

    //Create and populate the panel.
    JPanel p = new JPanel(new SpringLayout());
    for (int i = 0; i < numPairs; i++) {
        JLabel l = new JLabel(labels[i], JLabel.TRAILING);
        p.add(l);
        JTextField textField = new JTextField(10);
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

@Override
public void itemStateChanged(ItemEvent evt) {
	CardLayout cl = (CardLayout)(cards.getLayout());
    cl.show(cards, (String)evt.getItem()); 
}

	
}
