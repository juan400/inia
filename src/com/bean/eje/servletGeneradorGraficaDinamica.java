package com.bean.eje;

/*
 *
 * servletGeneradorGraficaDinamica.java
 *
 * Created on 21 de septiembre de 2003, 17:46
 */
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Roberto Canales
 * @version
 */

/**
 * Servlet implementation class servletGeneradorGraficaDinamica
 */
public class servletGeneradorGraficaDinamica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JFreeChart crearChart(int array[]) {
		// XYSeries series = new XYSeries("Evolucion Sesiones");
		//
		// // inicializamos los valores pasados
		// for (int i=0; i<array.length; i++)
		// {
		// series.add(i, array[i]);
		// }
		//
		// XYDataset juegoDatos= new XYSeriesCollection(series);
		//
		// JFreeChart chart =
		// ChartFactory.createLineXYChart("Sesiones en Adictos al Trabajo",
		// "Meses", "Sesiones", juegoDatos,
		// PlotOrientation.VERTICAL,
		// true,true,true
		// );
		GraficaEurodollar demo = new GraficaEurodollar(
				"Price Volume Chart Demo");
		// demo.pack();
		// RefineryUtilities.centerFrameOnScreen(demo);
		// demo.setVisible(true);

		return demo.createChart();
	}

	int getParamEntero(HttpServletRequest request, String pNombre, int pDefecto) {
		String param = request.getParameter(pNombre);

		if (param == null || param.compareTo("") == 0) {
			return pDefecto;
		}

		return Integer.parseInt(param);

	}

	/**
	 * Processes requests for both HTTP GET and POST methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");

		OutputStream salida = response.getOutputStream();

		int ancho = getParamEntero(request, "ancho", 300);
		int alto = getParamEntero(request, "alto", 300);

		// esto es una ñapa paro de momento solo queremos demostrar que funciona
		int iNumeroElemento = getParamEntero(request, "elementos", 3);

		int array[] = new int[iNumeroElemento];

		// leemos variables al estilo y0=1 y1=2 y3=22
		for (int i = 0; i < array.length; i++) {
			array[i] = getParamEntero(request, "y" + i, 0);
		}

		JFreeChart grafica = crearChart(array);
		ChartUtilities.writeChartAsJPEG(salida, grafica, ancho, alto);

		salida.close();
	}

	/**
	 * Handles the HTTP GET method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}