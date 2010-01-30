package com.bean.seg;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;

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
			if (super.getUsuario() != null) {
				if (!contrasenia.equalsIgnoreCase(super.getUsuario()
						.get_login())) {
					if (contrasenia.equalsIgnoreCase(confirmacion)) {
						if (!frase.isEmpty()
								&& !frase.equals("")
								&& !frase.equalsIgnoreCase(super.getUsuario()
										.get_login())
								&& !frase.equalsIgnoreCase(contrasenia)) {
							// super.getUsuario().set_codigoActivacion(null);
							super.getUsuario().set_password(contrasenia);
							super.getUsuario().set_ultimoAcceso(new Date());
							super.getUsuario().set_frase(frase);
							super.getUsuario().set_activado(true);
							super.getUsuario().set_estadoUsuario(
									EstadoUsuario.Activo);
							super.getSegFachada(Servicio.Usuario)
									.CambiarPassword(super.getUsuario());
							retorno = "confirmar-ok";
						} else {
							super
									.setError("Ingrese la clave secreta y recuerde que no sea igual a su contraseña o nombre de usuario.");
							retorno = "confirmar-error";
						}
					} else {
						super
								.setError("La contraseña ingresada no es igual a su confirmación.");
						retorno = "confirmar-error";
					}
				} else {
					super
							.setError("La contraseña ingresada no puede ser igual al usuario.");
					retorno = "confirmar-error";
				}
				// TODO Poner el metod de la fachada que confira el usuario.
			} else {
				super.setError("El usuario no esta registrado en el sistema.");
				retorno = "confirmar-error";
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
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
				super.setUsuario(super.getSegFachada(Servicio.Usuario)
						.ComprobarClaveReigstro(codigoActivacion.toString()));
				if (super.getUsuario() != null) {
					nombre = super.getUsuario().get_datos().get_nombre() + " "
							+ super.getUsuario().get_datos().get_apellido();
					loginName = super.getUsuario().get_login();
					if (super.getUsuario().is_activado()) {
						super.setError("Estiamdo usuario " + nombre
								+ " su cuenta está confirmada.");
						setActivado(true);
					} else if (super.getUsuario().get_estadoUsuario().equals(
							Enumerados.EstadoUsuario.Activo)) {
						super
								.setError("Estiamdo usuario "
										+ nombre
										+ " su cuenta está activada, no es necesario este paso.");
						setActivado(true);
					} else if (super.getUsuario().get_estadoUsuario().equals(
							Enumerados.EstadoUsuario.Bloqueado)) {
						super
								.setError("Estiamdo usuario "
										+ nombre
										+ " su cuenta está bloqueada, ingrese a recuperar su contraseña.");
						setActivado(true);
					} else if (super.getUsuario().get_estadoUsuario().equals(
							Enumerados.EstadoUsuario.Inactivo)) {
						super
								.setError("Estiamdo usuario "
										+ nombre
										+ " su cuenta está momentaneamente inactivada, consulte a su administrador.");
						setActivado(true);
					} else {
						super.setError("");
						setActivado(false);
					}
				} else {
					super
							.setError("Estiamdo usuario, realize previamente el paso Registro de usuario.");
					setActivado(true);
				}
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
		// else {
		// super.setError("Esta pagina es solo util para usuarios registrados.");
		// // setActivado(true);
		// }
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