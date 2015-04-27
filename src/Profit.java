import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Profit extends ApplicationFrame
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COMPANY = 0;
	private static final int DATE = 1;
	private static final int PRICE = 2;
	private static final int VOLUME = 3;
	private static final int VALUE = 4;
	private static final int SIGNAL = 5;
	private String inputFile;
public Profit( String applicationTitle , String chartTitle, String inputFile )
   {
      super(applicationTitle);
      //Set input file
      this.inputFile = inputFile;
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Profit",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( )
   {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      BufferedReader br = null;
	   String line = "";
	   String cvsSplitBy = ",";
	   Double profit = 0.0;
	   try {
			br = new BufferedReader(new FileReader(inputFile));
			int i = 0;
			while ((line = br.readLine()) != null ) {
				// use comma as separator
				String[] trade = line.split(cvsSplitBy);
				//
				if(!trade[0].equals("#RIC") && !trade[VALUE].isEmpty()){ 
					if(trade[SIGNAL].equals("S")){
						profit += Double.parseDouble(trade[VALUE]);
					} else if (trade[SIGNAL].equals("b")){
						profit += Double.parseDouble(trade[VALUE]);
					}
					dataset.addValue(profit , trade[COMPANY] , trade[DATE] );    
					//System.out.println(trade[8]);
					i++;
				}
			}
			System.out.println(profit);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
      return dataset;
   }
}