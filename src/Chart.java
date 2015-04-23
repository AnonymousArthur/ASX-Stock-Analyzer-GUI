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

public class Chart extends ApplicationFrame
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COMPANY = 0;
	private static final int DATE = 1;
	private static final int PRICE = 8;
	
	private String inputFile;
public Chart( String applicationTitle , String chartTitle, String inputFile )
   {
      super(applicationTitle);
      //Set input file
      this.inputFile = inputFile;
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Price",
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

	   try {
			br = new BufferedReader(new FileReader(inputFile));
			int i = 0;
			while ((line = br.readLine()) != null ) {
				// use comma as separator
				String[] trade = line.split(cvsSplitBy);
				//
				if(!trade[0].equals("#RIC") && !trade[8].isEmpty()){
					//Adds data
					//Value, category, x-value
					dataset.addValue(Double.parseDouble(trade[PRICE]) , trade[COMPANY] , trade[DATE] );    
					System.out.println(trade[8]);
					i++;
				}
			}
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