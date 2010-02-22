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

	public void paint(Graphics2D g2d, Object obj) {
		try {
			String pTituloGrafica = "Grafica de nitrogeno";
			String pTituloVariables = "Variables";
			String pTituloEjeY = "Calendario";
			JFreeChart chart;
			chart = GraficaEurodollar.crearGrafica(this.getEJEFachada(
					ServicioEJE.Ejecucion).obtenerMapaResultado(
					new ResultadoMSCC()), "SHSA,HUMR", pTituloGrafica, pTituloVariables,
					pTituloEjeY, 600, 500);
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
	
}