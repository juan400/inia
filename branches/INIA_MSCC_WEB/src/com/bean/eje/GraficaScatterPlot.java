package com.bean.eje;

/* ---------------------
 * ScatterPlotDemo1.java
 * ---------------------
 * (C) Copyright 2002-2006, by Object Refinery Limited.
 *
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo scatter plot.
 */
public class GraficaScatterPlot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics2D g;

	/**
     * A demonstration application showing a scatter plot.
     *
     * @param title  the frame title.
     */
    public GraficaScatterPlot(String title) {
//        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot Demo 1",
                "X", "Y", dataset, PlotOrientation.VERTICAL, true, false, false);
 
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        
        XYLineAndShapeRenderer renderer 
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setUseOutlinePaint(true);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        domainAxis.setTickMarkInsideLength(2.0f);
        domainAxis.setTickMarkOutsideLength(0.0f);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickMarkInsideLength(2.0f);
        rangeAxis.setTickMarkOutsideLength(0.0f);
        
        return chart;
    }
    
    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createPriceDataset());//(new SampleXYDataset2());
        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setVerticalAxisTrace(true);
        //chartPanel.setHorizontalAxisTrace(true);
        // popup menu conflicts with axis trace
        chartPanel.setPopupMenu(null);
        
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        return chartPanel;
    }
    
    private static XYDataset createPriceDataset() {

        // create dataset 1...
        TimeSeries series1 = new TimeSeries("Price", Day.class);
        
        series1.add(new Day(2, MonthConstants.JANUARY, 2002), 95.565);
        series1.add(new Day(3, MonthConstants.JANUARY, 2002), 95.640);
        series1.add(new Day(4, MonthConstants.JANUARY, 2002), 95.710);

        series1.add(new Day(7, MonthConstants.JANUARY, 2002), 95.930);
        series1.add(new Day(8, MonthConstants.JANUARY, 2002), 95.930);
        series1.add(new Day(9, MonthConstants.JANUARY, 2002), 95.960);
        series1.add(new Day(10, MonthConstants.JANUARY, 2002), 96.055);
        series1.add(new Day(11, MonthConstants.JANUARY, 2002), 96.335);

        series1.add(new Day(14, MonthConstants.JANUARY, 2002), 96.290);
        series1.add(new Day(15, MonthConstants.JANUARY, 2002), 96.275);
        series1.add(new Day(16, MonthConstants.JANUARY, 2002), 96.240);
        series1.add(new Day(17, MonthConstants.JANUARY, 2002), 96.080);
        series1.add(new Day(18, MonthConstants.JANUARY, 2002), 96.145);

        series1.add(new Day(22, MonthConstants.JANUARY, 2002), 96.120);
        series1.add(new Day(23, MonthConstants.JANUARY, 2002), 96.015);
        series1.add(new Day(24, MonthConstants.JANUARY, 2002), 95.890);
        series1.add(new Day(25, MonthConstants.JANUARY, 2002), 95.8650);

        series1.add(new Day(28, MonthConstants.JANUARY, 2002), 95.880);
        series1.add(new Day(29, MonthConstants.JANUARY, 2002), 96.050);
        series1.add(new Day(30, MonthConstants.JANUARY, 2002), 96.065);
        series1.add(new Day(31, MonthConstants.JANUARY, 2002), 95.910);
        series1.add(new Day(1, MonthConstants.FEBRUARY, 2002), 96.015);

        series1.add(new Day(4, MonthConstants.FEBRUARY, 2002), 96.140);
        series1.add(new Day(5, MonthConstants.FEBRUARY, 2002), 96.195);
        series1.add(new Day(6, MonthConstants.FEBRUARY, 2002), 96.245);
        series1.add(new Day(7, MonthConstants.FEBRUARY, 2002), 96.220);
        series1.add(new Day(8, MonthConstants.FEBRUARY, 2002), 96.280);

        series1.add(new Day(11, MonthConstants.FEBRUARY, 2002), 96.265);
        series1.add(new Day(12, MonthConstants.FEBRUARY, 2002), 96.160);
        series1.add(new Day(13, MonthConstants.FEBRUARY, 2002), 96.120);
        series1.add(new Day(14, MonthConstants.FEBRUARY, 2002), 96.125);
        series1.add(new Day(15, MonthConstants.FEBRUARY, 2002), 96.265);

        series1.add(new Day(19, MonthConstants.FEBRUARY, 2002), 96.290);
        series1.add(new Day(20, MonthConstants.FEBRUARY, 2002), 96.275);
        series1.add(new Day(21, MonthConstants.FEBRUARY, 2002), 96.280);
        series1.add(new Day(22, MonthConstants.FEBRUARY, 2002), 96.305);

        series1.add(new Day(25, MonthConstants.FEBRUARY, 2002), 96.265);
        series1.add(new Day(26, MonthConstants.FEBRUARY, 2002), 96.185);
        series1.add(new Day(27, MonthConstants.FEBRUARY, 2002), 96.305);
        series1.add(new Day(28, MonthConstants.FEBRUARY, 2002), 96.215);
        series1.add(new Day(1, MonthConstants.MARCH, 2002), 96.015);

        series1.add(new Day(4, MonthConstants.MARCH, 2002), 95.970);
        series1.add(new Day(5, MonthConstants.MARCH, 2002), 95.935);
        series1.add(new Day(6, MonthConstants.MARCH, 2002), 95.935);
        series1.add(new Day(7, MonthConstants.MARCH, 2002), 95.705);
        series1.add(new Day(8, MonthConstants.MARCH, 2002), 95.4850);

        series1.add(new Day(11, MonthConstants.MARCH, 2002), 95.505);
        series1.add(new Day(12, MonthConstants.MARCH, 2002), 95.540);
        series1.add(new Day(13, MonthConstants.MARCH, 2002), 95.675);
        series1.add(new Day(14, MonthConstants.MARCH, 2002), 95.510);
        series1.add(new Day(15, MonthConstants.MARCH, 2002), 95.500);

        series1.add(new Day(18, MonthConstants.MARCH, 2002), 95.500);
        series1.add(new Day(19, MonthConstants.MARCH, 2002), 95.535);
        series1.add(new Day(20, MonthConstants.MARCH, 2002), 95.420);
        series1.add(new Day(21, MonthConstants.MARCH, 2002), 95.400);
        series1.add(new Day(22, MonthConstants.MARCH, 2002), 95.375);

        series1.add(new Day(25, MonthConstants.MARCH, 2002), 95.350);
        series1.add(new Day(26, MonthConstants.MARCH, 2002), 95.505);
        series1.add(new Day(27, MonthConstants.MARCH, 2002), 95.550);
        series1.add(new Day(28, MonthConstants.MARCH, 2002), 95.485);

        series1.add(new Day(1, MonthConstants.APRIL, 2002), 95.485);
        series1.add(new Day(2, MonthConstants.APRIL, 2002), 95.630);
        series1.add(new Day(3, MonthConstants.APRIL, 2002), 95.735);
        series1.add(new Day(4, MonthConstants.APRIL, 2002), 95.695);
        series1.add(new Day(5, MonthConstants.APRIL, 2002), 95.810);

        series1.add(new Day(8, MonthConstants.APRIL, 2002), 95.810);
        series1.add(new Day(9, MonthConstants.APRIL, 2002), 95.865);
        series1.add(new Day(10, MonthConstants.APRIL, 2002), 95.885);
        series1.add(new Day(11, MonthConstants.APRIL, 2002), 95.900);
        series1.add(new Day(12, MonthConstants.APRIL, 2002), 95.980);

        series1.add(new Day(15, MonthConstants.APRIL, 2002), 96.035);
        series1.add(new Day(16, MonthConstants.APRIL, 2002), 96.000);
        series1.add(new Day(17, MonthConstants.APRIL, 2002), 96.035);
        series1.add(new Day(18, MonthConstants.APRIL, 2002), 96.085);
        series1.add(new Day(19, MonthConstants.APRIL, 2002), 96.0750);

        series1.add(new Day(22, MonthConstants.APRIL, 2002), 96.105);
        series1.add(new Day(23, MonthConstants.APRIL, 2002), 96.075);
        series1.add(new Day(24, MonthConstants.APRIL, 2002), 96.210);
        series1.add(new Day(25, MonthConstants.APRIL, 2002), 96.255);
        series1.add(new Day(26, MonthConstants.APRIL, 2002), 96.310);

        series1.add(new Day(29, MonthConstants.APRIL, 2002), 96.310);
        series1.add(new Day(30, MonthConstants.APRIL, 2002), 96.325);
        series1.add(new Day(1, MonthConstants.MAY, 2002), 96.345);
        series1.add(new Day(2, MonthConstants.MAY, 2002), 96.285);
        series1.add(new Day(3, MonthConstants.MAY, 2002), 96.385);

        series1.add(new Day(6, MonthConstants.MAY, 2002), 96.380);
        series1.add(new Day(7, MonthConstants.MAY, 2002), 96.485);
        series1.add(new Day(8, MonthConstants.MAY, 2002), 96.230);
        series1.add(new Day(9, MonthConstants.MAY, 2002), 96.310);
        series1.add(new Day(10, MonthConstants.MAY, 2002), 96.445);

        series1.add(new Day(13, MonthConstants.MAY, 2002), 96.355);
        series1.add(new Day(14, MonthConstants.MAY, 2002), 96.180);
        series1.add(new Day(15, MonthConstants.MAY, 2002), 96.240);
        series1.add(new Day(16, MonthConstants.MAY, 2002), 96.325);
        series1.add(new Day(17, MonthConstants.MAY, 2002), 96.200);

        series1.add(new Day(20, MonthConstants.MAY, 2002), 96.305);
        series1.add(new Day(21, MonthConstants.MAY, 2002), 96.385);
        series1.add(new Day(22, MonthConstants.MAY, 2002), 96.445);
        series1.add(new Day(23, MonthConstants.MAY, 2002), 96.385);
        series1.add(new Day(24, MonthConstants.MAY, 2002), 96.390);

        series1.add(new Day(28, MonthConstants.MAY, 2002), 96.390);
        series1.add(new Day(29, MonthConstants.MAY, 2002), 96.475);
        series1.add(new Day(30, MonthConstants.MAY, 2002), 96.555);
        series1.add(new Day(31, MonthConstants.MAY, 2002), 96.500);

        series1.add(new Day(3, MonthConstants.JUNE, 2002), 96.540);
        series1.add(new Day(4, MonthConstants.JUNE, 2002), 96.605);
        series1.add(new Day(5, MonthConstants.JUNE, 2002), 96.580);
        series1.add(new Day(6, MonthConstants.JUNE, 2002), 96.610);
        series1.add(new Day(7, MonthConstants.JUNE, 2002), 96.600);

        series1.add(new Day(10, MonthConstants.JUNE, 2002), 96.615);
        series1.add(new Day(11, MonthConstants.JUNE, 2002), 96.705);
        series1.add(new Day(12, MonthConstants.JUNE, 2002), 96.750);
        series1.add(new Day(13, MonthConstants.JUNE, 2002), 96.830);
        series1.add(new Day(14, MonthConstants.JUNE, 2002), 96.965);

        series1.add(new Day(17, MonthConstants.JUNE, 2002), 96.945);
        series1.add(new Day(18, MonthConstants.JUNE, 2002), 96.990);
        series1.add(new Day(19, MonthConstants.JUNE, 2002), 97.165);
        series1.add(new Day(20, MonthConstants.JUNE, 2002), 97.030);
        series1.add(new Day(21, MonthConstants.JUNE, 2002), 97.145);

        series1.add(new Day(24, MonthConstants.JUNE, 2002), 97.120);
        series1.add(new Day(25, MonthConstants.JUNE, 2002), 97.175);
        series1.add(new Day(26, MonthConstants.JUNE, 2002), 97.365);
        series1.add(new Day(27, MonthConstants.JUNE, 2002), 97.245);
        series1.add(new Day(28, MonthConstants.JUNE, 2002), 97.245);

        series1.add(new Day(1, MonthConstants.JULY, 2002), 97.290);
        series1.add(new Day(2, MonthConstants.JULY, 2002), 97.380);
        series1.add(new Day(3, MonthConstants.JULY, 2002), 97.380);

        series1.add(new Day(5, MonthConstants.JULY, 2002), 97.220);

        series1.add(new Day(8, MonthConstants.JULY, 2002), 97.325);
        series1.add(new Day(9, MonthConstants.JULY, 2002), 97.455);
        series1.add(new Day(10, MonthConstants.JULY, 2002), 97.580);
        series1.add(new Day(11, MonthConstants.JULY, 2002), 97.605);
        series1.add(new Day(12, MonthConstants.JULY, 2002), 97.690);

        series1.add(new Day(15, MonthConstants.JULY, 2002), 97.730);
        series1.add(new Day(16, MonthConstants.JULY, 2002), 97.580);
        series1.add(new Day(17, MonthConstants.JULY, 2002), 97.640);
        series1.add(new Day(18, MonthConstants.JULY, 2002), 97.680);
        series1.add(new Day(19, MonthConstants.JULY, 2002), 97.715);

        series1.add(new Day(22, MonthConstants.JULY, 2002), 97.815);
        series1.add(new Day(23, MonthConstants.JULY, 2002), 97.875);
        series1.add(new Day(24, MonthConstants.JULY, 2002), 97.835);
        series1.add(new Day(25, MonthConstants.JULY, 2002), 97.925);
        series1.add(new Day(26, MonthConstants.JULY, 2002), 97.960);

        series1.add(new Day(29, MonthConstants.JULY, 2002), 97.745);
        series1.add(new Day(30, MonthConstants.JULY, 2002), 97.710);
        series1.add(new Day(31, MonthConstants.JULY, 2002), 97.930);
        series1.add(new Day(1, MonthConstants.AUGUST, 2002), 98.000);
        series1.add(new Day(2, MonthConstants.AUGUST, 2002), 98.170);

        series1.add(new Day(5, MonthConstants.AUGUST, 2002), 98.225);
        series1.add(new Day(6, MonthConstants.AUGUST, 2002), 98.115);
        series1.add(new Day(7, MonthConstants.AUGUST, 2002), 98.265);
        series1.add(new Day(8, MonthConstants.AUGUST, 2002), 98.180);
        series1.add(new Day(9, MonthConstants.AUGUST, 2002), 98.185);

        series1.add(new Day(12, MonthConstants.AUGUST, 2002), 98.150);
        series1.add(new Day(13, MonthConstants.AUGUST, 2002), 98.290);
        series1.add(new Day(14, MonthConstants.AUGUST, 2002), 98.155);
        series1.add(new Day(15, MonthConstants.AUGUST, 2002), 98.075);
        series1.add(new Day(16, MonthConstants.AUGUST, 2002), 98.000);

        series1.add(new Day(19, MonthConstants.AUGUST, 2002), 98.040);
        series1.add(new Day(20, MonthConstants.AUGUST, 2002), 98.135);
        series1.add(new Day(21, MonthConstants.AUGUST, 2002), 98.110);
        series1.add(new Day(22, MonthConstants.AUGUST, 2002), 98.005);
        series1.add(new Day(23, MonthConstants.AUGUST, 2002), 98.055);

        series1.add(new Day(26, MonthConstants.AUGUST, 2002), 98.065);
        series1.add(new Day(27, MonthConstants.AUGUST, 2002), 97.980);
        series1.add(new Day(28, MonthConstants.AUGUST, 2002), 98.035);
        series1.add(new Day(29, MonthConstants.AUGUST, 2002), 98.095);
        series1.add(new Day(30, MonthConstants.AUGUST, 2002), 98.060);

        series1.add(new Day(3, MonthConstants.SEPTEMBER, 2002), 98.250);
        series1.add(new Day(4, MonthConstants.SEPTEMBER, 2002), 98.245);
        series1.add(new Day(5, MonthConstants.SEPTEMBER, 2002), 98.315);
        series1.add(new Day(6, MonthConstants.SEPTEMBER, 2002), 98.170);

        series1.add(new Day(9, MonthConstants.SEPTEMBER, 2002), 98.080);
        series1.add(new Day(10, MonthConstants.SEPTEMBER, 2002), 98.090);
        series1.add(new Day(11, MonthConstants.SEPTEMBER, 2002), 98.030);
        series1.add(new Day(12, MonthConstants.SEPTEMBER, 2002), 98.105);
        series1.add(new Day(13, MonthConstants.SEPTEMBER, 2002), 98.135);

        series1.add(new Day(16, MonthConstants.SEPTEMBER, 2002), 98.115);
        series1.add(new Day(17, MonthConstants.SEPTEMBER, 2002), 98.125);
        series1.add(new Day(18, MonthConstants.SEPTEMBER, 2002), 98.130);
        series1.add(new Day(19, MonthConstants.SEPTEMBER, 2002), 98.255);
        series1.add(new Day(20, MonthConstants.SEPTEMBER, 2002), 98.255);

        series1.add(new Day(23, MonthConstants.SEPTEMBER, 2002), 98.280);
        series1.add(new Day(24, MonthConstants.SEPTEMBER, 2002), 98.310);
        series1.add(new Day(25, MonthConstants.SEPTEMBER, 2002), 98.250);
        series1.add(new Day(26, MonthConstants.SEPTEMBER, 2002), 98.300);
        series1.add(new Day(27, MonthConstants.SEPTEMBER, 2002), 98.410);

        series1.add(new Day(30, MonthConstants.SEPTEMBER, 2002), 98.495);
        series1.add(new Day(1, MonthConstants.OCTOBER, 2002), 98.440);
        series1.add(new Day(2, MonthConstants.OCTOBER, 2002), 98.440);
        series1.add(new Day(3, MonthConstants.OCTOBER, 2002), 98.440);
        series1.add(new Day(4, MonthConstants.OCTOBER, 2002), 98.380);

        series1.add(new Day(7, MonthConstants.OCTOBER, 2002), 98.385);
        series1.add(new Day(8, MonthConstants.OCTOBER, 2002), 98.340);
        series1.add(new Day(9, MonthConstants.OCTOBER, 2002), 98.420);
        series1.add(new Day(10, MonthConstants.OCTOBER, 2002), 98.375);
        series1.add(new Day(11, MonthConstants.OCTOBER, 2002), 98.275);

        series1.add(new Day(14, MonthConstants.OCTOBER, 2002), 98.275);
        series1.add(new Day(15, MonthConstants.OCTOBER, 2002), 98.135);
        series1.add(new Day(16, MonthConstants.OCTOBER, 2002), 98.165);
        series1.add(new Day(17, MonthConstants.OCTOBER, 2002), 98.170);
        series1.add(new Day(18, MonthConstants.OCTOBER, 2002), 98.165);

        series1.add(new Day(21, MonthConstants.OCTOBER, 2002), 98.105);
        series1.add(new Day(22, MonthConstants.OCTOBER, 2002), 98.125);
        series1.add(new Day(23, MonthConstants.OCTOBER, 2002), 98.185);
        series1.add(new Day(24, MonthConstants.OCTOBER, 2002), 98.245);
        series1.add(new Day(25, MonthConstants.OCTOBER, 2002), 98.320);

        series1.add(new Day(28, MonthConstants.OCTOBER, 2002), 98.420);
        series1.add(new Day(29, MonthConstants.OCTOBER, 2002), 98.540);
        series1.add(new Day(30, MonthConstants.OCTOBER, 2002), 98.545);
        series1.add(new Day(31, MonthConstants.OCTOBER, 2002), 98.560);

        return new TimeSeriesCollection(series1);

    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
//    	GraficaScatterPlot demo = new GraficaScatterPlot("Scatter Plot Demo 1");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
    }

	public Graphics2D getG() {
		return g;
	}

	public void setG(Graphics2D g) {
		this.g = g;
	}

}