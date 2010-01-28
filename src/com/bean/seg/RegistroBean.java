package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class RegistroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada(Enumerados.Servicio.Usuario);

	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String formatoTelefono;
	private String celular;
	private String formatoCelular;
	private String direccion;
	private String ciudad;
	private String departamento;
	private String pais;
	private String codigoPostal;

	private String error;

	/*
	 * Region de Metodos
	 */
	public boolean isInit() {
		boolean retrono = false;

		return retrono;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public String registrar() throws Exception {
		// MaestroBean.getInstance().getTextBundle();
		String retorno = "";
		try {
			DatoUsuario datos = new DatoUsuario();
			datos.set_nombre(nombre);
			datos.set_apellido(apellido);
			datos.set_mail(email);
			datos.set_pais(null);
			datos.set_departamento(null);
			datos.set_ciudad(null);
			datos.set_direccion(direccion);
			datos.set_cel(celular);
			datos.set_tele(telefono);
			datos.set_fechaRegistro(new Date());
			datos.set_timeStamp(new Date());
			Usuario pUsuario = new Usuario();
			pUsuario.set_datos(datos);
			pUsuario.set_login(datos.get_mail().substring(0,
					datos.get_mail().indexOf("@")));
			StringBuffer p = new StringBuffer();
			for (int i = 0; i < 8; i++) {
				String c = "" + (int) (Math.random() * 10);
				p.append(c);
			}
			pUsuario.set_codigoActivacion(p.toString());
			pUsuario.set_password(p.toString());
			pUsuario.set_estadoUsuario(EstadoUsuario.Registrado);
			pUsuario.set_ultimoAcceso(new Date());
			pUsuario.set_frase("Ingrese su frase secreta");
			Usuario u = segFachada.RegistrarUsuario(pUsuario);
			if (u != null) {
				if (!this.salvarNombre(pUsuario)) {
					error = "No ha sido posible registrar el usuario, el e-mail proporcionado no esta disponible.";
					MaestroBean.getInstance().setOpcion(
							"/Servicios/SEG/SEG002.jsp");
					retorno = "registro-error";
				}
				error = "";
				MaestroBean.getInstance()
						.setOpcion("/Servicios/SEG/SEG001.jsp");
				retorno = "registro-ok";
			} else {
				error = "No ha sido posible registrar el usuario, revise los datos ingresados y intentelo nuevamente.";
				MaestroBean.getInstance()
						.setOpcion("/Servicios/SEG/SEG002.jsp");
				retorno = "registro-error";
			}
		} catch (Exception ex) {
			error = ex.getMessage();
		}
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

		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.live.com");// hotmail
		// smtp.live.com

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port", "587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "INIA - MSCC Registro");

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

		message.setSubject("Activacion de usuario en el sistema INIA - MSCC");

		message
				.setText(
						"<i><b>Usted se a registrado stisfactoriamente en INIA - MSCC<br>"
								+ "para concluir con el registro aceda al siguiente link </b></i>.<br>"
								+
								// TODO cambiar el path del servidor
								"<a href='http://localhost:8081/INIA_MSCC/Servicios/SEG/SEG003.jsf?codigoActivacion="
								+ pUsuario.get_password()
								+ "'>"
								+ "Concluir el registro de usuario</a><br><br>"
								+ "<i><b>Muchas gracias por registrarse!</b></i>",
						"ISO-8859-1", "html");

		Transport t = session.getTransport("smtp");
		t.connect("juan400_4@hotmail.com", "andres4003341");
		t.sendMessage(message, message.getAllRecipients());
		t.close();

		// MailService mail = new MailService();
		// mail.setJNDIName("java:/MailSenderService");
		// mail.setUser("juan400@gmail.com");
		// mail.setPassword("andres4003341");
		// mail.setConfiguration(.ull)
		// Element ele = new Element();
		// MailSenderServices mail = new MailSenderProvider();
		// mail.enviarMailTextoPlano(pDatos.get_mail(), "Enviado desde MSCC",
		// "Esto es uun mail de prueba.");
		return true;
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

	/*
	 * Region de Getters y Setters
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFormatoTelefono() {
		return formatoTelefono;
	}

	public void setFormatoTelefono(String formatoTelefono) {
		this.formatoTelefono = formatoTelefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFormatoCelular() {
		return formatoCelular;
	}

	public void setFormatoCelular(String formatoCelular) {
		this.formatoCelular = formatoCelular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
