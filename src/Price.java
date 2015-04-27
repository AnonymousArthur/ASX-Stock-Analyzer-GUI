import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Price
{
   /**
	 * 
	 */
	private ChartPanel chartPanel ;
	private static ArrayList<TradeRec> tradeRecs;
	private String inputFile;
	public Price( String applicationTitle , String chartTitle, String inputFile ){
      
      //Set input file
      this.inputFile = inputFile;
      tradeRecs = CSVParser.CSVParse(inputFile);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Price",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
     chartPanel = new ChartPanel( lineChart );
     chartPanel.setPreferredSize( new java.awt.Dimension(800, 600) );
      
   }

   private DefaultCategoryDataset createDataset( )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	   for (Iterator<TradeRec> iterator = tradeRecs.iterator(); iterator.hasNext();) {
		   TradeRec trade = iterator.next();
		   if(trade.last != 0){
			   dataset.addValue(trade.last, trade.ric, trade.date);
		   }
	   }
      return dataset;
   }
   public ChartPanel get_chartPanel(){
	   return chartPanel; 
   }
}