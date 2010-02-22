package com.bean.eje;

/* ---------------------
 * PriceVolumeDemo1.java
 * ---------------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 */

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.DateUtilities;
import org.jfree.date.MonthConstants;

/**
 * A demonstration application showing how to create a price-volume chart.
 */
public abstract class GraficaEurodollar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final float FIRST_LINE_LENGTH = 10.0f;
	private static final float SECOND_LINE_LENGTH = 6.0f;

	/**
	 * Creates a chart.
	 * 
	 * @return a chart.
	 */
	public static JFreeChart crearGrafica(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas, String tituloGrafica,
			String pTituloVariables, String pTituloEjeY, int width, int height) {
		// Crea el dataset para poblar la grilla
		XYDataset priceData = createPriceDataset(pDatos, pColumnasConcatenadas);

		JFreeChart chart = ChartFactory.createTimeSeriesChart(tituloGrafica,
				pTituloEjeY, pTituloVariables, priceData, true, true, false);
		XYPlot plot = chart.getXYPlot();
		NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
		rangeAxis1.setLowerMargin(0.10); // to leave room for volume bars
		// TODO regvissar el alcanze de los decimal
		DecimalFormat format = new DecimalFormat("00.00");
		rangeAxis1.setNumberFormatOverride(format);

		XYItemRenderer renderer1 = plot.getRenderer();
		renderer1.setToolTipGenerator(new StandardXYToolTipGenerator(
				StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

		// create interval overlay

		// double[] xValue = new double[1];
		// double[] yStart = new double[1];
		// double[] yEnd = new double[1];
		// double[] radius = new double[1];
		// Date sampleDate = DateUtilities.createDate(2002, MonthConstants.MAY,
		// 1);
		//
		// xValue[0] = (double) sampleDate.getTime();
		// yStart[0] = 96.345;
		// yEnd[0] = 96.75;
		// radius[0] = 31.0;

		// OpusXYZDataset intervalData = new OpusXYZDataset(xValue, yStart,
		// radius);
		XYBubbleRenderer renderer = new XYBubbleRenderer(0);
		renderer.setSeriesPaint(0, new Color(0, 255, 0, 118));
		// plot.setDataset(1, intervalData);
		plot.setRenderer(1, renderer);
		return chart;
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private static XYDataset createPriceDataset(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas) {
		Calendar dia = Calendar.getInstance();
		dia.set(new Date().getDay(), new Date().getMonth(), new Date()
				.getYear());
		String[] columnas = pColumnasConcatenadas.split(",");
		TimeSeriesCollection coleccionSeries = new TimeSeriesCollection();
		for (int i = 0; i < columnas.length; i++) {
			ArrayList valores = pDatos.get(columnas[i]);
			TimeSeries serie = new TimeSeries(columnas[i], Day.class);
			// serie.setDescription(columnas[i]);
			for (Iterator iterador = valores.iterator(); iterador.hasNext();) {
				Double dato = (Double) iterador.next();
				dia.add(Calendar.DAY_OF_MONTH, 1);
				serie.add(new Day(dia.DAY_OF_MONTH, dia.MONTH, dia.YEAR), dato
						.doubleValue());
			}
			coleccionSeries.addSeries(serie);
		}
		TimeSeries series1 = new TimeSeries("HPUS", Day.class);
		series1.add(new Day(28, MonthConstants.OCTOBER, 2002), 98.420);
		series1.add(new Day(29, MonthConstants.OCTOBER, 2002), 98.540);
		series1.add(new Day(30, MonthConstants.OCTOBER, 2002), 98.545);
		series1.add(new Day(31, MonthConstants.OCTOBER, 2002), 98.560);
		coleccionSeries.addSeries(series1);
		return coleccionSeries;
	}

}