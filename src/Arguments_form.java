import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.util.StringConverter;
import javafx.util.Callback;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class Arguments_form {
	JPanel p; 
	JFXPanel fxPanel = new JFXPanel();
	public DatePicker startDatePicker;
	public DatePicker endDatePicker;
	//public Stage stage;
	private final String datePattern = "dd-MMM-yyyy";
	private final String placeHolder = "Optional";
	private int window = 3;
	private double threshold = 0.001;
	private JTextField window_field = new JTextField(10);
	private JTextField threshold_field = new JTextField(10);
	
	public Arguments_form(JPanel card) {
	    String[] labels = {"Window: ", "Threshold: "};
	    int numPairs = labels.length;
	    VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 310, 80);              
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

        
        startDatePicker = new DatePicker();
        startDatePicker.setConverter(converter);
        startDatePicker.setPromptText(placeHolder);
        endDatePicker = new DatePicker();        
        endDatePicker.setConverter(converter);
        endDatePicker.setPromptText(placeHolder);
        final Callback<DatePicker, DateCell> dayCellFactory = 
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if(startDatePicker.getValue() != null)
                                if (item.isBefore(                              
                                        startDatePicker.getValue().plusDays(1))
                                    ) {
                                        setDisable(true);
                                        setStyle("-fx-background-color: #ffc0cb;");
                                }   
                        }
                    };
                }
            };
        endDatePicker.setDayCellFactory(dayCellFactory);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label startlabel= new Label("Start Date:");
        gridPane.add(startlabel, 0, 0);
        GridPane.setHalignment(startlabel, HPos.LEFT);
        gridPane.add(startDatePicker, 2, 0);
        Label endlabel = new Label("End Date:");
        gridPane.add(endlabel, 0, 1);
        GridPane.setHalignment(endlabel, HPos.LEFT);
        gridPane.add(endDatePicker, 2, 1);

        vbox.getChildren().add(gridPane);
        fxPanel.setScene(scene);
	    
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