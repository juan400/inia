package com.bean.seg;

import java.io.Serializable;
import java.util.List;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Perfil;

public class PerfilBean implements Serializable {

	private String nombre;
	private String descripcion;
	private String estado;
	private String error;
	private List<Perfil> perfiles;
	private Perfil perfil;

	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada(Enumerados.Servicio.Perfil);

	public boolean isInit() {
		this.perfiles = segFachada.ObtenerPerfiles();
		boolean retorno = false;

		return retorno;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public List<Perfil> obternerPerfiles() {
		return segFachada.ObtenerPerfiles();
	}

	public String registrar() throws Exception {
		// MaestroBean.getInstance().getTextBundle();
		String retorno = "";
		try {
			Perfil datosPerfil = new Perfil();
			datosPerfil.set_nombre(nombre);
			datosPerfil.set_descripcion(descripcion);
			datosPerfil.set_estado(Enumerados.Estado.valueOf(estado));

			Perfil p = segFachada.RegistrarPerfil(datosPerfil);
			if (p != null) {
				setError("");
				MaestroBean.getInstance()
						.setOpcion("/Servicios/SEG/SEG009.jsp");
				retorno = "registro-ok";
				LimpiarBean();
			} else {
				setError("No ha sido posible registrar el perfil, revise los datos ingresados y intentelo nuevamente.");
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
		perfil = null;
		perfiles= null;
		error= null;
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

}