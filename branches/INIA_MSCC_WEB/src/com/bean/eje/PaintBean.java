package com.bean.eje;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.jfree.chart.JFreeChart;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioEJE;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;
import com.inia_mscc.modulos.gem.entidades.Archivo;

public class PaintBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String varUno;
	private String varDos;

	public boolean isInit() {
		varDos = "NNLL";
		varUno = "CLVD";
		return false;
	}

	/**
	 * Grafica WUL y WLL vs TIME en lineas y RAIN vs TIME en barras de una
	 * grafica tipo "Eurodollar futures..."
	 * 
	 * @param g2d
	 * @param obj
	 */
	public void paintEurodollar(Graphics2D g2d, Object obj) {
		try {
			String pTituloGrafica = "Balance de agua en el suelo";
			JFreeChart chart;
			ResultadoMSCC resultado = new ResultadoMSCC();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
					new Date(), Estado.Activo, TipoExtencionArchivo.txt,
					ubicacion);
			archivo.set_datos(new File(
					"C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			resultado.set_archivo(archivo);
			chart = Graficador.createTipoEurodollar(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resultado), null);
			BufferedImage image = chart.createBufferedImage(600, 500,
					BufferedImage.TYPE_INT_RGB, null);
			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	/**
	 * Grafica las variables CSH y LAI vs TIME en grafica tipo XYSplineRenderer
	 * 
	 * @param g2d
	 * @param obj
	 */
	public void paintXYSplineRendererCSH_LAI(Graphics2D g2d, Object obj) {
		try {
			String pTituloGrafica = "Acumulación de biomasa y Indice de area foliar";
			String pLineas = "CSH,LAI";
			JFreeChart chart;
			ResultadoMSCC resultado = new ResultadoMSCC();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
					new Date(), Estado.Activo, TipoExtencionArchivo.txt,
					ubicacion);
			archivo.set_datos(new File(
					"C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			resultado.set_archivo(archivo);
			chart = Graficador.createTipoXYSpline(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resultado), pLineas);
			BufferedImage image = chart.createBufferedImage(600, 500,
					BufferedImage.TYPE_INT_RGB, null);
			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	/**
	 * grafica NN y NUPTN vs TIME en grafica tipo XYSplineRenderer
	 * 
	 * @param g2d
	 * @param obj
	 */
	public void paintXYSplineRendererNN_NUPTN(Graphics2D g2d, Object obj) {
		try {
			String pTituloGrafica = "Balance de nitrogeno en suelo";
			String pLineas = "NN,NUPTN";
			JFreeChart chart;
			ResultadoMSCC resultado = new ResultadoMSCC();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
					new Date(), Estado.Activo, TipoExtencionArchivo.txt,
					ubicacion);
			archivo.set_datos(new File(
					"C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			resultado.set_archivo(archivo);

			chart = Graficador.createTipoXYSpline(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resultado), pLineas);

			BufferedImage image = chart.createBufferedImage(600, 500,
					BufferedImage.TYPE_INT_RGB, null);
			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	/**
	 * Grafica cualquier variable contra cualquier variable de las disponibles
	 * en output_modelo_trigo.txt en graficas tipo "Scatter plot"
	 * 
	 * @param g2d
	 * @param obj
	 */
	public void paintScatterPlot(Graphics2D g2d, Object obj) {
		try {
			String pLineas = "";
			String pTituloGrafica = "Grafica resultante de referencia "
					+ this.getVarUno() + " comparada con " + this.getVarDos();
			if (this.getVarUno() != null && this.getVarDos() != null
					&& !this.getVarUno().isEmpty()
					&& !this.getVarDos().isEmpty()) {
				pLineas = this.getVarUno() + "," + this.getVarDos();
			}
			JFreeChart chart;
			ResultadoMSCC resultado = new ResultadoMSCC();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
					new Date(), Estado.Activo, TipoExtencionArchivo.txt,
					ubicacion);
			archivo.set_datos(new File(
					"C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			resultado.set_archivo(archivo);

			chart = Graficador.createTipoScatter(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resultado), pLineas);

			BufferedImage image = chart.createBufferedImage(600, 500,
					BufferedImage.TYPE_INT_RGB, null);
			g2d.drawImage(image, null, 0, 0);
		} catch (Exception e) {
			this.setError(e.getMessage());
		}
	}

	public String getVarUno() {
		return varUno;
	}

	public void setVarUno(String varUno) {
		this.varUno = varUno;
	}

	public String getVarDos() {
		return varDos;
	}

	public void setVarDos(String varDos) {
		this.varDos = varDos;
	}

}