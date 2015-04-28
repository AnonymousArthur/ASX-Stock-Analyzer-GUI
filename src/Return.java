import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Return {

	public ArrayList<Trade> trades;
	private ChartPanel chartPanel;
	private String company;

	public Return(String applicationTitle, String chartTitle,
			ArrayList<Trade> trades, String company) {
		this.trades = trades;
		this.company = company;
		JFreeChart jfreechart = ChartFactory.createScatterPlot(
				"Scatter Plot Demo 2", "X", "Y", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
		xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.green);
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setVerticalTickLabels(true);
        chartPanel = new ChartPanel(jfreechart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
	}

	private XYDataset createDataset() {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		XYSeries gainSeries = new XYSeries("Gain");
		XYSeries loseSeries = new XYSeries("lose");
		int j = 0;
		for (int i = 0; i != trades.size(); i++) {			
			if (i != 0 && trades.get(i).signal == 'S') {
				// Cost of Investment
				double COI = trades.get(i - 1).price * trades.get(i - 1).volume;
				// Return of Investment
				double ROI = trades.get(i).price * trades.get(i).volume;
				// Rate of R
				double ROR = (COI - ROI) / COI;
				//dataset.addValue(ROR, company, trades.get(i).date);
				if(ROR>0){
					gainSeries.add(j,ROR);
				}else{
					loseSeries.add(j,ROR);
				}
				j++;
			}
		}
		xySeriesCollection.addSeries(gainSeries);
		xySeriesCollection.addSeries(loseSeries);
		return xySeriesCollection;
	}

	public ChartPanel get_chartPanel() {
		return chartPanel;
	}

}
