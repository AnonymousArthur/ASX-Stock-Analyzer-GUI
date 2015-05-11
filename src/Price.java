import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.jfree.data.Range;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.DateCellRenderer;
import org.jfree.ui.NumberCellRenderer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.util.DefaultShadowGenerator;

//import demo.CrosshairDemo1.DemoTableModel;

public class Price implements ChangeListener, ChartProgressListener {
	/**
	 * 
	 */
	private ChartPanel chartPanel;
	private ArrayList<TradeRec> tradeRecs;
	private ArrayList<Trade> trade;
	private JSlider slider;
	private JFreeChart chart;
	private TimeSeries series;
	private DemoTableModel model;
	private JPanel jpanel;
	private JPanel panel;
	private int days = 0;
	private String companyName;
	private Date startDate;
	private Date endDate;

	// private String inputFile;
	public Price(String applicationTitle, String chartTitle,
			ArrayList<TradeRec> tradeRecs, ArrayList<Trade> trade,
			Date startDate, Date endDate) throws ParseException {
		panel = new JPanel();
		// panel.setPreferredSize(new java.awt.Dimension(1500,1000));

		this.tradeRecs = tradeRecs;
		companyName = tradeRecs.get(0).getRic();
		this.trade = trade;
		SimpleDateFormat fmttmp = new SimpleDateFormat("dd-MMM-yyy",
				Locale.ENGLISH);

		this.startDate = fmttmp.parse("01-JAN-1000");
		this.endDate = fmttmp.parse("01-JAN-3000");
		
		if (startDate != null)
			this.startDate = startDate;
		if (endDate != null)
			this.endDate = endDate;

		chart = ChartFactory.createTimeSeriesChart("Price over Time", // title
				"Date", // x-axis label
				"Price", // y-axis label
				createDataset(), // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);
		chart.addProgressListener(this);
		XYPlot xyplot = (XYPlot) chart.getPlot();
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setBackgroundPaint(Color.WHITE);
		xyplot.setRangeGridlinePaint(Color.BLACK);
		xyplot.setShadowGenerator(new DefaultShadowGenerator());
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 400));
		panel.add(chartPanel);

		model = new DemoTableModel(3);
		model.setValueAt(xyplot.getDataset().getSeriesKey(0), 0, 0);
		model.setValueAt(new Double("0.0"), 0, 1);
		model.setValueAt(new Double("0.00"), 0, 2);
		JTable jtable = new JTable(model);
		DateCellRenderer datecellrenderer = new DateCellRenderer(
				new SimpleDateFormat("dd-MMM-yyyy"));
		NumberCellRenderer numbercellrenderer = new NumberCellRenderer();
		jtable.getColumnModel().getColumn(1).setCellRenderer(datecellrenderer);
		jtable.getColumnModel().getColumn(2)
				.setCellRenderer(numbercellrenderer);
		jpanel = new JPanel(new BorderLayout());
		jpanel.setPreferredSize(new Dimension(1000, 80));
		jpanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		jpanel.add(new JScrollPane(jtable));
		slider = new JSlider(0, days, 50);
		slider.addChangeListener(this);
		jpanel.add(slider, "South");
		panel.add(jpanel, "South");

	}

	private XYDataset createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		series = new TimeSeries(companyName);
		double lastDay = 0;
		for (Iterator<TradeRec> iterator = tradeRecs.iterator(); iterator
				.hasNext();) {
			TradeRec trade = iterator.next();
			if (trade.date.equals(startDate)||trade.date.equals(endDate)||trade.date.after(startDate) && trade.date.before(endDate)) {
				if (trade.last != 0) {
					series.addOrUpdate(new Day(trade.date), trade.last);
					days++;
					lastDay = trade.last;
				} else {
					if (days != 0) {
						series.addOrUpdate(new Day(trade.date), lastDay);
						days++;
					}
				}
			}
		}
		dataset.addSeries(series);
		return dataset;
	}

	public JPanel get_chartPanel() {
		return panel;
	}

	@Override
	public void chartProgress(ChartProgressEvent chartprogressevent) {
		if (chartprogressevent.getType() != 2)
			return;
		if (chartPanel != null) {
			JFreeChart jfreechart = chartPanel.getChart();
			if (jfreechart != null) {
				XYPlot xyplot = (XYPlot) jfreechart.getPlot();
				XYDataset xydataset = xyplot.getDataset();
				Comparable comparable = xydataset.getSeriesKey(0);
				double d = xyplot.getDomainCrosshairValue();
				model.setValueAt(comparable, 0, 0);
				long l = (long) d;
				// System.out.println(1);
				model.setValueAt(new Long(l), 0, 1);
				//System.out.println(new Date(l));
				int i = series.getIndex(new Day(new Date(l)));
				if (i >= 0) {
					TimeSeriesDataItem timeseriesdataitem = series
							.getDataItem(Math.max(0, i));
					// TimeSeriesDataItem timeseriesdataitem1 =
					// series.getDataItem(Math.max(0, i - 1));
					long l1 = timeseriesdataitem.getPeriod()
							.getMiddleMillisecond();
					double d1 = timeseriesdataitem.getValue().doubleValue();
					//System.out.println(d1);
					model.setValueAt(new Long(l1), 0, 1);
					model.setValueAt(new Double(d1), 0, 2);

				}
			}
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int i = slider.getValue();
		XYPlot xyplot = (XYPlot) chart.getPlot();
		ValueAxis valueaxis = xyplot.getDomainAxis();
		Range range = valueaxis.getRange();
		double d = valueaxis.getLowerBound() + ((double) i / days)
				* range.getLength();
		xyplot.setDomainCrosshairValue(d);
	}

	static class DemoTableModel extends AbstractTableModel implements
			TableModel {

		private Object data[][];

		public int getColumnCount() {
			return 3;
		}

		public int getRowCount() {
			return 1;
		}

		public Object getValueAt(int i, int j) {
			return data[i][j];
		}

		public void setValueAt(Object obj, int i, int j) {
			data[i][j] = obj;
			fireTableDataChanged();
		}

		public String getColumnName(int i) {
			switch (i) {
			case 0: // '\0'
				return "Company Name";

			case 1: // '\001'
				return "Date";

			case 2: // '\002'
				return "Last Price";

			}
			return null;
		}

		public DemoTableModel(int i) {
			data = new Object[i][7];
		}
	}
}