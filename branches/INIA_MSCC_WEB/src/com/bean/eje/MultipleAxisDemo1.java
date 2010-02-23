package com.bean.eje;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLine3DRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * An example of....
 *
 * @author David Gilbert
 */
public class MultipleAxisDemo1 extends ApplicationFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * A demonstration application showing how to create a time series chart with muliple axes.
     *
     * @param title  the frame title.
     */
    public MultipleAxisDemo1(String title) {

        super(title);
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setVerticalAxisTrace(true);
        setContentPane(chartPanel);

    }

    /**
     * Creates the demo chart.
     * 
     * @return The chart.
     */
    private JFreeChart createChart() {

        XYDataset dataset1 = createDataset("Series 1", 100.0, new Minute(), 200);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Multiple Axis Demo 1", 
            "Time of Day", 
            "Primary Range Axis",
            dataset1, 
            true, 
            true, 
            false
        );

        chart.setBackgroundPaint(Color.white);
        chart.addSubtitle(new TextTitle("Four datasets and four range axes."));  
        XYPlot plot = chart.getXYPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.GREEN);
        plot.setRangeGridlinePaint(Color.GREEN);
        
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        
//        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setToolTipGenerator(
                new StandardXYToolTipGenerator(
                    StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                    new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")
                )
            );
        renderer.setPaint(Color.black);
       
        // AXIS 2
        NumberAxis axis2 = new NumberAxis("Range Axis 2");
        axis2.setAutoRangeIncludesZero(false);
        axis2.setLabelPaint(Color.red);
        axis2.setTickLabelPaint(Color.red);
        plot.setRangeAxis(0, axis2);
        plot.setRangeAxisLocation(0, AxisLocation.BOTTOM_OR_LEFT);

        XYDataset dataset2 = createDataset("Series 2", 1000.0, new Minute(), 170);
        plot.setDataset(0, dataset2);
        plot.mapDatasetToRangeAxis(0, new Integer(0));
        plot.setRenderer(0, new XYLine3DRenderer());
        plot.getRenderer(0).setSeriesPaint(0, Color.red);
        
        // AXIS 3
        NumberAxis axis3 = new NumberAxis("Range Axis 3");
        axis3.setLabelPaint(Color.darkGray);
        axis3.setTickLabelPaint(Color.darkGray);
        plot.setRangeAxis(1, axis3);

        XYDataset dataset3 = createDataset("Series 3", 10000.0, new Minute(), 170);
        plot.setDataset(1, dataset3);
        plot.mapDatasetToRangeAxis(1, new Integer(1));
        
        plot.setRenderer(1, new XYBarRenderer());
        plot.getRenderer(1).setSeriesPaint(0, Color.darkGray);

        // AXIS 4        
//        NumberAxis axis4 = new NumberAxis("Range Axis 4");
//        axis4.setLabelPaint(Color.green);
//        axis4.setTickLabelPaint(Color.green);
//        plot.setRangeAxis(2, axis4);
//        
//        XYDataset dataset4 = createDataset("Series 4", 25.0, new Minute(), 200);
//        plot.setDataset(2, dataset4);
//        plot.mapDatasetToRangeAxis(2, new Integer(2));
//        
//        plot.setRenderer(2, new XYBarRenderer());
//        plot.getRenderer(2).setSeriesPaint(0, Color.green);        
                
        return chart;
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample dataset.
     * 
     * @param name  the dataset name.
     * @param base  the starting value.
     * @param start  the starting period.
     * @param count  the number of values to generate.
     *
     * @return The dataset.
     */
    private XYDataset createDataset(String name, double base, RegularTimePeriod start, int count) {

        TimeSeries series = new TimeSeries(name, start.getClass());
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
            series.add(period, value);    
            period = period.next();
            value = value * (1 + (Math.random() - 0.495) / 10.0);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        return dataset;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {

        MultipleAxisDemo1 demo = new MultipleAxisDemo1("Multiple Axis Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}