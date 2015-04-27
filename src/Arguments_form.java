import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class Arguments_form {
	JPanel p;
	private int window = 3;
	private double threshold = 0.001;
	private JTextField window_field = new JTextField(10);
	private JTextField threshold_field = new JTextField(10);
	
	public Arguments_form() {
	    String[] labels = {"Window: ", "Threshold: "};
	    int numPairs = labels.length;
	    
	    //Create and populate the panel.
	    p = new JPanel(new SpringLayout());
	    
	    JLabel window_label = new JLabel("Window: ", JLabel.TRAILING);
	    p.add(window_label);
	    window_label.setLabelFor(window_field);
	    p.add(window_field);
	    
	    JLabel threshold_label = new JLabel("Threshold: ", JLabel.TRAILING);
	    p.add(threshold_label);
	    threshold_label.setLabelFor(threshold_field);
	    p.add(threshold_field);

	    //Lay out the panel.
	    SpringUtilities.makeCompactGrid(p,
	                                    numPairs, 2, //rows, cols
	                                    6, 6,        //initX, initY
	                                    6, 6);       //xPad, yPad
	}
	public JPanel get_Panel (){
		return p;
	}
	public int getWindow(){
		window = Integer.parseInt(window_field.getText());
		return window;
	}
	public double getThreshold(){
		threshold = Double.parseDouble(threshold_field.getText());
		return threshold;
	}
}