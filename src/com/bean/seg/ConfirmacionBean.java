package com.bean.seg;

import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.seg.SEGFachada;

public class ConfirmacionBean extends MaestroBean {

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
	private String codigoActivacion;
	private Boolean activado;

	/**
	 * @return
	 */
	public String Confirmar() {
		String retorno;
		// retorno = "confirmar-ok";
		// error = "confirmar-ok";
		if (super.getUsuario() != null) {
			if (contrasenia.equals(confirmacion)) {
				if (!frase.isEmpty() || !frase.equals("")
						|| !frase.equals(super.getUsuario().get_login())
						|| !frase.equals(contrasenia)) {
					super.getUsuario().set_codigoActivacion(null);
					super.getUsuario().set_password(contrasenia);
					super.getUsuario().set_frase(frase);
					super.getUsuario().set_activado(true);
					super.getUsuario().set_estadoUsuario(EstadoUsuario.Activo);
					segFachada.CambiarPassword(super.getUsuario());
					retorno = "confirmar-ok";
				} else {
					error = "Ingrese la clave secreta y recuerde que no sea igual a su contraseña o nombre de usuario.";
					retorno = "confirmar-error";
				}
			} else {
				error = "La contraseña ingresada no es igual a su confirmación.";
				retorno = "confirmar-error";
			}
			// TODO Poner el metod de la fachada que confira el usuario.
		} else {
			error = "El usuario no esta registrado en el sistema.";
			retorno = "confirmar-error";
		}
		return retorno;
	}

	/*
	 * Region de Metodos
	 */
	public boolean isInit() {
		activado = false;
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		if (params.containsKey("codigoActivacion")) {
			if (!params.get("codigoActivacion").isEmpty()) {
				this.setCodigoActivacion(params.get("codigoActivacion"));
			} else {
				codigoActivacion = null;
			}
		} else {
			codigoActivacion = null;
		}
		if (codigoActivacion != null) {
			super.setUsuario(segFachada.ComprobarClaveReigstro(codigoActivacion
					.toString()));
			if (super.getUsuario() != null) {
				nombre = super.getUsuario().get_datos().get_nombre() + " "
						+ super.getUsuario().get_datos().get_apellido();
				if (super.getUsuario().is_activado()) {
					error = "Estiamdo usuario " + nombre
							+ " su cuenta está confirmada.";
					activado = true;
				} else if (super.getUsuario().get_estadoUsuario().equals(
						Enumerados.EstadoUsuario.Activo)) {
					error = "Estiamdo usuario "
							+ nombre
							+ " su cuenta está activada, no es necesario este paso.";
					activado = true;
				} else if (super.getUsuario().get_estadoUsuario().equals(
						Enumerados.EstadoUsuario.Bloqueado)) {
					error = "Estiamdo usuario "
							+ nombre
							+ " su cuenta está bloqueada, ingrese a recuperar su contraseña.";
					activado = true;
				} else if (super.getUsuario().get_estadoUsuario().equals(
						Enumerados.EstadoUsuario.Inactivo)) {
					error = "Estiamdo usuario "
							+ nombre
							+ " su cuenta está momentaneamente inactivada, consulte a su administrador.";
					activado = true;
				} else {
					error = "";
				}
			} else {
				error = "Estiamdo usuario, realize previamente el paso Registro de usuario.";
				activado = true;
			}
		} else {
			error = "Esta pagina es solo util para usuarios registrados.";
			activado = true;
		}
		return activado;
	}

	/**
	 * @return
	 */
	public boolean isActivado() {
		return activado;
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

	public Boolean getActivado() {
		return activado;
	}

	public void setActivado(Boolean activado) {
		this.activado = activado;
	}

	public String getCodigoActivacion() {
		return codigoActivacion;
	}

	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}

}