package com.bean.eje;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.jfree.chart.JFreeChart;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioEJE;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;

public class ResultadoBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fechaEjecucion;
	private String varUno;
	private String varDos;
	private ListaCriterioSeleccion listaOuputs;
	private SelectItem[] variables;
	private ResultadoMSCC resutaldo;

	public ResultadoBean() {
		variables = new SelectItem[1];
		variables[0] = new SelectItem(this.getTextBundleKey("combo_seleccione"));
		varUno = this.getTextBundleKey("combo_seleccione");
		varDos = this.getTextBundleKey("combo_seleccione");
	}

	public boolean isInit() {
		try {
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			ResultadoMSCC resul = new ResultadoMSCC();
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
					new Date(), Estado.Activo, TipoExtencionArchivo.txt,
					ubicacion);
			archivo.set_datos(new File(
					"C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			resul.set_archivo(archivo);
			this.setSesion(ResultadoMSCC.class.toString(), resul);
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

			resutaldo = (ResultadoMSCC) this.getSesion(ResultadoMSCC.class
					.toString());
			ListaCriterioSeleccion unaLista = new ListaCriterioSeleccion();
			unaLista.set_codigo("PAR");
			if (listaOuputs == null) {
				this.setListaOuputs(this.getAdmFachada(
						ServicioADM.ListaCriterio).ObtenerCriterio(unaLista));
			}
			if (this.getListaOuputs() == null) {
				this.setListaOuputs(new ListaCriterioSeleccion());
			}
			if (resutaldo.get_matrizDatos() != null) {
				int i = 1;
				variables = new SelectItem[this.getListaOuputs()
						.get_listaValores().size() + 1];
				variables[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				i = 1;
				for (ValorSeleccion v : this.getListaOuputs()
						.get_listaValores()) {
					// if
					// (resutaldo.get_matrizDatos().containsKey(v.get_codigo()))
					// {
					SelectItem si = new SelectItem(v.get_codigo(), v
							.get_descripcion());
					variables[i] = si;
					i++;
					// }
				}
				varUno = this.getTextBundleKey("combo_seleccione");
				varDos = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public String CargarResultado() {
		try {
			if (resutaldo == null) {
				ListaCriterioSeleccion unaLista = new ListaCriterioSeleccion();
				unaLista.set_codigo("PAR");
				if (listaOuputs == null) {
					this.setListaOuputs(this.getAdmFachada(
							ServicioADM.ListaCriterio)
							.ObtenerCriterio(unaLista));
				}
				if (this.getListaOuputs() == null) {
					this.setListaOuputs(new ListaCriterioSeleccion());
				}
			} else {
				int i = 1;
				Iterator it = resutaldo.get_matrizDatos().entrySet().iterator();
				ArrayList<String> clavesValidas = new ArrayList<String>();
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					clavesValidas.add((String) pairs.getKey());
					i++;
				}

				variables = new SelectItem[this.getListaOuputs()
						.get_listaValores().size() + 1];
				variables[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				i = 1;
				for (ValorSeleccion v : this.getListaOuputs()
						.get_listaValores()) {
					boolean existe = false;
					for (String clave : clavesValidas) {
						if (clave.equals(v.get_codigo())) {
							existe = true;
							break;
						}
					}
					if (existe) {
						SelectItem si = new SelectItem(v.get_codigo(), v
								.get_descripcion());
						variables[i] = si;
						i++;
					}
				}
				varUno = this.getTextBundleKey("combo_seleccione");
				varDos = this.getTextBundleKey("combo_seleccione");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return "EJE002";
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
			chart = Graficador.createTipoEurodollar(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resutaldo), null);
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
			// ResultadoMSCC resultado = new ResultadoMSCC();
			// Ubicacion ubicacion = new Ubicacion();
			// ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			// Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
			// new Date(), Estado.Activo, TipoExtencionArchivo.txt,
			// ubicacion);
			// archivo.set_datos(new File(
			// "C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			// resultado.set_archivo(archivo);
			chart = Graficador.createTipoXYSpline(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resutaldo), pLineas);
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
			// ResultadoMSCC resultado = new ResultadoMSCC();
			// Ubicacion ubicacion = new Ubicacion();
			// ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			// Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
			// new Date(), Estado.Activo, TipoExtencionArchivo.txt,
			// ubicacion);
			// archivo.set_datos(new File(
			// "C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			// resultado.set_archivo(archivo);

			chart = Graficador.createTipoXYSpline(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resutaldo), pLineas);

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
//			PaintData grafica = (PaintData) obj;
//			if (grafica.getVarUno() != null && grafica.getVarDos() != null
//					&& !grafica.getVarUno().isEmpty()
//					&& !grafica.getVarDos().isEmpty()) {
//				pLineas = grafica.getVarUno() + "," + grafica.getVarDos();
//			}
			JFreeChart chart;
			// ResultadoMSCC resultado = new ResultadoMSCC();
			// Ubicacion ubicacion = new Ubicacion();
			// ubicacion.set_urlPaht("C:\\ArchivosSubidos");
			// Archivo archivo = new Archivo("sss", TipoArchivo.Resultados,
			// new Date(), Estado.Activo, TipoExtencionArchivo.txt,
			// ubicacion);
			// archivo.set_datos(new File(
			// "C:\\ArchivosSubidos\\output_modelo_trigo.txt"));
			// resultado.set_archivo(archivo);

			chart = Graficador.createTipoScatter(pTituloGrafica, this
					.getEJEFachada(ServicioEJE.Ejecucion).obtenerMapaResultado(
							resutaldo), pLineas);

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

	public ListaCriterioSeleccion getListaOuputs() {
		return listaOuputs;
	}

	public void setListaOuputs(ListaCriterioSeleccion listaOuputs) {
		this.listaOuputs = listaOuputs;
	}

	public SelectItem[] getVariables() {
		return variables;
	}

	public void setVariables(SelectItem[] variables) {
		this.variables = variables;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

}