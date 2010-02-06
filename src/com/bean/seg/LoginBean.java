package com.bean.seg;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.mail.MessagingException;
import javax.naming.NamingException;

import com.bean.comun.MaestroBean;
import com.bean.gem.ArchivosTexto;
import com.bean.gem.wgen;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class LoginBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loginName;
	private String password;
	private String error;
	private static long intentos = 0;

	public boolean isInit() {
		return false;
	}

	public String olvidoContrasenia() {
		super.setLogged(true);
		super.setUsuario(null);
		super.limpiarSesion();
		error = "";
		return "login-olvido";
	}

	public String login() {
		String mensaje = "login-error";
		try {
			wgen w = new wgen();
			

			// File directorio = new File("c:\\temp\\ArchivoClimaGenerado");
			// if (!directorio.isDirectory()) {
			// System.out.println(" NO es un directorio");
			// directorio.mkdir();
			// }

			File f = new File("/Wather_sim_pickle.txt");
			if (f.exists()) {
				f.deleteOnExit();
			}

			File fxml = new File(
					"C:/Biblioteca/Cajón/Proyecto/INIA/Archivos Recibidos/climate_parameters_for_site_LE.xml");
			
			// TODO hay que pasar el archivo, ahora esta siendo harcodeado
			// dnetro del metodo cargraArchivoParametros
			w.cargarArchivoParametros(fxml);
			int[] yearbounds = { 2007, 2009 };
			double meanppt = 6.217768d;
			double intensity = 1.0d;
			int[] adj = new int[] { 0, 0, 0, 365 };
			ArchivosTexto.saveString(f, w.Sim_wea(yearbounds, meanppt,
					intensity, adj));

			Usuario u = super.getSegFachada(Servicio.Usuario).Login(loginName,
					password);
			if (u != null) {
				if (u.is_activado()) {
					if (u.get_estadoUsuario().equals(EstadoUsuario.Activo)) {
						super.setLogged(true);
						super.setUsuario(u);
						Date fecha = u.get_ultimoAcceso();
						u.set_ultimoAcceso(new Date());
						super.getSegFachada(Servicio.Usuario)
								.ActualizarUltimoAcceso(u);
						u.set_ultimoAcceso(fecha);
						super.setSesion(Usuario.class.toString(), u);
						error = "";
						mensaje = "login-ok";
					} else {
						error = u.get_datos().get_nombre()
								+ " su cuenta esta "
								+ u.get_estadoUsuario().toString()
										.toLowerCase()
								+ " aún, recuerde chequear su correo, se le a enviado un e-mail para concluir con el registro.";
						mensaje = "login-error";
					}
				} else {
					error = u.get_datos().get_nombre()
							+ " su cuenta no esta activa aún, recuerde chequear su correo, se le a enviado un e-mail para concluir con el registro.";
					mensaje = "login-error";
				}
			} else {
				error = "El nombre de usuario o password no conciden";
				intentos++;
				if (intentos == 5) {
					error = "Este es el quinto intento en logearse, por favor verifique sus datos e intente nuevamente.";
					intentos = 0;
					// TODO Recordar si vamos a incluir el bloqueo de usuario
					// por
					// intentos.
					// u.set_estadoUsuario(EstadoUsuario.Bloqueado);
					// super.getSegFachada(Servicio.Usuario).CambiarPassword(u);
				}
				mensaje = "login-error";
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return mensaje;
	}

	public String logout() {
		try {
			super.setLogged(false);
			super.setUsuario(null);
			super.setOpcion("/Servicios/SEG/SEG001.jsp");
			super.removerSesion(Usuario.class.toString());
			error = "";
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		} finally {
			super.limpiarSesion();
		}
		return "logout";
	}

	public String olvidoPassword() {
		return "olvidoPassword";
	}

	public String registrarse() {
		return "registrarse";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

}
