import java.util.ArrayList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Return{

	public ArrayList<Trade> trades;
	private ChartPanel chartPanel;
	private String company;
	public Return(String applicationTitle, String chartTitle, ArrayList<Trade> trades, String company) {
		this.trades = trades;
		this.company = company;		
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,
				"Years", "Return", createDataset(), PlotOrientation.VERTICAL,
				true, true, false);

		chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize( new java.awt.Dimension(800, 600) );
	}

	private DefaultCategoryDataset createDataset( )
	   {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      for(int i = 0; i != trades.size(); i++){
			   if(i != 0 && trades.get(i).signal == 'S'){
				   // Cost of Investment
				   double COI = trades.get(i - 1).price * trades.get(i - 1).volume;
				   // Return of Investment
				   double ROI = trades.get(i).price * trades.get(i).volume;
				   // Rate of R
				   double ROR = (COI - ROI)/COI;
				   dataset.addValue(ROR , company , trades.get(i).date );  
			   }
		   }
	      return dataset;
	   }
	   public ChartPanel get_chartPanel(){
		   return chartPanel; 
	   }

}
