package com.bean.eje;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.jfree.chart.JFreeChart;

public class PaintBean {

	private int fontSize;

	public void paint(Graphics2D g2d, Object obj) {

		JFreeChart chart = new GraficaEurodollar("Grafica prueba").createChart();
		BufferedImage image = chart.createBufferedImage(300, 200,BufferedImage.TYPE_INT_RGB, null); 
		Graphics2D g = image.createGraphics();
		PaintData data = (PaintData) obj;		
		g2d.drawImage(image, null, 0, 0);
		
		
		
	}
}