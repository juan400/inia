package com.bean.seg;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Perfil;

public class PerfilBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada(Enumerados.Servicio.Perfil);

	private String nombre;
	private String descripcion;
	private String estado;
	private String fijo;
	private String error;
	private List<Perfil> perfiles;
	private Perfil perfil = new Perfil();

	public String eliminar() throws Exception {
		String retorno = "registro-error";
		retorno = "registro-error";
		try {
			Map paramMap = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String consultaElegida = (String) paramMap.get("consultaEliminar");
			Iterator<Perfil> it = perfiles.iterator();
			while (it.hasNext()) {
				Perfil object = (Perfil) it.next();
				if ((Long) object.get_id() == Long.parseLong(consultaElegida)) {
					perfil = object;
					nombre = perfil.get_nombre();
					descripcion = perfil.get_descripcion();
					estado = perfil.get_estado().toString();
				}
			}
			segFachada.EliminarPerfil(perfil);
			retorno = "registro-ok";
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public String verConsulta() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String consultaElegida = (String) paramMap.get("consultaElegida");
		Iterator<Perfil> it = perfiles.iterator();
		while (it.hasNext()) {
			Perfil object = (Perfil) it.next();
			if ((Long) object.get_id() == Long.parseLong(consultaElegida)) {
				perfil = object;
				nombre = perfil.get_nombre();
				descripcion = perfil.get_descripcion();
				estado = perfil.get_estado().toString();
			}
		}
		return "desplegarResultados";
	}

	public PerfilBean() {
		this.estado = Estado.Activo.name();
		this.perfiles = segFachada.ObtenerPerfiles();
	}

	public boolean isInit() {
		boolean retorno = false;

		return retorno;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public List<Perfil> obternerPerfiles() {
		return segFachada.ObtenerPerfiles();
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {

			perfil.set_nombre(nombre);
			perfil.set_descripcion(descripcion);
			perfil.set_estado(Enumerados.Estado.valueOf(estado));

			segFachada.ActualizarPerfil(perfil);
			retorno = "registro-ok";
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}

	public String registrar() throws Exception {
		// MaestroBean.getInstance().getTextBundle();
		String retorno = "";
		try {
			Perfil datosPerfil = new Perfil();
			datosPerfil.set_nombre(nombre);
			datosPerfil.set_descripcion(descripcion);
			datosPerfil.set_estado(Enumerados.Estado.valueOf(estado));

			Perfil per = segFachada.ComprobarPerfil(datosPerfil);
			if (per == null) {
				setError("");
				Perfil p = segFachada.RegistrarPerfil(datosPerfil);
				if (p != null) {
					setError("");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/SEG/SEG009.jsp");
					retorno = "registro-ok";
					LimpiarBean();
				} else {
					setError("No ha sido posible registrar el perfil, revise los datos ingresados y intentelo nuevamente.");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/SEG/SEG009.jsp");
					retorno = "registro-error";
				}
			} else {
				error = "Ya existe un Perfil con igual nombre, Por favor ingrese otro nombre.";
				MaestroBean.getInstance()
						.setOpcion("/Servicios/SEG/SEG009.jsp");
				retorno = "registro-error";
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}

	private void LimpiarBean() {
		nombre = null;
		descripcion = null;
		estado = null;
		perfiles = null;
		error = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getFijo() {
		return fijo;
	}
}