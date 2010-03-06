package com.bean.adm;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioGEM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;

public class UbicacionBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoArchvo;
	private SelectItem[] tipos;
	private String pathDirectorio;
	private List<Ubicacion> ubicaciones;
	// private List<Ubicacion> ubicacionesEliminadas = new
	// ArrayList<Ubicacion>();
	private String ubicacionEjegida;
	private Ubicacion ubicacion = new Ubicacion();
	private boolean disableTipoArchivo = false;
	private boolean disableActualizar = true;
	private boolean disableIngresar = true;

	public String registrar() {
		this.setError("");
		this.setExito("");
		String retorno = "ADM010";
		try {
			if (this.getUbicaciones().size() != 6) {
				this.setError("Debe ingresar ubicación para cada uno de los tipos de archivo.");
				retorno = "";
			} else {
				for (Ubicacion ubicacion : this.getUbicaciones()) {
					if (ubicacion.get_id() == 0) {
						this.getAdmFachada(ServicioADM.Ubicacion)
								.RegistrarUbicacion(ubicacion);
					} else {
						this.getAdmFachada(ServicioADM.Ubicacion)
								.ActualizarUbicacion(ubicacion);
					}
				}
				this.setExito("Se registraron corretamente sus cambios en las ubicaciones.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public void validarURL() {
		String url = pathDirectorio;// .replace("\\", "\\\\");
		File directorio = new File(url);
		if (!directorio.isDirectory()) {
			this.setError("La ruta ingresada no es un directorio valido.");
			System.out.println("La ruta ingresada no es un directorio valido.");
		} else {
			System.out.println("La ruta ingresada es un directorio valido.");
		}
	}

	public String nuevo() {
		this.setError("");
		this.setExito("");
		String retorno = "ADM010";
		String url = pathDirectorio;// .replace("\\", "\\\\");
		File directorio = new File(url);
		if (!directorio.isDirectory()) {
			this.setError("La ruta ingresada no es un directorio valido.");
			retorno = "";
		} else {
			if (!this.existeUbicacionTipoArchivo()) {
				this.setUbicacion(new Ubicacion());
				this.getUbicacion().set_tipoArchivo(
						TipoArchivo.valueOf(tipoArchvo));
				this.getUbicacion().set_urlPaht(pathDirectorio);
				this.getUbicacion().set_grabada(false);
				this.getUbicaciones().add(this.getUbicacion());
				if(this.getUbicaciones().size() == 6){
					this.setDisableIngresar(true);
				}
			}
		}
		return retorno;
	}

	public String actualizar() {
		this.setError("");
		this.setExito("");
		String retorno = "ADM010";
		String url = pathDirectorio;// .replace("\\", "\\\\");
		File directorio = new File(url);
		if (!directorio.isDirectory()) {
			this.setError("La ruta ingresada no es un directorio valido.");
			retorno = "";
		} else {
			if (this.getUbicacion().get_tipoArchivo().equals(
					TipoArchivo.valueOf(tipoArchvo))) {
//				this.getUbicacion().set_tipoArchivo(
//						TipoArchivo.valueOf(tipoArchvo));
				this.getUbicacion().set_urlPaht(pathDirectorio);
				Iterator<Ubicacion> it = ubicaciones.iterator();
				while (it.hasNext()) {
					Ubicacion ubicacionSeleccionada = (Ubicacion) it.next();
					if (ubicacionSeleccionada.get_tipoArchivo().equals(
							TipoArchivo.valueOf(tipoArchvo))) {
						// if (!this.existeUbicacionTipoArchivo()) {
						ubicacionSeleccionada = this.getUbicacion();
						this.setPathDirectorio("");
						this.setTipoArchvo("");
						this.setDisableTipoArchivo(false);
						this.setDisableActualizar(true);
						break;
						// }
					}
				}
			}
		}
		return retorno;
	}

	private boolean existeUbicacionTipoArchivo() {
		boolean existe = false;
		Iterator<Ubicacion> it = ubicaciones.iterator();
		while (it.hasNext()) {
			Ubicacion ubicacionSeleccionada = (Ubicacion) it.next();
			if (ubicacionSeleccionada.get_tipoArchivo().name()
					.equalsIgnoreCase(tipoArchvo)) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	public String verUbicacion() {
		this.setError("");
		this.setExito("");
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		Iterator<Ubicacion> it = ubicaciones.iterator();
		while (it.hasNext()) {
			Ubicacion ubicacionSeleccionada = (Ubicacion) it.next();
			if (ubicacionSeleccionada.get_tipoArchivo().equals(
					TipoArchivo.valueOf((String) paramMap
							.get("ubicacionEjegida")))) {
				this.setUbicacion(ubicacionSeleccionada);
				this.setPathDirectorio(this.getUbicacion().get_urlPaht());
				this
						.setTipoArchvo(this.getUbicacion().get_tipoArchivo()
								.name());
				this.setDisableTipoArchivo(true);
				this.setDisableActualizar(false);
			}
		}
		return "resultados";
	}

	public boolean isInit() {
		try {
			this.setUbicaciones(this.getAdmFachada(ServicioADM.Ubicacion)
					.ObtenerUbicacions(new Ubicacion()));
			if (this.getUbicaciones() == null) {
				this.setUbicaciones(new ArrayList<Ubicacion>());
			}else if(this.getUbicaciones().size() != 6){
				this.setDisableIngresar(false);
			}
			tipos = new SelectItem[TipoArchivo.values().length];
			SelectItem si = new SelectItem(TipoArchivo.Eejcucion.name());// ,
			// "Ejecuci�n");
			tipos[0] = si;
			si = new SelectItem(TipoArchivo.Climatologico.name());
			tipos[1] = si;
			si = new SelectItem(TipoArchivo.Escenario.name());
			tipos[2] = si;
			si = new SelectItem(TipoArchivo.ModeloSimulacion.name());// ,"Modelo de simulaci�n");
			tipos[3] = si;
			si = new SelectItem(TipoArchivo.ParametrosClimaticos.name());// ,"Par�metros clim�ticos");
			tipos[4] = si;
			si = new SelectItem(TipoArchivo.Resultados.name());
			tipos[5] = si;
			tipoArchvo = tipos[0].getValue().toString();
			this.getUbicacion().set_tipoArchivo(TipoArchivo.Eejcucion);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	public UbicacionBean() {
		this.setError("");
		this.setExito("");
		try {

			this.setUbicaciones(this.getAdmFachada(ServicioADM.Ubicacion)
					.ObtenerUbicacions(new Ubicacion()));
			if (this.getUbicaciones() == null) {
				this.setUbicaciones(new ArrayList<Ubicacion>());
			}else if(this.getUbicaciones().size() != 6){
				this.setDisableIngresar(false);
			}
			tipos = new SelectItem[TipoArchivo.values().length];
			SelectItem si = new SelectItem(TipoArchivo.Eejcucion.name());// ,
			// "Ejecuci�n");
			tipos[0] = si;
			si = new SelectItem(TipoArchivo.Climatologico.name());
			tipos[1] = si;
			si = new SelectItem(TipoArchivo.Escenario.name());
			tipos[2] = si;
			si = new SelectItem(TipoArchivo.ModeloSimulacion.name());// ,"Modelo de simulaci�n");
			tipos[3] = si;
			si = new SelectItem(TipoArchivo.ParametrosClimaticos.name());// ,"Par�metros clim�ticos");
			tipos[4] = si;
			si = new SelectItem(TipoArchivo.Resultados.name());
			tipos[5] = si;
			tipoArchvo = tipos[0].getValue().toString();
			// this.getUbicacion().set_tipoArchivo(TipoArchivo.Eejcucion);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String getTipoArchvo() {
		return tipoArchvo;
	}

	public void setTipoArchvo(String tipoArchvo) {
		this.tipoArchvo = tipoArchvo;
	}

	public SelectItem[] getTipos() {
		return tipos;
	}

	public void setTipos(SelectItem[] tipos) {
		this.tipos = tipos;
	}

	public String getPathDirectorio() {
		return pathDirectorio;
	}

	public void setPathDirectorio(String pathDirectorio) {
		this.pathDirectorio = pathDirectorio;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public String getUbicacionEjegida() {
		return ubicacionEjegida;
	}

	public void setUbicacionEjegida(String ubicacionEjegida) {
		this.ubicacionEjegida = ubicacionEjegida;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setDisableTipoArchivo(boolean disableTipoArchivo) {
		this.disableTipoArchivo = disableTipoArchivo;
	}

	public boolean isDisableTipoArchivo() {
		return disableTipoArchivo;
	}

	public void setDisableActualizar(boolean disableActualizar) {
		this.disableActualizar = disableActualizar;
	}

	public boolean isDisableActualizar() {
		return disableActualizar;
	}

	public void setDisableIngresar(boolean disableIngresar) {
		this.disableIngresar = disableIngresar;
	}

	public boolean isDisableIngresar() {
		return disableIngresar;
	}

}
