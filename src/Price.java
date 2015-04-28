import java.util.ArrayList;
import java.util.Iterator;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

public class Price {
	/**
	 * 
	 */
	private ChartPanel chartPanel;
	private ArrayList<TradeRec> tradeRecs;
	private ArrayList<Trade> trade;

	// private String inputFile;
	public Price(String applicationTitle, String chartTitle,
			ArrayList<TradeRec> tradeRecs, ArrayList<Trade> trade) {

		this.tradeRecs = tradeRecs;
		this.trade = trade;
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Price over Time", // title
				"Date", // x-axis label
				"Price", // y-axis label
				createDataset(), // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);
		XYPlot xyplot = (XYPlot)chart.getPlot();
		xyplot.setDomainCrosshairVisible(true);
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

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

	public ChartPanel get_chartPanel() {
		return chartPanel;
	}
}