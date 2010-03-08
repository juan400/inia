package com.bean.eje;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioEJE;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class EjecucionBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fechaEjecucion = new Date();
	private Date fSiembra = new Date();
	private String estacionClimatica = "LE";
	private String cultivar = "DonAlberto";
	private boolean proyectarClima = false;
	private List<Cultivo> listaCultivos;
	private SelectItem[] cultivos;
	private String cultivoElegido;
	private Cultivo cultivo;

	private Escenario escenario;
	private Modelo modelo;

	private List<Region> listaRegiones;
	private SelectItem[] regiones;
	private String regionElegida;
	private Region region;

	private ListaCriterioSeleccion listaRate;
	private ListaCriterioSeleccion listaFertilizantes;

	// Fertilizacion
	private Date fFertilizacionSiembra = new Date();
	private String fuenteFertilizacionSiembra = "18-46-0";
	private String rateFertilizacionSiembra = "100";

	// Refertilizacion 1
	private Date fRefertilizacion1 = new Date();
	private String fuenteRefertilizacion1 = "18-46-0";
	private String rateRefertilizacion1 = "100";

	// Refertilizacion 2
	private Date fRefertilizacion2 = new Date();
	private String fuenteRefertilizacion2 = "18-46-0";
	private String rateRefertilizacion2 = "100";

	private String nombreSueloConeat = "10.1";
	private double profundidadA = 0.2;
	private double profundidadB = 0.2;
	private double densidadPlantas = 200;
	private double wuli = 50;
	private double wlli = 50;
	private double dpmi = 200;
	private double rpmi = 100;
	private double humi = 2.0;
	private double nauli = 10;
	private double nalli = 10;
	private double nnuli = 20;
	private double nnlli = 20;

	public EjecucionBean() {
		super();
	}

	public boolean isInit() {
		try {
			this.setListaCultivos(this.getGEMFachada(ServicioGEM.Cultivo)
					.ObtenerCultivos(null));
			if (this.getListaCultivos() == null) {
				this.setListaCultivos(new ArrayList<Cultivo>());
			}
			cultivos = new SelectItem[listaCultivos.size() + 1];
			cultivos[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Cultivo c : listaCultivos) {
				SelectItem si = new SelectItem(c.get_nombre());
				cultivos[i] = si;
				i++;
			}
			cultivoElegido = this.getTextBundleKey("combo_seleccione");
			this.setListaRegiones(this.getAdmFachada(ServicioADM.Region)
					.ObtenerRegiones());
			if (this.getListaRegiones() == null) {
				this.setListaRegiones(new ArrayList<Region>());
			}
			regiones = new SelectItem[listaRegiones.size() + 1];
			regiones[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			i = 1;
			for (Region c : listaRegiones) {
				SelectItem si = new SelectItem(c.get_codigo(), c.get_nombre(),
						c.get_descripcion());
				regiones[i] = si;
				i++;
			}
			regionElegida = this.getTextBundleKey("combo_seleccione");
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public String ejecutarEscenario() {
		String resultado = "";
		try {

			EjecucionMSCC ejecucionMSCC = new EjecucionMSCC();
			// Escenario escenario = new Escenario();
			// Cultivo cultivo = new Cultivo();

			escenario.set_cultivo(cultivo);
			ejecucionMSCC.set_modelo(modelo);

			cultivo.set_listaPropiedades(this.armarListaPropiedades());

			// TODO Reemplazar rutas de los archivos por las que correspondan.
			// Archivo archivoTemplate = new Archivo();
			// archivoTemplate.set_datos(new File(
			// "C:/INIA/Templates/TemplateEscenario.py"));

			// Archivo archivoResultado = new Archivo();
			// archivoResultado.set_datos(new File("C:/INIA/resultado.py"));

			// private Ubicacion UbicacionCarpetaTemporalEjecucion(){
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.set_tipoArchivo(TipoArchivo.Ejecucion);
			ubicacion = this.getAdmFachada(ServicioADM.Ubicacion)
					.ObtenerUbicacion(ubicacion);
			if (ubicacion == null) {
				this
						.setError("No hay definida una ubicación para los archivos de ejecución.");
			}
			ubicacion.set_urlPaht(ubicacion.get_urlPaht());
			// return ubicacion;
			// }

			// Archivo archivoEjecucion = new Archivo();
			this.setUsuario((Usuario) this.getSesion(Usuario.class.toString()));
			Archivo archivoEjecucion = new Archivo(getUsuario().get_login(),
					TipoArchivo.Ejecucion, new Date(), Estado.Activo,
					TipoExtencionArchivo.py, ubicacion);
			// archivoSubido.set_datos(this.getFiles().get(0).getFile());
			archivoEjecucion.set_usuario(this.getUsuario());

			archivoEjecucion.set_ubicacion(ubicacion);
			ejecucionMSCC.set_archivoEjecucion(archivoEjecucion);
			// ejecucionMSCC.get_modelo().get_escenario().set_archivoEscenario(
			// archivoTemplate);

			this.generarEscenario(ejecucionMSCC);
			resultado = "EJE002";
		} catch (Exception ex) {
			this.setError(ex.getLocalizedMessage());
		}
		return resultado;
	}

	private boolean generarEscenario(EjecucionMSCC ejecucionMSCC) {
		try {
			this.getEJEFachada(ServicioEJE.Ejecucion).generarArchivoEjecucion(
					ejecucionMSCC);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private List<Propiedad> armarListaPropiedades() throws Exception {
		List<Propiedad> propiedades = null;
		try {
			propiedades = new ArrayList<Propiedad>();

			Propiedad propiedad = new Propiedad();
			propiedad.set_codigo("KeyCorrida");
			propiedad.set_valor("Key_de_corrida");
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("FSirmbra");
			propiedad.set_valor(this.formatearFecha(fSiembra));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("EstacionClimatica");
			propiedad.set_valor(this.estacionClimatica);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Cultivar");
			propiedad.set_valor(this.cultivar);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Fecha1");
			propiedad.set_valor(this.formatearFecha(fFertilizacionSiembra));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Fuente1");
			propiedad.set_valor(this.fuenteFertilizacionSiembra);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Rate1");
			propiedad.set_valor(this.rateFertilizacionSiembra);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Fecha2");
			propiedad.set_valor(this.formatearFecha(fRefertilizacion1));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Fuente2");
			propiedad.set_valor(this.fuenteRefertilizacion1);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Rate2");
			propiedad.set_valor(this.rateRefertilizacion1);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Fecha3");
			propiedad.set_valor(this.formatearFecha(fRefertilizacion2));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Fuente3");
			propiedad.set_valor(this.fuenteRefertilizacion2);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("Rate3");
			propiedad.set_valor(this.rateRefertilizacion2);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("NombreSuelo");
			propiedad.set_valor(this.nombreSueloConeat);
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("ProfundidadA");
			propiedad.set_valor(String.valueOf(this.profundidadA));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("ProfundidadB");
			propiedad.set_valor(String.valueOf(this.profundidadB));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("DensidadPlantas");
			propiedad.set_valor(String.valueOf(this.densidadPlantas));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("WULI");
			propiedad.set_valor(String.valueOf(this.wuli));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("WLLI");
			propiedad.set_valor(String.valueOf(this.wlli));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("DPMI");
			propiedad.set_valor(String.valueOf(this.dpmi));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("RPMI");
			propiedad.set_valor(String.valueOf(this.rpmi));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("HUMI");
			propiedad.set_valor(String.valueOf(this.humi));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("NAULI");
			propiedad.set_valor(String.valueOf(this.nauli));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("NALLI");
			propiedad.set_valor(String.valueOf(this.nalli));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("NNULI");
			propiedad.set_valor(String.valueOf(this.nnuli));
			propiedades.add(propiedad);

			propiedad = new Propiedad();
			propiedad.set_codigo("NNLLI");
			propiedad.set_valor(String.valueOf(this.nnlli));
			propiedades.add(propiedad);
		} catch (Exception ex) {
			throw ex;
		}
		return propiedades;
	}

	public void takeSelectionCultivo() {
		this.setError("");
		this.setExito("");
		try {
			cultivo = new Cultivo();
			if (!this.getCultivoElegido().isEmpty()
					&& !this.getCultivoElegido().equals(
							this.getTextBundleKey("combo_seleccione"))) {
				this.setCultivo(BuscarCultivo(this.getCultivoElegido()));
				Escenario esce = new Escenario();
				esce.set_fechaHora(null);
				esce.set_cultivo(this.getCultivo());
				if (this.getRegion() != null) {
					esce.set_region(this.getRegion());
					this.setEscenario(this.getGEMFachada(ServicioGEM.Escenario)
							.ObtenerEscenario(esce));
					if (this.getEscenario() == null) {
						this.setError("No hay escenarios registrados "
								+ "para la estación climática "
								+ "y el cultivo escogidos.");
					} else {
						Modelo modelo = new Modelo();
						modelo.set_fechaHora(null);
						modelo.set_escenario(this.getEscenario());
						modelo = this.getGEMFachada(ServicioGEM.Modelo)
								.ObtenerModelo(modelo);
						if (modelo == null) {
							this
									.setError("No hay modelos de sinulación registrados "
											+ "para la estación climática "
											+ "y el cultivo escogidos.");
						}
					}
				}
				// this.setDisableRegion(false);
				// this.setDisableUpload(true);
				// recargo = false;
			} else {
				this.setCultivo(null);
				// this.setDisableRegion(true);
				// this.setDisableUpload(true);
			}
			regiones = new SelectItem[listaRegiones.size() + 1];
			regiones[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Region c : listaRegiones) {
				SelectItem si = new SelectItem(c.get_codigo(), c.get_nombre(),
						c.get_descripcion());
				regiones[i] = si;
				i++;
			}
			regionElegida = this.getTextBundleKey("combo_seleccione");
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionRegion() {
		this.setError("");
		this.setExito("");
		try {
			region = new Region();
			if (!this.getRegionElegida().isEmpty()
					&& !this.getRegionElegida().equals(
							this.getTextBundleKey("combo_seleccione"))) {
				this.setRegion(BuscarRegion(this.getRegionElegida()));
				Escenario esce = new Escenario();
				esce.set_fechaHora(null);
				esce.set_region(this.getRegion());
				if (this.getCultivo() != null) {
					esce.set_cultivo(this.getCultivo());
					this.setEscenario(this.getGEMFachada(ServicioGEM.Escenario)
							.ObtenerEscenario(esce));
					if (this.getEscenario() == null) {
						this.setError("No hay escenarios registrados "
								+ "para la estación climática "
								+ "y el cultivo escogidos.");
					} else {
						Modelo modelo = new Modelo();
						modelo.set_fechaHora(null);
						modelo.set_escenario(this.getEscenario());
						this.setModelo(this.getGEMFachada(ServicioGEM.Modelo)
								.ObtenerModelo(modelo));
						if (this.getModelo() == null) {
							this
									.setError("No hay modelos de sinulación registrados "
											+ "para la estación climática "
											+ "y el cultivo escogidos.");
						}
					}
				}
				// this.setDisableUpload(false);
				// this.setError("");
				// this.setExito("");
				// recargo = false;
			} else {
				this.setRegion(null);
				// this.setDisableUpload(true);
				// this.setError("");
				// this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	private Cultivo BuscarCultivo(String pCodigo) {
		Cultivo cultivoBuscado = null;
		if (this.getListaCultivos() != null
				&& !this.getListaCultivos().isEmpty()) {
			for (Cultivo cultivo : this.getListaCultivos()) {
				if (cultivo.get_nombre().equals(pCodigo)) {
					cultivoBuscado = cultivo;
				}
			}
		}
		return cultivoBuscado;
	}

	private Region BuscarRegion(String pCodigo) {
		Region regionBuscada = null;
		if (this.getListaRegiones() != null
				&& !this.getListaRegiones().isEmpty()) {
			for (Region region : this.getListaRegiones()) {
				if (region.get_codigo().equals(pCodigo)) {
					regionBuscada = region;
				}
			}
		}
		return regionBuscada;
	}

	private String formatearFecha(Date fecha) {
		String fechaFormateada = "";
		Calendar gc = new GregorianCalendar();
		gc.setTime(fecha);

		fechaFormateada = gc.get(Calendar.YEAR) + ","
				+ (gc.get(Calendar.MONTH) + 1) + ","
				+ gc.get(Calendar.DAY_OF_MONTH);

		return fechaFormateada;
	}

	public Date getfSiembra() {
		return fSiembra;
	}

	public void setfSiembra(Date fSiembra) {
		this.fSiembra = fSiembra;
	}

	public String getEstacionClimatica() {
		return estacionClimatica;
	}

	public void setEstacionClimatica(String estacionClimatica) {
		this.estacionClimatica = estacionClimatica;
	}

	public String getCultivar() {
		return cultivar;
	}

	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}

	public String getCultivoElegido() {
		return cultivoElegido;
	}

	public void setCultivoElegido(String cultivoElegido) {
		this.cultivoElegido = cultivoElegido;
	}

	public SelectItem[] getCultivos() {
		return cultivos;
	}

	public void setCultivos(SelectItem[] cultivos) {
		this.cultivos = cultivos;
	}

	public Date getfFertilizacionSiembra() {
		return fFertilizacionSiembra;
	}

	public void setfFertilizacionSiembra(Date fFertilizacionSiembra) {
		this.fFertilizacionSiembra = fFertilizacionSiembra;
	}

	public String getFuenteFertilizacionSiembra() {
		return fuenteFertilizacionSiembra;
	}

	public void setFuenteFertilizacionSiembra(String fuenteFertilizacionSiembra) {
		this.fuenteFertilizacionSiembra = fuenteFertilizacionSiembra;
	}

	public String getRateFertilizacionSiembra() {
		return rateFertilizacionSiembra;
	}

	public void setRateFertilizacionSiembra(String rateFertilizacionSiembra) {
		this.rateFertilizacionSiembra = rateFertilizacionSiembra;
	}

	public Date getfRefertilizacion1() {
		return fRefertilizacion1;
	}

	public void setfRefertilizacion1(Date fRefertilizacion1) {
		this.fRefertilizacion1 = fRefertilizacion1;
	}

	public String getFuenteRefertilizacion1() {
		return fuenteRefertilizacion1;
	}

	public void setFuenteRefertilizacion1(String fuenteRefertilizacion1) {
		this.fuenteRefertilizacion1 = fuenteRefertilizacion1;
	}

	public String getRateRefertilizacion1() {
		return rateRefertilizacion1;
	}

	public void setRateRefertilizacion1(String rateRefertilizacion1) {
		this.rateRefertilizacion1 = rateRefertilizacion1;
	}

	public Date getfRefertilizacion2() {
		return fRefertilizacion2;
	}

	public void setfRefertilizacion2(Date fRefertilizacion2) {
		this.fRefertilizacion2 = fRefertilizacion2;
	}

	public String getFuenteRefertilizacion2() {
		return fuenteRefertilizacion2;
	}

	public void setFuenteRefertilizacion2(String fuenteRefertilizacion2) {
		this.fuenteRefertilizacion2 = fuenteRefertilizacion2;
	}

	public String getRateRefertilizacion2() {
		return rateRefertilizacion2;
	}

	public void setRateRefertilizacion2(String rateRefertilizacion2) {
		this.rateRefertilizacion2 = rateRefertilizacion2;
	}

	public String getNombreSueloConeat() {
		return nombreSueloConeat;
	}

	public void setNombreSueloConeat(String nombreSueloConeat) {
		this.nombreSueloConeat = nombreSueloConeat;
	}

	public double getProfundidadA() {
		return profundidadA;
	}

	public void setProfundidadA(double profundidadA) {
		this.profundidadA = profundidadA;
	}

	public double getProfundidadB() {
		return profundidadB;
	}

	public void setProfundidadB(double profundidadB) {
		this.profundidadB = profundidadB;
	}

	public double getDensidadPlantas() {
		return densidadPlantas;
	}

	public void setDensidadPlantas(double densidadPlantas) {
		this.densidadPlantas = densidadPlantas;
	}

	public double getWuli() {
		return wuli;
	}

	public void setWuli(double wuli) {
		this.wuli = wuli;
	}

	public double getWlli() {
		return wlli;
	}

	public void setWlli(double wlli) {
		this.wlli = wlli;
	}

	public double getDpmi() {
		return dpmi;
	}

	public void setDpmi(double dpmi) {
		this.dpmi = dpmi;
	}

	public double getRpmi() {
		return rpmi;
	}

	public void setRpmi(double rpmi) {
		this.rpmi = rpmi;
	}

	public double getHumi() {
		return humi;
	}

	public void setHumi(double humi) {
		this.humi = humi;
	}

	public double getNauli() {
		return nauli;
	}

	public void setNauli(double nauli) {
		this.nauli = nauli;
	}

	public double getNalli() {
		return nalli;
	}

	public void setNalli(double nalli) {
		this.nalli = nalli;
	}

	public double getNnuli() {
		return nnuli;
	}

	public void setNnuli(double nnuli) {
		this.nnuli = nnuli;
	}

	public double getNnlli() {
		return nnlli;
	}

	public void setNnlli(double nnlli) {
		this.nnlli = nnlli;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public void setProyectarClima(boolean proyectarClima) {
		this.proyectarClima = proyectarClima;
	}

	public boolean isProyectarClima() {
		return proyectarClima;
	}

	public void setListaRate(ListaCriterioSeleccion listaRate) {
		this.listaRate = listaRate;
	}

	public ListaCriterioSeleccion getListaRate() {
		return listaRate;
	}

	public void setListaFertilizantes(ListaCriterioSeleccion listaFertilizantes) {
		this.listaFertilizantes = listaFertilizantes;
	}

	public ListaCriterioSeleccion getListaFertilizantes() {
		return listaFertilizantes;
	}

	public List<Cultivo> getListaCultivos() {
		return listaCultivos;
	}

	public void setListaCultivos(List<Cultivo> listaCultivos) {
		this.listaCultivos = listaCultivos;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public List<Region> getListaRegiones() {
		return listaRegiones;
	}

	public void setListaRegiones(List<Region> listaRegiones) {
		this.listaRegiones = listaRegiones;
	}

	public SelectItem[] getRegiones() {
		return regiones;
	}

	public void setRegiones(SelectItem[] regiones) {
		this.regiones = regiones;
	}

	public String getRegionElegida() {
		return regionElegida;
	}

	public void setRegionElegida(String regionElegida) {
		this.regionElegida = regionElegida;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}
