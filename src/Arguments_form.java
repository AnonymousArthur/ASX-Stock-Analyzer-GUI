import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class Arguments_form {
	JPanel p; 
	JFXPanel fxPanel = new JFXPanel();
	public DatePicker checkInDatePicker;
	//public Stage stage;
	private final String datePattern = "dd-MMM-yyyy";
	private int window = 3;
	private double threshold = 0.001;
	private JTextField window_field = new JTextField(10);
	private JTextField threshold_field = new JTextField(10);
	
	public Arguments_form() {
	    String[] labels = {"Window: ", "Threshold: "};
	    int numPairs = labels.length;
	    
	    //Locale.setDefault(Locale.US);
	    
	    //stage.setTitle("Date Picker");
	    
	    VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 100, 200);
        //stage.setScene(scene);
        
        checkInDatePicker = new DatePicker();
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
                DateTimeFormatter.ofPattern(datePattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };             
        checkInDatePicker.setConverter(converter);
        //checkInDatePicker.setPromptText(datePattern.toLowerCase());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Start Date:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        
        vbox.getChildren().add(checkInDatePicker);
	    
        fxPanel.setScene(scene);
        //stage.show();
	    
	    
	    //Create and populate the panel.
	    p = new JPanel(new SpringLayout());
	    p.add(fxPanel);
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