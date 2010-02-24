package com.bean.eje;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import org.jfree.chart.JFreeChart;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioEJE;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;

public class PaintBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fontSize;

	// Grafica WUL y WLL vs TIME en lineas y RAIN vs TIME en barras de una
	// grafica tipo "Eurodollar futures..."
	public void paintEurodollar(Graphics2D g2d, Object obj) {
		try {
			String pTituloGrafica = "Grafica de nitrogeno";
			String pTituloVariables = "Variables";
			String pTituloEjeY = "Calendario";
			String pLineas = "WUL,WLL";
			String pBarras = "RAIN";
			JFreeChart chart;
//			chart = GraficaEurodollar.crearGrafica(this.getEJEFachada(
//					ServicioEJE.Ejecucion).obtenerMapaResultado(
//					new ResultadoMSCC()), pLineas, pTituloGrafica,
//					pTituloVariables, pTituloEjeY, 600, 500);
			chart = GraficaEurodollar.createChart();
			
			// crearGrafica(Map<String, ArrayList> pDatos,
			// String pTituloGrafica, String pTituloVariables, String
			// pTituloEjeY,
			// int width, int height)
			BufferedImage image = chart.createBufferedImage(600, 500,
					BufferedImage.TYPE_INT_RGB, null);
			Graphics2D g = image.createGraphics();
			// PaintData data = (PaintData) obj;
			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	// que grafique cualquier variable contra cualquier variable de las
	// disponibles en output_modelo_trigo.txt en graficas tipo "Scatter plot"
	public void paintScatterPlot(Graphics2D g2d, Object obj) {
		try {
//			String pTituloGrafica = "Grafica de nitrogeno";
//			String pTituloVariables = "Variables";
//			String pTituloEjeY = "Calendario";
//			JFreeChart chart;
//			chart = GraficaScatterPlot.crearGrafica(this.getEJEFachada(
//					ServicioEJE.Ejecucion).obtenerMapaResultado(
//					new ResultadoMSCC()), "SHSA,HUMR", pTituloGrafica,
//					pTituloVariables, pTituloEjeY, 600, 500);
//			// crearGrafica(Map<String, ArrayList> pDatos,
//			// String pTituloGrafica, String pTituloVariables, String
//			// pTituloEjeY,
//			// int width, int height)
//			BufferedImage image = chart.createBufferedImage(600, 500,
//					BufferedImage.TYPE_INT_RGB, null);
//			Graphics2D g = image.createGraphics();
//			// PaintData data = (PaintData) obj;
//			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	// Grafica las variables CSH y LAI vs TIME en grafica tipo XYSplineRenderer
	public void paintXYSplineRendererCSH_LAI(Graphics2D g2d, Object obj) {
		try {
//			String pTituloGrafica = "Grafica de nitrogeno";
//			String pTituloVariables = "Variables";
//			String pTituloEjeY = "Calendario";
//			JFreeChart chart;
//			chart = GraficaXYSplineRenderer.crearGrafica(this.getEJEFachada(
//					ServicioEJE.Ejecucion).obtenerMapaResultado(
//					new ResultadoMSCC()), "SHSA,HUMR", pTituloGrafica,
//					pTituloVariables, pTituloEjeY, 600, 500);
//			// crearGrafica(Map<String, ArrayList> pDatos,
//			// String pTituloGrafica, String pTituloVariables, String
//			// pTituloEjeY,
//			// int width, int height)
//			BufferedImage image = chart.createBufferedImage(600, 500,
//					BufferedImage.TYPE_INT_RGB, null);
//			Graphics2D g = image.createGraphics();
//			// PaintData data = (PaintData) obj;
//			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	// grafica NN y NUPTN vs TIME en grafica tipo XYSplineRenderer
	public void paintXYSplineRendererNN_NUPTN(Graphics2D g2d, Object obj) {
		try {
//			String pTituloGrafica = "Grafica de nitrogeno";
//			String pTituloVariables = "Variables";
//			String pTituloEjeY = "Calendario";
//			JFreeChart chart;
//			chart = GraficaXYSplineRenderer.crearGrafica(this.getEJEFachada(
//					ServicioEJE.Ejecucion).obtenerMapaResultado(
//					new ResultadoMSCC()), "SHSA,HUMR", pTituloGrafica,
//					pTituloVariables, pTituloEjeY, 600, 500);
//			// crearGrafica(Map<String, ArrayList> pDatos,
//			// String pTituloGrafica, String pTituloVariables, String
//			// pTituloEjeY,
//			// int width, int height)
//			BufferedImage image = chart.createBufferedImage(600, 500,
//					BufferedImage.TYPE_INT_RGB, null);
//			Graphics2D g = image.createGraphics();
//			// PaintData data = (PaintData) obj;
//			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}
}