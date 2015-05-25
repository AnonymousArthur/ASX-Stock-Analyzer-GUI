import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

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
	private JScrollPane jsp = null;
	private int trades_size = 0;
	private JTable table;
	private Object[] columnTitle = {"Pair No.","Buy Price", "Buy Date", "Sell Price" ,"Sell Date", "Return"};
	private int column_num = 6;
	private Object[][] T_data ;
	
	public Return(String applicationTitle, String chartTitle,
			ArrayList<Trade> trades, String company) {
		this.trades = trades;
		this.company = company;
		T_data = new Object[trades.size()/2][column_num];
		
		JFreeChart jfreechart = ChartFactory.createScatterPlot(
				"Return over Transaction pairs","Pair No." , "Return", createDataset(),
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
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
		JPanel return_panel = new JPanel(new BorderLayout());
		return_panel.add(chartPanel,BorderLayout.NORTH);
		
		table = new JTable(T_data , columnTitle);
		
	    DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
	    table.getColumn("Pair No.").setCellRenderer(render);
	    table.getColumn("Buy Price").setCellRenderer(render);
	    table.getColumn("Buy Date").setCellRenderer(render);
	    table.getColumn("Sell Price").setCellRenderer(render);
	    table.getColumn("Sell Date").setCellRenderer(render);
	    table.getColumn("Return").setCellRenderer(render);
		
		return_panel.add(new JScrollPane(table),BorderLayout.SOUTH);
		jsp = new JScrollPane(return_panel);
	}

	private XYDataset createDataset() {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		XYSeries gainSeries = new XYSeries("Gain");
		XYSeries loseSeries = new XYSeries("lose");
		SimpleDateFormat fmttmp = new SimpleDateFormat("dd-MMM-yyy",
				Locale.ENGLISH);
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
			//private Object[] columnTitle = {"Pair No.","Buy Price", "Buy Date", "Sell Price" ,"Sell Date", "Return"};
			String buy = "B";
			double COI = 0;
			double ROI = 0;
			T_data[j][0] = j;
			if(trade1.signal == buy.charAt(0)){
				COI = trade1.price;
				ROI = trade2.price;
				T_data[j][1] = trade1.price;
				T_data[j][3] = trade2.price;

				String dt_buy = fmttmp.format(trade1.date);
				String dt_sell = fmttmp.format(trade2.date);
				T_data[j][2] = dt_buy;
				T_data[j][4] = dt_sell;
				
			}else{
				COI = trade2.price;
				ROI = trade1.price;
				T_data[j][1] = trade2.price;
				T_data[j][3] = trade1.price;

				String dt_buy = fmttmp.format(trade2.date);
				String dt_sell = fmttmp.format(trade1.date);
				T_data[j][2] = dt_buy;
				T_data[j][4] = dt_sell;
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
			T_data[j][5] = String.format("%.2f", ROR*100)+"%";
			
			i++;
			j++;
		}
		totalReturn = R;
		//System.out.println("Total return: " + R * 100 + "%");
		xySeriesCollection.addSeries(gainSeries);
		xySeriesCollection.addSeries(loseSeries);
		return xySeriesCollection;
	}

	public JScrollPane get_chartPanel() {
		return jsp;
	}
	
	public double getReturn(){
		return totalReturn;
	}

}
