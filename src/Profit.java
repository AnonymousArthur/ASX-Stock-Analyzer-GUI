import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.util.DefaultShadowGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
 
public class Profit {
	private ChartPanel chartPanel;
	private ArrayList<Trade> trades;
	private String company;

	public Profit(String applicationTitle, String chartTitle,
			ArrayList<Trade> trade, String company) {
		this.trades = trade;
		this.company = company;

		/*JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,
				"Years", "Profit", createDataset(), PlotOrientation.VERTICAL,
				true, true, false);*/
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
		xyplot.setBackgroundPaint(Color.WHITE);
		xyplot.setRangeGridlinePaint(Color.BLACK);
		xyplot.setShadowGenerator(new DefaultShadowGenerator());
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

	}

	private XYDataset createDataset() {
		/*DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double profit = 0.0;
		for (int i = 0; i != trades.size(); i++) {
			if (i != 0 && trades.get(i).signal == 'S') {
				Double investment = trades.get(i - 1).price
						* trades.get(i - 1).volume;
				Double ret = trades.get(i).price * trades.get(i).volume;
				profit = profit + (ret - investment);
				dataset.addValue(profit, company, trades.get(i).date);
			}
		}*/
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries s1 = new TimeSeries("Company");
		Double profit = 0.0;
		for (int i = 0; i != trades.size(); i++) {
			if (i != 0 && trades.get(i).signal == 'S') {
				Double investment = trades.get(i - 1).price
						* trades.get(i - 1).volume;
				Double ret = trades.get(i).price * trades.get(i).volume;
				profit = profit + (ret - investment);
				s1.addOrUpdate(new Day(trades.get(i).date),profit);
			}
		}
		dataset.addSeries(s1);
		return dataset;
	}

	public ChartPanel get_chartPanel() {
		return chartPanel;
	}
}