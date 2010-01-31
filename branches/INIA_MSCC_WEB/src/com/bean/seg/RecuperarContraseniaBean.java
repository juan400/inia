package com.bean.seg;

import java.io.Serializable;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;

public class RecuperarContraseniaBean extends MaestroBean implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String loginName;
	private String email;
	private String contrasenia;
	private String confirmacion;
	private String frase;
	private boolean existeEmail;

	public void validarEmail() {
		try {
			if (super.getSegFachada(Servicio.Usuario).ComprobarEmail(email)) {
				super
						.setError("El e-mail ingresado ya esta registrado en el sistema.");
			} else {
				super.setError("");
				super.setUsuario(super.getSegFachada(Servicio.Usuario)
						.ObtenerUsuarioXDatos(null, null, null, email, null));
				nombre = super.getUsuario().get_datos().get_nombre();
				loginName = super.getUsuario().get_login();
				existeEmail = true;
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	public void validarFrase() {

	}

	public void setExisteEmail(boolean existeEmail) {
		this.existeEmail = existeEmail;
	}

	public boolean isExisteEmail() {
		return existeEmail;
	}

	public boolean isInit() {
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
