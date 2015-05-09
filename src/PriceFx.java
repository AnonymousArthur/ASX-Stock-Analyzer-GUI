import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.VBox;

import javax.swing.JPanel;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
 
public class PriceFx {
	/**
	 * 
	 */
	private ArrayList<TradeRec> tradeRecs;
	private ArrayList<Trade> trade;
	JPanel p; 
	JFXPanel fxPanel = new JFXPanel();
	
	
	// private String inputFile;
	public PriceFx(String applicationTitle, String chartTitle,
			ArrayList<TradeRec> tradeRecs, ArrayList<Trade> trade) {
		this.tradeRecs = tradeRecs;
		this.trade = trade;
		/*JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Price over Time", // title
				"Date", // x-axis label
				"Price", // y-axis label
				createDataset(), // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);*/
		p = new JPanel();
		//VBox vbox = new VBox(20);
        //vbox.setStyle("-fx-padding: 10;");
        //Scene scene = new Scene(vbox, 800, 600);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
       
        lineChart.setCreateSymbols(false);
        Scene scene  = new Scene(lineChart,700,450);
        lineChart.getData().add(createDataset());
        fxPanel.setScene(scene);
        p.add(fxPanel);
		//XYPlot xyplot = (XYPlot)chart.getPlot();
		//xyplot.setDomainCrosshairVisible(true);
		
		p.setPreferredSize(new java.awt.Dimension(800, 600));

	}

	private Series createDataset() {
		/*TimeSeriesCollection dataset = new TimeSeriesCollection();	
		TimeSeries s1 = new TimeSeries("Company");
		for (Iterator<TradeRec> iterator = tradeRecs.iterator(); iterator
				.hasNext();) {
			TradeRec trade = iterator.next();
			if (trade.last != 0) {
				s1.addOrUpdate(new Day(trade.date),trade.last);				
			}
		}
		dataset.addSeries(s1);*/
		XYChart.Series series = new XYChart.Series();
	    series.setName("My portfolio");
	    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
	    for (Iterator<TradeRec> iterator = tradeRecs.iterator(); iterator
		.hasNext();) {
		TradeRec trade = iterator.next();
		if (trade.last != 0) {
			//s1.addOrUpdate(new Day(trade.date),trade.last);		
			 series.getData().add(new XYChart.Data(df.format(trade.date), trade.last));	
			}
	    }
	        //populating the series with data
	             
		return series;
	}

	public JPanel get_p() {
		return p;
	}
}