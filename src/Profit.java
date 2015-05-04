import java.util.ArrayList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
 
public class Profit {
	private ChartPanel chartPanel;
	private ArrayList<Trade> trades;
	private String company;

	public Profit(String applicationTitle, String chartTitle,
			ArrayList<Trade> trade, String company) {
		this.trades = trade;
		this.company = company;

		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,
				"Years", "Profit", createDataset(), PlotOrientation.VERTICAL,
				true, true, false);

		chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

	}

	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Double profit = 0.0;
		for (int i = 0; i != trades.size(); i++) {
			if (i != 0 && trades.get(i).signal == 'S') {
				Double investment = trades.get(i - 1).price
						* trades.get(i - 1).volume;
				Double ret = trades.get(i).price * trades.get(i).volume;
				profit = profit + (ret - investment);
				dataset.addValue(profit, company, trades.get(i).date);
			}
		}
		return dataset;
	}

	public ChartPanel get_chartPanel() {
		return chartPanel;
	}
}