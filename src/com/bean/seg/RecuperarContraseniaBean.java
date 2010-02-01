package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.entidades.Usuario;

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
	private String actual;

	public boolean isInit(){
		return false;
	}
	
	public void validarContrasenia(){
		try {
			if (super.getUsuario().get_password().equals(getActual())) {
				super
						.setError("La contraseña actual que ingreso no es correcta.");
			} else {
				super.setError("");
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}
	
	
	public void validarEmail() {
		try {
			if (super.getSegFachada(Servicio.Usuario).ComprobarEmail(email)) {
				super
						.setError("El e-mail ingresado no esta registrado en el sistema.");
				super.setUsuario(null);
			} else {
				super.setError("");
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	public void validarFrase() {
		try {
			super.setUsuario(super.getSegFachada(Servicio.Usuario)
					.ObtenerUsuarioXDatos(null, null, null, email, frase));
			if (super.getUsuario() != null) {
				super.setError("");
				super.setExito("Bienvenido "
						+ super.getUsuario().get_datos().get_nombre()
						+ " su login es " + super.getUsuario().get_login());
			} else {
				super
						.setError("Lamentablemente no hay un usuario registrado con el e-mail y frase secretaingresada.");
				super.setExito("");
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	public String EnviarPassword() {
		String retorno = "";
		try {
			if (super.getUsuario() != null) {
				if (!this.salvarNombre(super.getUsuario())) {
					// if (!this.enviarMailConfirmacion(pUsuario)) {
					super
							.setError("No ha sido posible enviar el e-mail, el e-mail proporcionado no esta disponible.");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/SEG/SEG002.jsp");
					retorno = "registro-error";
				}
			}
			super.setError("");
			super
					.setExito("Se a enviado un e-mail a su casilla de correo, lea el correo ver su clave.");
			retorno = "EnvioPassword";
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
		return retorno;
	}

	public String Confirmar() {
		String retorno = "";
		return retorno;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 * @throws MessagingException
	 */
	public Boolean salvarNombre(Usuario pUsuario) throws IOException,
			NamingException, MessagingException {
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			//props.setProperty("mail.smtp.host", "smtp.live.com");// hotmail
			props.setProperty("mail.smtp.host", "smtp.gmail.com");// gmail

			// TLS si está disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port", "587");

			// Nombre del usuario
			props.setProperty("mail.smtp.user", "INIA - MSCC Administración");

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);

			MimeMessage message = new MimeMessage(session);
			// Quien envia el correo
			message.setFrom(new InternetAddress("juan400SVN@gmail.com"));

			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					pUsuario.get_datos().get_mail()));

			message
					.setSubject("Clave de usuario en el sistema INIA - MSCC");

			message
					.setText(
							"<br></br><br></br><br><center><i><b>"
									+ pUsuario.get_datos().get_nombre()
									+ " "
									+ pUsuario.get_datos().get_apellido()
									+ " su usuario es "
									+ pUsuario.get_login()
									+ " en INIA - MSCC,</br>"
									+ "<br>para acceder al sistema su contraseña es: </br><br>"
									+ pUsuario.get_password()
									+ "</br><br>Recuerde memorizar su contraseña y eliminar este correo.</b></i></center><br></br><br></br><br></br>"
									+ "<br>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>",
							"ISO-8859-1", "html");

			Transport t = session.getTransport("smtp");
			t.connect("juan400SVN@gmail.com", "andres4003341");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
		return true;
	}

	// TODO Utilizar este envio de corro si encontramos como no de problemas con
	// los puetos 25 y 587
	public boolean enviarMailConfirmacion(Usuario pUsuario) {
		try {
			String body = "<br></br><br></br><br><center><i><b>"
					+ pUsuario.get_datos().get_nombre()
					+ " "
					+ pUsuario.get_datos().get_apellido()
					+ " su usuario es "
					+ pUsuario.get_login()
					+ " en INIA - MSCC,</br>"
					+ "<br>para acceder al sistema su contraseña es "
					+ pUsuario.get_password()
					+ "</b></i></br><br>"
					+ "<br><i><b>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>";

			super.getComunFachada(Servicio.MailSender).enviarMailTextoPlano(
					pUsuario.get_datos().get_mail(), "INIA - MSCC Administración", body);
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
		return true;
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

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}
}
