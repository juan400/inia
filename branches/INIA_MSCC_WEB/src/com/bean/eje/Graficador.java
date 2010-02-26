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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public abstract class Graficador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFreeChart createTipoEurodollar(String pTituloGrafica,
			Map<String, ArrayList> pDatos, String pColumnasConcatenadas)
			throws Exception {
		String pLineas = "WUL,WLL";
		String pBarras = "RAIN";

		JFreeChart chart = ChartFactory.createTimeSeriesChart(pTituloGrafica,
				null, null, null, true, true, false);

		chart.addSubtitle(new TextTitle(
				"Grafica WUL y WLL vs TIME y RAIN vs TIME."));

		XYPlot plot = chart.getXYPlot();
		try {
			// AXIS 0
			cargarPlot(crearDatasetVsTime(pDatos, "WUL"),
					new XYLineAndShapeRenderer(true, false), 0, "WUL", plot,
					Color.BLUE, AxisLocation.BOTTOM_OR_LEFT);

			// AXIS 1
			cargarPlot(crearDatasetVsTime(pDatos, "WLL"),
					new XYLineAndShapeRenderer(true, false), 1, "WLL", plot,
					Color.RED, AxisLocation.BOTTOM_OR_LEFT);

			// AXIS 2
			cargarPlot(crearDatasetVsTime(pDatos, "RAIN"),
					new XYBarRenderer(0), 2, "RAIN", plot, Color.YELLOW,
					AxisLocation.BOTTOM_OR_RIGHT);
		} catch (Exception e) {
			throw e;
		}

		plot.setNoDataMessage("NO DATA");
		plot.setDomainZeroBaselineVisible(true);
		plot.setRangeZeroBaselineVisible(true);
		return chart;
	}

	/**
	 * @param pTituloGrafica
	 * @param pDatos
	 * @param pColumnasConcatenadas
	 * @return
	 * @throws Exception
	 */
	public static JFreeChart createTipoXYSpline(String pTituloGrafica,
			Map<String, ArrayList> pDatos, String pColumnasConcatenadas)
			throws Exception {

		JFreeChart chart = ChartFactory.createTimeSeriesChart(pTituloGrafica,
				null, null, null, true, true, false);

		chart.addSubtitle(new TextTitle(
				"Grafica las variables CSH y LAI vs TIME"));

		XYPlot plot = chart.getXYPlot();
		String[] columnas = pColumnasConcatenadas.split(",");
		for (int i = 0; i < columnas.length; i++) {
			XYSplineRenderer linea = new XYSplineRenderer();
			linea.setShapesVisible(false);
			try {
				cargarPlot(crearDatasetVsTime(pDatos, columnas[i]), linea, i,
						columnas[i], plot, Color.BLUE,
						AxisLocation.BOTTOM_OR_LEFT);
			} catch (Exception e) {
				throw e;
			}
		}
		plot.setNoDataMessage("NO DATA");
		plot.setDomainZeroBaselineVisible(true);
		plot.setRangeZeroBaselineVisible(true);
		return chart;
	}

	public static JFreeChart createTipoScatter(String pTituloGrafica,
			Map<String, ArrayList> pDatos, String pColumnasConcatenadas)
			throws Exception {
		String[] columnas = pColumnasConcatenadas.split(",");
		String ejeX = "";
		String ejeY = "";
		if (columnas.length < 1) {
			ejeX = columnas[0];
			ejeY = columnas[1];
		}
		JFreeChart chart = ChartFactory.createScatterPlot(pTituloGrafica,
				ejeX, ejeY, crearDataset(pDatos,
						pColumnasConcatenadas), PlotOrientation.VERTICAL, true,
				false, false);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setNoDataMessage("NO DATA");
		plot.setDomainZeroBaselineVisible(true);
		plot.setRangeZeroBaselineVisible(true);

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesOutlinePaint(0, Color.black);
		renderer.setUseOutlinePaint(true);
		NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setAutoRangeIncludesZero(false);
		// domainAxis.setTickMarkInsideLength(2.0f);
		domainAxis.setTickMarkOutsideLength(0);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		// rangeAxis.setTickMarkInsideLength(2.0f);
		rangeAxis.setTickMarkOutsideLength(0.0f);
		return chart;
	}

	/**
	 * @param datos
	 * @param pTipoCurva
	 * @param pNumeroAxis
	 * @param pNombreAxis
	 * @param pPlot
	 * @param pColor
	 * @param pOrientacion
	 */
	private static void cargarPlot(XYDataset datos, XYItemRenderer pTipoCurva,
			int pNumeroAxis, String pNombreAxis, XYPlot pPlot, Color pColor,
			AxisLocation pOrientacion) {
		NumberAxis axis1 = new NumberAxis(pNombreAxis);
		axis1.setAutoRangeIncludesZero(false);
		// axis1.setLabelPaint(pColor);
		pPlot.setRangeAxis(pNumeroAxis, axis1);
		pPlot.setRangeAxisLocation(pNumeroAxis, pOrientacion);
		pPlot.setDataset(pNumeroAxis, datos);
		pPlot.mapDatasetToRangeAxis(pNumeroAxis, new Integer(pNumeroAxis));
		pPlot.setRenderer(pNumeroAxis, pTipoCurva);
		// pPlot.getRenderer(pNumeroAxis).setSeriesPaint(0, pColor);
	}

	/**
	 * @param pDatos
	 * @param pColumnasConcatenadas
	 * @return
	 * @throws Exception
	 */
	private static XYDataset crearDatasetVsTime(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas) throws Exception {
		Calendar diaCalendario = Calendar.getInstance();
		diaCalendario.setTime(new Date());
		String[] columnas = pColumnasConcatenadas.split(",");
		TimeSeriesCollection coleccionSeries = new TimeSeriesCollection();
		try {
			for (int i = 0; i < columnas.length; i++) {
				ArrayList<Double> valores = pDatos.get(columnas[i]);
				TimeSeries serie = new TimeSeries(columnas[i], Day.class);
				if (valores != null) {
					for (Iterator<Double> iterador = valores.iterator(); iterador
							.hasNext();) {
						diaCalendario.add(Calendar.DAY_OF_MONTH, 1);
						int dia = diaCalendario.get(Calendar.DAY_OF_MONTH);
						int mes = diaCalendario.get(Calendar.MONTH) + 1;
						int anio = diaCalendario.get(Calendar.YEAR);
						if (iterador.next() != null) {
							Double dato = (Double) iterador.next();
							serie.add(new Day(dia, mes, anio), dato
									.doubleValue());
						} else {
							serie.add(new Day(dia, mes, anio), null);
						}
					}
				}
				coleccionSeries.addSeries(serie);
			}
		} catch (Exception e) {
			throw e;
		}
		return coleccionSeries;
	}

	/**
	 * @param pDatos
	 * @param pColumnasConcatenadas
	 * @return
	 * @throws Exception
	 */
	private static XYDataset crearDataset(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas) throws Exception {
		Calendar diaCalendario = Calendar.getInstance();
		diaCalendario.setTime(new Date());
		String[] columnas = pColumnasConcatenadas.split(",");
		XYSeriesCollection coleccionSeries = new XYSeriesCollection();
		try {
			if (columnas.length < 1) {
				for (int i = 0; i < columnas.length; i++) {
					ArrayList<Double> valores = pDatos.get(columnas[i]);
					XYSeries serie = new XYSeries(columnas[i]);
					if (valores != null) {
						for (Iterator<Double> iterador = valores.iterator(); iterador
								.hasNext();) {
							if (iterador.next() != null) {
								Double dato = iterador.next();
								serie.add(Double.parseDouble("" + i), dato
										.doubleValue());
							} else {
								serie.add(Double.parseDouble("" + i), null);
							}
						}
					}
					coleccionSeries.addSeries(serie);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return coleccionSeries;
	}

}
