package com.bean.adm;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;

public class RegionBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descripcion;
	private String nombre;
	private Region region = new Region();
	private List<Region> regiones;

	public boolean isInit() {
		this.limpiarBean();
		this.setRegiones(this.getAdmFachada(ServicioADM.Region).ObtenerRegiones());
		return false;
	}

	private void limpiarBean() {
		nombre = "";
		descripcion = "";
		codigo = "";
	}

	public String alta() {
		this.setError("");
		this.setExito("");
		return "Alta";
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {
			region.set_codigo(codigo);
			region.set_descripcion(descripcion);
			region.set_nombre(nombre);
			this.getAdmFachada(ServicioADM.Region).ActualizarRegion(region);
			retorno = "registro-ok";
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String verRegiones() {
		this.alta();
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String regionElegida = (String) paramMap.get("regionElegida");
		Iterator<Region> it = regiones.iterator();
		while (it.hasNext()) {
			Region regionSeleccionada = (Region) it.next();
			if (regionSeleccionada.get_id() == (long) Long
					.parseLong(regionElegida)) {
				region = regionSeleccionada;

				codigo = region.get_codigo();
				descripcion = region.get_descripcion();
				nombre = region.get_nombre();
			}
		}
		return "resultados";
	}

	public String registrar() throws Exception {
		String retorno = "";
		try {
			Region datosRegion = new Region();
			datosRegion.set_codigo(codigo);
			datosRegion.set_descripcion(descripcion);
			datosRegion.set_nombre(nombre);

			Region regCod = this.getAdmFachada(ServicioADM.Region)
					.ComprobarRegionCodigo(datosRegion);
			Region regNom = this.getAdmFachada(ServicioADM.Region)
					.ComprobarRegion(datosRegion);

			if (regCod == null) {
				if (regNom == null) {
					setError("");
					Region r = this.getAdmFachada(ServicioADM.Region)
							.RegistrarRegion(datosRegion);
					if (r != null) {
						this.setError("");
						MaestroBean.getInstance().setOpcion(
								"/Servicios/ADM/ADM005.jsp");
						retorno = "registro-ok";
						limpiarBean();
					} else {
						this
								.setError("No ha sido posible crear la Región, revise los datos ingresados y intentelo nuevamente.");
						retorno = "";
					}
				} else {
					this
							.setError("Ya existe una Región con igual nombre, Por favor ingrese otro nombre.");
					retorno = "";
				}
			} else {
				this
						.setError("Ya existe una Región con igual código, Por favor ingrese otro código.");
				retorno = "";
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}

	public String eliminar() {
		String retorno = "";
		try {
			Map paramMap = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String regionElegida = (String) paramMap.get("regionEliminar");
			Iterator<Region> it = regiones.iterator();
			while (it.hasNext()) {
				Region object = (Region) it.next();
				if ((Long) object.get_id() == Long.parseLong(regionElegida)) {
					region = object;
					nombre = region.get_nombre();
					descripcion = region.get_descripcion();
					codigo = region.get_codigo();
				}
			}
			this.getAdmFachada(ServicioADM.Region).EliminarRegion(region);
			this.setExito("Se ha eliminado exitosamente la región.");
			retorno = "eliminado";

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegiones(List<Region> regiones) {
		this.regiones = regiones;
	}

	public List<Region> getRegiones() {
		return regiones;
	}

}
