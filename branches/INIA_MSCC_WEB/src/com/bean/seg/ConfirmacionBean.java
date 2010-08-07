package com.bean.seg;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioSEG;

public class ConfirmacionBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String loginName;

	private String contrasenia;
	private String confirmacion;
	private String frase;

	private String codigoActivacion;
	private boolean activado;

	/**
	 * @return
	 */
	public String Confirmar() {
		String retorno = "confirmar-error";
		try {
			if (this.getUsuario() != null) {
				if (!contrasenia.equalsIgnoreCase(this.getUsuario()
						.get_login())) {
					if (contrasenia.equalsIgnoreCase(confirmacion)) {
						if (!frase.isEmpty()
								&& !frase.equals("")
								&& !frase.equalsIgnoreCase(this.getUsuario()
										.get_login())
								&& !frase.equalsIgnoreCase(contrasenia)) {
							this.getUsuario().set_password(contrasenia);
							this.getUsuario().set_ultimoAcceso(new Date());
							this.getUsuario().set_frase(frase);
							this.getUsuario().set_activado(true);
							this.getUsuario().set_estadoUsuario(
									EstadoUsuario.Activo);
							this.getSegFachada(ServicioSEG.Usuario)
									.CambiarPassword(this.getUsuario());
							retorno = "confirmar-ok";
						} else {
							this
									.setError("Ingrese la Clave Secreta y recuerde que no sea igual a su contrase�a o nombre de usuario.");
							retorno = "confirmar-error";
						}
					} else {
						this
								.setError("La contrase�a ingresada no es igual a la confirmaci�n.");
						retorno = "confirmar-error";
					}
				} else {
					this
							.setError("La contrase�a ingresada no puede ser igual al Nombre de usuario.");
					retorno = "confirmar-error";
				}

			} else {
				this.setError("El usuario no esta registrado en el sistema.");
				retorno = "confirmar-error";
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	/*
	 * Region de Metodos
	 */
	public boolean isInit() {
		try {
			setActivado(false);
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
				this.setUsuario(this.getSegFachada(ServicioSEG.Usuario)
						.ComprobarClaveReigstro(codigoActivacion.toString()));
				if (this.getUsuario() != null) {
					nombre = this.getUsuario().get_datos().get_nombre() + " "
							+ this.getUsuario().get_datos().get_apellido();
					loginName = this.getUsuario().get_login();
					if (this.getUsuario().is_activado()) {
						this.setError("Estiamdo usuario " + nombre
								+ " su cuenta est� confirmada.");
						setActivado(true);
					} else if (this.getUsuario().get_estadoUsuario().equals(
							Enumerados.EstadoUsuario.Activo)) {
						this
								.setError("Estiamdo usuario "
										+ nombre
										+ " su cuenta est� activada, no es necesario este paso.");
						setActivado(true);
					} else if (this.getUsuario().get_estadoUsuario().equals(
							Enumerados.EstadoUsuario.Bloqueado)) {
						this
								.setError("Estiamdo usuario "
										+ nombre
										+ " su cuenta est� BLOQUEADA, ingrese a Recuperar Contrase�a para activarla.");
						setActivado(true);
					} else if (this.getUsuario().get_estadoUsuario().equals(
							Enumerados.EstadoUsuario.Inactivo)) {
						this
								.setError("Estiamdo usuario "
										+ nombre
										+ " su cuenta est� momentaneamente INACTIVA, consulte al administrador del sistema.");
						setActivado(true);
					} else {
						this.setError("");
						setActivado(false);
					}
				} else {
					this
							.setError("Estiamdo usuario, debe Registrarse previamente.");
					setActivado(true);
				}
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return false;
	}

	/**
	 * @return
	 */
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

	public String getCodigoActivacion() {
		return codigoActivacion;
	}

	public void setCodigoActivacion(String codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

}