///******************************************************************************
// * HistoryGraphBuilder.java - Build a graph of the hours worked.
// * $Id: HistoryGraphBuilder.java,v 1.1 2008/07/28 23:18:16 dick Exp $
// * 
// * TimeSheet - Keep track of Dick's work time
// * Copyright© 2008 - BuckoSoft, Corp. and Dick Balaska
// * 
// * $Log: HistoryGraphBuilder.java,v $
// * Revision 1.1  2008/07/28 23:18:16  dick
// * Move all of the graph building stuff from HistoryGraphController to here.
// * Create a graph and save it to a file.
// *
// */
//package com.bean.adm;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.io.File;
//import java.text.FieldPosition;
//import java.text.NumberFormat;
//import java.text.ParsePosition;
//import java.util.Calendar;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.DateAxis;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.axis.NumberTickUnit;
//import org.jfree.chart.axis.SymbolAxis;
//import org.jfree.chart.plot.IntervalMarker;
//import org.jfree.chart.plot.PiePlot;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.LookupPaintScale;
//import org.jfree.chart.renderer.xy.XYBlockRenderer;
//import org.jfree.chart.title.PaintScaleLegend;
//import org.jfree.data.time.Day;
//import org.jfree.data.time.RegularTimePeriod;
//import org.jfree.data.xy.DefaultXYZDataset;
//import org.jfree.data.xy.XYZDataset;
//import org.jfree.ui.Layer;
//import org.jfree.ui.RectangleAnchor;
//import org.jfree.ui.RectangleEdge;
//import org.jfree.ui.RectangleInsets;
//import org.jfree.ui.TextAnchor;
//
//import com.buckosoft.TimeSheet.domain.Config;
//import com.buckosoft.TimeSheet.domain.WebCalCategory;
//import com.buckosoft.TimeSheet.domain.WebCalEntry;
//
///** Build a graph of the hours worked.
// * @author Dick Balaska
// * @since 2008/07/28
// * @version $Revision: 1.1 $ <br> $Date: 2008/07/28 23:18:16 $
// * @see <a href="http://cvs.buckosoft.com/Projects/java/TimeSheet/TimeSheet/src/com/buckosoft/TimeSheet/business/HistoryGraphBuilder.java">HistoryGraphBuilder.java</a>
// */
//public class HistoryGraphBuilder {
//	protected static final boolean DEBUG = true;
//	protected final Log logger = LogFactory.getLog(getClass());
//
//	/** Translate the category Ids into jobs that we want to handle. */
//	private final static int jobCategories[] = { 4, 5, 6, 1, 3 };
//
//	private	Config	config;
//	private	TimeSheetFacade	tsf;
//
//	/** AOP setter for the primary TimeSheet API
//	 * @param tsf The TimeSheet API object
//	 */
//	public void setTimeSheet(TimeSheetFacade tsf) {
//		this.tsf = tsf;
//	}
//
//	/** Set the system configuration
//	 * @param config A reference to the config 
//	 */
//	public void setConfig(Config config) {
//		this.config = config;
//	}
//
//	public void buildGraph(int year) {
//		XYZDataset dataset = createDataset(year);
//		try {
//			DateAxis xAxis = new DateAxis("Date");
//			xAxis.setLowerMargin(0.0);
//			xAxis.setUpperMargin(0.0);
//			xAxis.setInverted(true);
//			NumberAxis yAxis = new NumberAxis("Hour");
//			yAxis.setUpperMargin(0.0);
//			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//			yAxis.setTickUnit(new NumberTickUnit(4));
//			yAxis.setNumberFormatOverride(new MyNumberFormat());
//			XYBlockRenderer renderer = new XYBlockRenderer();
//			renderer.setBlockWidth(1000.0 * 60.0 * 60.0 * 24.0);
//			renderer.setBlockAnchor(RectangleAnchor.BOTTOM_LEFT);
//			LookupPaintScale paintScale = new LookupPaintScale(0.5, 5.5,
//					Color.white);
//			paintScale.add(0.5, Color.getHSBColor(0.3125f, 0.9375f, 0.5f));
//			paintScale.add(1.5, Color.red);
//			paintScale.add(2.5, Color.blue);
//			paintScale.add(3.5, Color.yellow);
//			//paintScale.add(4.5, Color.orange);
//			paintScale.add(4.5, Color.pink);
//			renderer.setPaintScale(paintScale);
//			XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
//			plot.setOrientation(PlotOrientation.HORIZONTAL);
//			plot.setBackgroundPaint(Color.lightGray);
//			plot.setRangeGridlinePaint(Color.white);
//			plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
//			
//	        IntervalMarker target = new IntervalMarker(9*4, 17*4);
//	        //target.setLabel("9 to 5");
//	        target.setLabel(null);
//	        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
//	        target.setLabelAnchor(RectangleAnchor.BOTTOM);
//	        target.setLabelTextAnchor(TextAnchor.BOTTOM_CENTER);
//	        target.setPaint(new Color(222, 222, 255, 128));
//	        plot.addRangeMarker(target, Layer.BACKGROUND);
//			JFreeChart chart = new JFreeChart("Hours Worked " + year, plot);
//			chart.removeLegend();
//			chart.setBackgroundPaint(Color.white);
//
//			String[] titles = new String[jobCategories.length+1];
//			for (int i=0; i<jobCategories.length; i++) {
//				WebCalCategory wce = tsf.getDB().getCategory(jobCategories[i]);
//				titles[i+1] = wce.getName();
//			}
////			SymbolAxis scaleAxis = new SymbolAxis(null, new String[] {"",
////					"Gretag", "Free", "Group 1", "Group 2"});
//			SymbolAxis scaleAxis = new SymbolAxis(null, titles);
//			scaleAxis.setRange(0.5, 5.5);
////			scaleAxis.setRange(1, 1);
//			scaleAxis.setPlot(new PiePlot());
//			scaleAxis.setGridBandsVisible(false);
//			PaintScaleLegend psl = new PaintScaleLegend(paintScale, scaleAxis);
//			psl.setMargin(new RectangleInsets(3, 10, 3, 10));
//			psl.setPosition(RectangleEdge.BOTTOM);
//			psl.setAxisOffset(5.0);
//			chart.addSubtitle(psl);
//			File file = new File(this.config.getGraphDirectory(),
//					this.config.getGraphFileName() + year + "." + this.config.getGraphExt());
//			if (DEBUG)
//				logger.info("Writing '" + file.getPath() + "'");
//			//try {
//				ChartUtilities.saveChartAsPNG(file, chart, 800, 850);
//			//} catch (IOException e) {}
//		}
//		catch (Exception e) {
//			logger.info(e);
//		}
//	}
//		
//	private XYZDataset createDataset(int year) {
//		int horiz = 24*4;		// 15 minute intervals
//		int maxDays = 365;
////		int maxDays = 60;
//		if (year % 4 == 0)
//			maxDays++;
//		if (DEBUG)
//			logger.info("year=" + year + " maxDays=" + maxDays);
//		double[] xvalues = new double[horiz*maxDays];
//		double[] yvalues = new double[horiz*maxDays];
//		double[] zvalues = new double[horiz*maxDays];
//		RegularTimePeriod t = new Day(1, 1, year);
//		Calendar cal = Calendar.getInstance();
//		int day;
//		for (day=0; day<maxDays; day++) {
//			for (int hour=0; hour<horiz; hour++) {
//				xvalues[day * horiz + hour] = t.getFirstMillisecond();
//				yvalues[day * horiz + hour] = hour;
//				zvalues[day * horiz + hour] = 0;
//				
//			}
//		}
//		for (day = 0; day < maxDays; day++) {
//			cal.setTimeInMillis(t.getFirstMillisecond());
//			for (int cat=0; cat<jobCategories.length; cat++) {
//				List<WebCalEntry> list = tsf.getDB().getEntries(jobCategories[cat], cal, cal);
//				if (list.isEmpty())
//					continue;
//				Iterator<WebCalEntry> iter = list.iterator();
//				while (iter.hasNext()) {
//					WebCalEntry wce = iter.next();
//					cal.setTime(wce.getStartDateTime());
//					int duration = wce.getDuration()/15;
//					int hour = cal.get(Calendar.HOUR_OF_DAY)*4;
//					hour += cal.get(Calendar.MINUTE)/15;
//					for (int i=0; i<duration; i++) {
//						xvalues[day * horiz + hour+i] = t.getFirstMillisecond();
//						yvalues[day * horiz + hour+i] = hour+i;
//						zvalues[day * horiz + hour+i] = cat+1;
//					}
//					
//				}
//			}
//			t = t.next();
//		}
//		DefaultXYZDataset dataset = new DefaultXYZDataset();
//		dataset.addSeries("Series 1",
//				new double[][] { xvalues, yvalues, zvalues });
//		return dataset;
//	}
//	
//	/** The X axis is in 15 minute increments.  I can't figure out how to get the legend
//	 * on the axis to display number/4 which would display the hour.
//	 * What i can do is override the axis's number formatter and do my /4 here.
//	 * @author dick
//	 */
//	private class MyNumberFormat extends NumberFormat {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
//			int n = (int)(number /4.0);
//			toAppendTo.append("" + n);
//			return(toAppendTo);
//		}
//
//		@Override
//		public StringBuffer format(long number, StringBuffer toAppendTo,
//				FieldPosition pos) {
//			int n = (int)(number /4);
//			toAppendTo.append("" + n);
//			return(toAppendTo);
//		}
//
//		@Override
//		public Number parse(String source, ParsePosition parsePosition) {
//			// TODO Auto-generated method stub
//			return new Integer(Integer.parseInt(source));
//		}
//		
//	}
//
//}
