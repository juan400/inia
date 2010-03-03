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
	private String ubicacionEjegida;
	private Ubicacion ubicacion = new Ubicacion();
	private boolean disableTipoArchivo = false;

	public String registrar(){
		return "ADM010";
	}
	
	public void validarURL() {
		String url = pathDirectorio.replace("\\","\\\\");
//		   File directorio = new File("c:\\ArchivosSubidos\\directorio");
		   File directorio = new File(url);
		   if (!directorio.isDirectory()){
			   this.setError("La ruta ingresada no es un directorio valido.");
			   System.out.println("La ruta ingresada no es un directorio valido.");
		   }else{
			   System.out.println("La ruta ingresada es un directorio valido.");
		   }
	}

	public String nuevo() {
		if (!this.existeUbicacionTipoArchivo()) {
			this.setUbicacion(new Ubicacion());
			this.getUbicacion().set_tipoArchivo(TipoArchivo.valueOf(tipoArchvo));
			this.getUbicacion().set_urlPaht(pathDirectorio);
		}
		return "ADM010";
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

	public String actualizar() {
		if (this.getUbicacion().get_id() == (long) Long
				.parseLong(ubicacionEjegida)) {
			this.getUbicacion()
					.set_tipoArchivo(TipoArchivo.valueOf(tipoArchvo));
			this.getUbicacion().set_urlPaht(pathDirectorio);
			Iterator<Ubicacion> it = ubicaciones.iterator();
			while (it.hasNext()) {
				Ubicacion ubicacionSeleccionada = (Ubicacion) it.next();
				if (ubicacionSeleccionada.get_id() == (long) Long
						.parseLong(ubicacionEjegida)) {
					// if (!this.existeUbicacionTipoArchivo()) {
					ubicacionSeleccionada = this.getUbicacion();
					this.setDisableTipoArchivo(false);
					// }
				}
			}
		}
		return "ADM010";
	}

	public String verUbicacion() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String ubicacionEjegida = (String) paramMap.get("ubicacionEjegida");
		Iterator<Ubicacion> it = ubicaciones.iterator();
		while (it.hasNext()) {
			Ubicacion ubicacionSeleccionada = (Ubicacion) it.next();
			if (ubicacionSeleccionada.get_id() == (long) Long
					.parseLong(ubicacionEjegida)) {
				this.setUbicacion(ubicacionSeleccionada);
				this.setDisableTipoArchivo(true);
			}
		}
		return "resultados";
	}

	public boolean isInit() {
		tipos = new SelectItem[TipoArchivo.values().length];
		SelectItem si = new SelectItem(TipoArchivo.Climatologico.name());
		tipos[0] = si;
		si = new SelectItem(TipoArchivo.Eejcucion.name(), "Ejecución");
		tipos[1] = si;
		si = new SelectItem(TipoArchivo.Escenario.name());
		tipos[2] = si;
		si = new SelectItem(TipoArchivo.ModeloSimulacion.name(),
				"Modelo de simulación");
		tipos[3] = si;
		si = new SelectItem(TipoArchivo.ParametrosClimaticos.name(),
				"Parámetros climáticos");
		tipos[4] = si;
		si = new SelectItem(TipoArchivo.Resultados.name());
		tipos[5] = si;
		tipoArchvo = tipos[0].getValue().toString();
		// this.getUbicacion().set_tipoArchivo(TipoArchivo.Eejcucion);
		return false;
	}

	public UbicacionBean() {
		try {
			this.setUbicaciones(this.getAdmFachada(ServicioADM.Ubicacion)
					.ObtenerUbicacions(null));
			this.setUbicaciones(new ArrayList<Ubicacion>());
			tipos = new SelectItem[TipoArchivo.values().length];
			SelectItem si = new SelectItem(TipoArchivo.Climatologico.name());
			tipos[0] = si;
			si = new SelectItem(TipoArchivo.Eejcucion.name(), "Ejecución");
			tipos[1] = si;
			si = new SelectItem(TipoArchivo.Escenario.name());
			tipos[2] = si;
			si = new SelectItem(TipoArchivo.ModeloSimulacion.name(),
					"Modelo de simulación");
			tipos[3] = si;
			si = new SelectItem(TipoArchivo.ParametrosClimaticos.name(),
					"Parámetros climáticos");
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

}
