import java.util.ArrayList;
import java.util.Iterator;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        fxPanel.setScene(scene);
        p.add(fxPanel);
        
		//XYPlot xyplot = (XYPlot)chart.getPlot();
		//xyplot.setDomainCrosshairVisible(true);
		
		//p.setPreferredSize(new java.awt.Dimension(800, 600));

	}

	private XYDataset createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();	
		TimeSeries s1 = new TimeSeries("Company");
		for (Iterator<TradeRec> iterator = tradeRecs.iterator(); iterator
				.hasNext();) {
			TradeRec trade = iterator.next();
			if (trade.last != 0) {
				s1.addOrUpdate(new Day(trade.date),trade.last);				
			}
		}
		dataset.addSeries(s1);
		return dataset;
	}

	public JPanel get_p() {
		return p;
	}
}