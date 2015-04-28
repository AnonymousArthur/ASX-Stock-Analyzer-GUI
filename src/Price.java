import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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

		// Set input file
		// this.inputFile = inputFile;
		// tradeRecs = CSVParser.CSVParse(inputFile);
		this.tradeRecs = tradeRecs;
		this.trade = trade;
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,
				"Years", "Price", createDataset(), PlotOrientation.VERTICAL,
				true, true, false);

		chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

	}

	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Iterator<TradeRec> iterator = tradeRecs.iterator(); iterator
				.hasNext();) {
			TradeRec trade = iterator.next();
			if (trade.last != 0) {
				dataset.addValue(trade.last, trade.ric, trade.date);
			}
		}
		return dataset;
	}

	public ChartPanel get_chartPanel() {
		return chartPanel;
	}
}