package com.bean.eje;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;

public abstract class Graficador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Creates a chart.
	 * 
	 * @return a chart.
	 */
	public static JFreeChart createTipoEurodollar(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas) {
		String pTituloGrafica = "Grafica de nitrogeno";
		String pTituloVariables = "Variables";
		String pTituloEjeY = "Calendario";
		String pLineas = "WUL,WLL";
		String pBarras = "RAIN";
		String title = "Acumulación de biomasa y Indice de area foliar";
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, null,
				null, null, true, true, false);
		// Grafica WUL y WLL vs TIME en lineas y RAIN vs TIME en barras de una
		// grafica tipo "Eurodollar futures..."
		chart.addSubtitle(new TextTitle(
				"Grafica WUL y WLL vs TIME y RAIN vs TIME."));
		XYPlot plot = chart.getXYPlot();
		// AXIS 0
//		cargarPlot(createPriceDataset("WUL"),new XYLineAndShapeRenderer(true,false), 0,"WUL",plot,Color.BLUE,AxisLocation.BOTTOM_OR_LEFT);
		cargarPlot(crearDataset(pDatos, "WUL"),new XYLineAndShapeRenderer(true,false), 0,"WUL",plot,Color.BLUE,AxisLocation.BOTTOM_OR_LEFT);
		// AXIS 1
//		cargarPlot(createVolumeDataset("WLL"),new XYLineAndShapeRenderer(true,false), 1,"WLL",plot,Color.RED,AxisLocation.BOTTOM_OR_LEFT);
		cargarPlot(crearDataset(pDatos, "WLL"),new XYLineAndShapeRenderer(true,false), 1,"WLL",plot,Color.RED,AxisLocation.BOTTOM_OR_LEFT);
		// AXIS 2
//		cargarPlot(createVolumeDataset("RAIN"),new XYBarRenderer(0), 2,"RAIN",plot,Color.YELLOW,AxisLocation.BOTTOM_OR_RIGHT);
		cargarPlot(crearDataset(pDatos, "RAIN"),new XYBarRenderer(0), 2,"RAIN",plot,Color.YELLOW,AxisLocation.BOTTOM_OR_RIGHT);
		
		return chart;
	}

	private static void cargarPlot(XYDataset datos, XYItemRenderer pTipoCurva,int pNumeroAxis, String pNombreAxis, XYPlot pPlot, Color pColor, AxisLocation pOrientacion){
		NumberAxis axis1 = new NumberAxis(pNombreAxis);
		axis1.setAutoRangeIncludesZero(false);
		axis1.setLabelPaint(pColor);
//		axis1.setTickLabelPaint(pColor);
//		axis1.setTickLabelFont(Font.BOLD);
		pPlot.setRangeAxis(pNumeroAxis, axis1);
		pPlot.setRangeAxisLocation(pNumeroAxis, pOrientacion);

//		XYDataset dataset1 = createPriceDataset(pNombreAxis);
		pPlot.setDataset(pNumeroAxis, datos);
		pPlot.mapDatasetToRangeAxis(pNumeroAxis, new Integer(pNumeroAxis));
		pPlot.setRenderer(pNumeroAxis, pTipoCurva);
		pPlot.getRenderer(pNumeroAxis).setSeriesPaint(0, pColor);
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private static XYDataset crearDataset(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas) {
		Calendar dia = Calendar.getInstance();
		Date diaActual = new Date();
		dia.set(diaActual.getDay(), diaActual.getMonth(), diaActual
				.getYear());
		String[] columnas = pColumnasConcatenadas.split(",");
		TimeSeriesCollection coleccionSeries = new TimeSeriesCollection();
		for (int i = 0; i < columnas.length; i++) {
			ArrayList valores = pDatos.get(columnas[i]);
			TimeSeries serie = new TimeSeries(columnas[i], Day.class);
			// serie.setDescription(columnas[i]);
//			Day day = new Day(diaActual);
			RegularTimePeriod time = new Day((1+dia.get(Calendar.DAY_OF_MONTH)), (1+dia.get(Calendar.MONTH)), (1900+dia.get(Calendar.YEAR)));
			for (Iterator iterador = valores.iterator(); iterador.hasNext();) {
				Double dato = (Double) iterador.next();
//				dia.add(Calendar.DAY_OF_MONTH, 1);
//				dia.set(dia.get(Calendar.YEAR), dia.get(Calendar.MONTH), dia.get(Calendar.DAY_OF_MONTH));
				//dia.get(Calendar.DAY_OF_MONTH),dia.get(Calendar.MONTH),dia.get(Calendar.YEAR));
//				RegularTimePeriod actual = day.next().;
//				Day day = new Day((Date)dia.getTime());
				int day = dia.get(Calendar.DAY_OF_MONTH)+1;
				int month = dia.get(Calendar.MONTH)+1;
				int year = 1900+dia.get(Calendar.YEAR);
//				(TimeSeries)pop.get(item)).add(new Day(day, month, year), success_rate)
				serie.add(new Day(day, month, year), dato.doubleValue());
				time = time.next();
			}
			coleccionSeries.addSeries(serie);
		}
//		TimeSeries series1 = new TimeSeries("HPUS", Day.class);
//		series1.add(new Day(28, MonthConstants.OCTOBER, 2002), 98.420);
//		series1.add(new Day(29, MonthConstants.OCTOBER, 2002), 98.540);
//		series1.add(new Day(30, MonthConstants.OCTOBER, 2002), 98.545);
//		series1.add(new Day(31, MonthConstants.OCTOBER, 2002), 98.560);
//		coleccionSeries.addSeries(series1);
		return coleccionSeries;
	}
	
}
