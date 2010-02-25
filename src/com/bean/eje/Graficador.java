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
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

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
			cargarPlot(crearDataset(pDatos, "WUL"), new XYLineAndShapeRenderer(
					true, false), 0, "WUL", plot, Color.BLUE,
					AxisLocation.BOTTOM_OR_LEFT);

			// AXIS 1
			cargarPlot(crearDataset(pDatos, "WLL"), new XYLineAndShapeRenderer(
					true, false), 1, "WLL", plot, Color.RED,
					AxisLocation.BOTTOM_OR_LEFT);

			// AXIS 2
			cargarPlot(crearDataset(pDatos, "RAIN"), new XYBarRenderer(0), 2,
					"RAIN", plot, Color.YELLOW, AxisLocation.BOTTOM_OR_RIGHT);
		} catch (Exception e) {
			throw e;
		}
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
				cargarPlot(crearDataset(pDatos, columnas[i]), linea, i,
						columnas[i], plot, Color.BLUE,
						AxisLocation.BOTTOM_OR_LEFT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}

		// // AXIS 0
		// XYSplineRenderer linea = new XYSplineRenderer();
		// linea.setShapesVisible(false);
		// cargarPlot(crearDataset(pDatos, "CSH"), linea, 0, "CSH", plot,
		// Color.BLUE,
		// AxisLocation.BOTTOM_OR_LEFT);
		// // AXIS 1
		// linea = new XYSplineRenderer();
		// linea.setShapesVisible(false);
		// cargarPlot(crearDataset(pDatos, "LAI"), linea, 1, "LAI", plot,
		// Color.RED,
		// AxisLocation.BOTTOM_OR_LEFT);

		return chart;
	}

	public static JFreeChart createTipoScatter(String pTituloGrafica,
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
				cargarPlot(crearDataset(pDatos, columnas[i]), linea, i,
						columnas[i], plot, Color.BLUE,
						AxisLocation.BOTTOM_OR_LEFT);
			} catch (Exception e) {
				throw e;
			}
		}

		// // AXIS 0
		// XYSplineRenderer linea = new XYSplineRenderer();
		// linea.setShapesVisible(false);
		// cargarPlot(crearDataset(pDatos, "CSH"), linea, 0, "CSH", plot,
		// Color.BLUE,
		// AxisLocation.BOTTOM_OR_LEFT);
		// // AXIS 1
		// linea = new XYSplineRenderer();
		// linea.setShapesVisible(false);
		// cargarPlot(crearDataset(pDatos, "LAI"), linea, 1, "LAI", plot,
		// Color.RED,
		// AxisLocation.BOTTOM_OR_LEFT);

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
	private static XYDataset crearDataset(Map<String, ArrayList> pDatos,
			String pColumnasConcatenadas) throws Exception {
		Calendar diaCalendario = Calendar.getInstance();
		diaCalendario.setTime(new Date());
		String[] columnas = pColumnasConcatenadas.split(",");
		TimeSeriesCollection coleccionSeries = new TimeSeriesCollection();
		try {
			for (int i = 0; i < columnas.length; i++) {
				ArrayList valores = pDatos.get(columnas[i]);
				TimeSeries serie = new TimeSeries(columnas[i], Day.class);
				if (valores != null) {
					for (Iterator iterador = valores.iterator(); iterador
							.hasNext();) {
						Double dato = (Double) iterador.next();
						diaCalendario.add(Calendar.DAY_OF_MONTH, 1);
						int dia = diaCalendario.get(Calendar.DAY_OF_MONTH);
						int mes = diaCalendario.get(Calendar.MONTH) + 1;
						int anio = diaCalendario.get(Calendar.YEAR);
						serie.add(new Day(dia, mes, anio), dato.doubleValue());
					}
				}
				coleccionSeries.addSeries(serie);
			}
		} catch (Exception e) {
			throw e;
		}
		return coleccionSeries;
	}

}
