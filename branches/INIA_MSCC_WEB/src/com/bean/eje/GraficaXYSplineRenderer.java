package com.bean.eje;

/*
 * ventanaGrafica.java
 *
 * Created on 9 de agosto de 2003, 9:11
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * @author Roberto Canales
 */
public class GraficaXYSplineRenderer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics2D g;
	BufferedImage grafica = null;
	/** Creates new form ventanaGrafica */
	public GraficaXYSplineRenderer() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

//		addWindowListener(new java.awt.event.WindowAdapter() {
//			public void windowClosing(java.awt.event.WindowEvent evt) {
//				exitForm(evt);
//			}
//		});
//
//		pack();
	}

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		XYSeriesCollection dataset = new XYSeriesCollection();  
	       XYSeries serie = new XYSeries("Legenda");  
	         
	       serie.add(0, 0);  
	       serie.add(10, 43);  
	       serie.add(15, 52);  
	       serie.add(20, 57);  
	       serie.add(26, 62.8);  
	       serie.add(34, 56.7);  
	        
	       
	       XYSeries series1 = new XYSeries("Primero");
			series1.add(10.0, 1.0);
			series1.add(20.0, 24.0);
			series1.add(30.0, 33.0);
			series1.add(40.0, 44.0);
			series1.add(50.0, 55.0);
			series1.add(60.0, 76.0);
			series1.add(70.0, 77.70);
			series1.add(80.0, 87.0);
			XYSeries series2 = new XYSeries("Segundo");
			series2.add(10.0, 25.0);
			series2.add(20.0, 37.0);
			series2.add(30.0, 46.0);
			series2.add(40.0, 58.0);
			series2.add(50.0, 64.0);
			series2.add(60.0, 74.0);
			series2.add(70.0, 82.0);
			series2.add(80.0, 91.0);
			XYSeries series3 = new XYSeries("Tercero");
			series3.add(30.0, 4.0);
			series3.add(40.0, 3.0);
			series3.add(50.0, 2.0);
			series3.add(60.0, 3.0);
			series3.add(70.0, 6.0);
			series3.add(80.0, 3.0);
			series3.add(90.0, 4.0);
			series3.add(100.0, 3.0);
//			XYSeriesColle0ction dataset = new XYSeriesCollection();
			dataset.addSeries(series1);
			dataset.addSeries(series2);
			dataset.addSeries(series3);
	       
	       
	       
	       
	       
	        
	       dataset.addSeries(serie);  
	       ValueAxis axis1 = new NumberAxis("Eixo X");     
	        ValueAxis axis2 = new NumberAxis("Tiemp en dias desde la siembra");  
	        XYSplineRenderer renderer = new XYSplineRenderer();  
	        renderer.setShapesVisible(false);
	        renderer.setSeriesPaint(0, Color.BLUE);  
	        XYPlot plot = new XYPlot(dataset, axis1, axis2, renderer);     
	        JFreeChart chart = new JFreeChart(plot);     
	        
	        

//			JFreeChart chart2 = ChartFactory.createXYLineChart(
//					"Sesiones en Adictos al Trabajo", "Meses", "Sesiones",
//					dataset, PlotOrientation.VERTICAL, false, false, true // Show
//																				// legend
//					);
//	        chart2.getPlot().setRenderer(new XYSplineRenderer());
	        
	        ChartPanel panel = new ChartPanel(chart);  
	        
	        
	        
	        JFrame f = new JFrame("T�tulo da Janela Gr�fico");  
	        f.setSize(640, 480);     
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     
	        f.getContentPane().add(panel);     
	        f.setVisible(true);  
		
//		GraficaXYSplineRenderer miventana = new GraficaXYSplineRenderer();
//		miventana.setSize(400, 400);
//		miventana.show();
	}

	public BufferedImage creaImagen() {
		XYSeries series = new XYSeries("Evolucion");
		series.add(1, 23);
		series.add(2, 34);
		series.add(3, 51);
		series.add(4, 67);
		series.add(5, 89);
		series.add(6, 121);
		series.add(7, 137);
		XYDataset juegoDatos = new XYSeriesCollection(series);

		JFreeChart chart = ChartFactory.createXYLineChart(
				"Sesiones en Adictos al Trabajo", "Meses", "Sesiones",
				juegoDatos, PlotOrientation.VERTICAL, false, false, true // Show
																			// legend
				);

		BufferedImage image = chart.createBufferedImage(300, 300);
		return image;
	}

	public void paint(java.awt.Graphics g) {
		// super.paint(g);

		if (grafica == null) {
			grafica = this.creaImagen();
		}
		g.drawImage(grafica, 30, 30, null);
	}

	public Graphics2D getG() {
		return g;
	}

	public void setG(Graphics2D g) {
		this.g = g;
	}

}