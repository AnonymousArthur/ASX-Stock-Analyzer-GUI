import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
 
public class Return {

	public ArrayList<Trade> trades;
	private ChartPanel chartPanel;
	private String company;
	private static double highest = 0;
	private double totalReturn;
	
	public Return(String applicationTitle, String chartTitle,
			ArrayList<Trade> trades, String company) {
		this.trades = trades;
		this.company = company;
		JFreeChart jfreechart = ChartFactory.createScatterPlot(
				"Return over Transactions","Transactions" , "Return", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
		xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.green);
        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();       
        Font font = new Font("SansSerif", Font.PLAIN, 16);   
        XYTextAnnotation annotation = new XYTextAnnotation("Total Return: "+String.format("%.2f", totalReturn*100)+"%", 3, highest-0.01);   
        annotation.setFont(font);   
        annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT); 
        xyPlot.addAnnotation(annotation);   
        xyPlot.setBackgroundPaint(Color.WHITE);
		xyPlot.setRangeGridlinePaint(Color.BLACK);
        domain.setVerticalTickLabels(true);
        chartPanel = new ChartPanel(jfreechart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
	}

	private XYDataset createDataset() {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		XYSeries gainSeries = new XYSeries("Gain");
		XYSeries loseSeries = new XYSeries("lose");
		int j = 0;
		double R = 0;
		for (int i = 0; i < trades.size(); i++) {
			Trade trade1 = trades.get(i);
			Trade trade2;
			
			if ((trades.size() - 1) > (i + 1)) {
				trade2 = trades.get(i + 1);
			} else {
				break;
			}
			String buy = "B";
			double COI = 0;
			double ROI = 0;
			if(trade1.signal == buy.charAt(0)){
				COI = trade1.price;
				ROI = trade2.price;
			}else{
				COI = trade2.price;
				ROI = trade1.price;
			}
			double ROR = (ROI - COI) / COI;
			if(ROR>=0){
				gainSeries.add(j,ROR);
				if(ROR > highest){
					highest = ROR;
				}
			}else{
				loseSeries.add(j,ROR);
			}
			R = R + ROR;
			j++;
		}
		totalReturn = R;
		//System.out.println("Total return: " + R * 100 + "%");
		xySeriesCollection.addSeries(gainSeries);
		xySeriesCollection.addSeries(loseSeries);
		return xySeriesCollection;
	}

	public ChartPanel get_chartPanel() {
		return chartPanel;
	}
	
	public double getReturn(){
		return totalReturn;
	}

}
