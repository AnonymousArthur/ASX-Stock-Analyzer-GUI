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
	public DatePicker checkInDatePicker_from;
	public DatePicker checkInDatePicker_to;
	//public Stage stage;
	private final String datePattern = "dd-MMM-yyyy";
	private int window = 3;
	private double threshold = 0.001;
	private JTextField window_field = new JTextField(10);
	private JTextField threshold_field = new JTextField(10);
	
	public Arguments_form(JPanel card) {
	    String[] labels = {"Window: ", "Threshold: "};
	    int numPairs = labels.length;
	    
	    //Locale.setDefault(Locale.US);
	    
	    //stage.setTitle("Date Picker");
	    
	    VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 250, 100);
        //stage.setScene(scene);
        //Date from picker*************************************************************
        checkInDatePicker_from = new DatePicker();
        StringConverter converter_from = new StringConverter<LocalDate>() {
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
        checkInDatePicker_from.setConverter(converter_from);
        //checkInDatePicker_from.setPromptText(datePattern.toLowerCase());
        GridPane gridPane_from = new GridPane();
        gridPane_from.setHgap(10);
        gridPane_from.setVgap(10);

        Label checkInlabel_from = new Label("Start Date:");
        gridPane_from.add(checkInlabel_from, 0, 0);

        GridPane.setHalignment(checkInlabel_from, HPos.LEFT);
        gridPane_from.add(checkInDatePicker_from, 0, 1);
        
        //Date to picker**********************************************************
        checkInDatePicker_to = new DatePicker();
        StringConverter converter_to = new StringConverter<LocalDate>() {
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
        checkInDatePicker_to.setConverter(converter_to);
        //checkInDatePicker_from.setPromptText(datePattern.toLowerCase());
        GridPane gridPane_to = new GridPane();
        gridPane_to.setHgap(10);
        gridPane_to.setVgap(10);

        Label checkInlabel_to = new Label("End Date:");
        gridPane_to.add(checkInlabel_to, 1, 0);

        GridPane.setHalignment(checkInlabel_to, HPos.LEFT);
        gridPane_to.add(checkInDatePicker_to, 1, 1);
        //**********************************************
        vbox.getChildren().addAll(new Label("Start"),checkInDatePicker_from, checkInDatePicker_to);
	    
        fxPanel.setScene(scene);
        //stage.show();
	    
	    
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

	    card.add(fxPanel);
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