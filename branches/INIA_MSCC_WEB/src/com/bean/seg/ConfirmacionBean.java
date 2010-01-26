package com.bean.seg;

import java.io.Serializable;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class ConfirmacionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SEGFachada segFachada = new SEGFachada(Enumerados.Servicio.Usuario);

	private String nombre;

	private String contrasenia;
	private String confirmacion;
	private String frase;

	private String error;
	private Long codigoActivacion;
	private Usuario usuario = new Usuario();

	/*
	 * Region de Metodos
	 */
	public boolean isInit() {
		boolean retrono = true;
		if (codigoActivacion != null) {
			Usuario usuario = segFachada
					.ComprobarClaveReigstro(codigoActivacion.toString());
			if (usuario != null) {
				nombre = usuario.get_datos().get_nombre() + " "
						+ usuario.get_datos().get_apellido();
				if (usuario.get_estadoUsuario().equals(
						Enumerados.EstadoUsuario.Activo)) {
					error = "Su usuario esta activado, no es necesario este paso.";
				} else if (usuario.get_estadoUsuario().equals(
						Enumerados.EstadoUsuario.Bloqueado)) {
					error = "Su usuario esta bloqueado, ingrese a recuperar su contraseña.";
				} else if (usuario.get_estadoUsuario().equals(
						Enumerados.EstadoUsuario.Inactivo)) {
					error = "Su usuario esta inactivado, consulte a su administrador.";
				}
			} else {
				error = "Su usuario esta inactivado, consulte a su administrador.";
			}
		} else {
		}
		return retrono;
	}
	
	public String Confirmar(){
		String retorno = "registro-ok";
		if (usuario == null){
			retorno = "registro-error";
		}else{
			
		}
		return retorno;
	}
	
	/**
	 * @return
	 */
	public boolean isActivado() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// Map<String, String> params =
		// context.getExternalContext().getRequestParameterMap();
		// if (!params.get("CodigoActivacion").isEmpty())
		// this.setCodigoActivacion(Long.parseLong(params.get("CodigoActivacion")));
		return MaestroBean.getInstance().isActivado();
	}
	
	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getCodigoActivacion() {
		return codigoActivacion;
	}

	public void setCodigoActivacion(Long codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}
	
}
