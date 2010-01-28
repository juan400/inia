package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class RegistroBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String formatoTelefono;
	private String celular;
	private String formatoCelular;
	private String direccion;
	private String paisElegido;
	private String departamentoElegido;
	private String ciudadElegido;
	private String codigoPostal;
	private SelectItem[] paises;
	private SelectItem[] departamentos;
	private SelectItem[] ciudades;
	private String error;

	public void takeSelectionDepartamento() {
		Departamento unDepto = new Departamento();
		unDepto.set_nombre(getDepartamentoElegido());
		unDepto = super.getAdmFachada(Servicio.RelacionPCD).ObtenerDepartamento(unDepto);
		List<Ciudad> cs = super.getAdmFachada(Servicio.RelacionPCD)
				.ObtenerCiudadesXDeptos(unDepto);
		ciudades = new SelectItem[cs.size() + 1];
		ciudades[0] = new SelectItem(super.getTextBundleKey("combo_seleccione"));
		int l = 1;
		for (Ciudad c : cs) {
			SelectItem si = new SelectItem(c.get_nombre());
			ciudades[l] = si;
			l++;
		}
		ciudadElegido = ciudades[0].getValue().toString();
	}

	public void takeSelectionPais() {
		Pais unPais = new Pais();
		unPais.set_nombre(getPaisElegido());
		unPais = super.getAdmFachada(Servicio.RelacionPCD).ObtenerPais(unPais);
		List<Departamento> depto = super.getAdmFachada(Servicio.RelacionPCD)
				.ObtenerDepartamentosXPais(unPais);
		departamentos = new SelectItem[depto.size() + 1];
		departamentos[0] = new SelectItem(super
				.getTextBundleKey("combo_seleccione"));
		int j = 1;
		for (Departamento d : depto) {
			SelectItem si = new SelectItem(d.get_nombre());
			departamentos[j] = si;
			j++;
		}
		departamentoElegido = departamentos[0].getValue().toString();
	}

	public RegistroBean() throws Exception {
		try {
			List<Pais> ps = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerPaises();
			paises = new SelectItem[ps.size() + 1];
			paises[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Pais p : ps) {
				SelectItem si = new SelectItem(p.get_nombre());
				paises[i] = si;
				i++;
			}
			List<Departamento> ds = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamentos();
			departamentos = new SelectItem[ds.size() + 1];
			departamentos[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int j = 1;
			for (Departamento d : ds) {
				SelectItem si = new SelectItem(d.get_nombre());
				departamentos[j] = si;
				j++;
			}
			List<Ciudad> cs = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerCiudades();
			ciudades = new SelectItem[cs.size() + 1];
			ciudades[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (Ciudad c : cs) {
				SelectItem si = new SelectItem(c.get_nombre());
				ciudades[l] = si;
				l++;
			}
			paisElegido = paises[0].getValue().toString();
			departamentoElegido = departamentos[0].getValue().toString();
			ciudadElegido = ciudades[0].getValue().toString();
		} catch (Exception e) {
			throw e;
		}
	}

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
			Usuario u = super.getSegFachada(Servicio.Usuario).RegistrarUsuario(
					pUsuario);
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

		HttpServletRequest request = (HttpServletRequest) super
				.getFacesContext().getExternalContext().getRequest();
		StringBuffer path = request.getRequestURL();// http://localhost:8081/INIA_MSCC/Servicios/SEG/SEG002.jsf
		String server = path.toString().replaceFirst("SEG002", "SEG003")
				.toString();

		message.setSubject("Activacion de usuario en el sistema INIA - MSCC");

		message
				.setText(
						"<br></br><br></br><br><center><i><b>Usted se a registrado stisfactoriamente en INIA - MSCC,</br>"
								+ "<br>para concluir con el registro aceda al siguiente link </b></i>.</br>"
								+ "<br><a href='"
								+ server
								+ "?codigoActivacion="
								+ pUsuario.get_password()
								+ "'>"
								+ "Concluir el registro de usuario</a></br><br></br>"
								+ "<br><i><b>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>",
						"ISO-8859-1", "html");

		Transport t = session.getTransport("smtp");
		t.connect("juan400_4@hotmail.com", "andres4003341");
		t.sendMessage(message, message.getAllRecipients());
		t.close();
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

	public String getPaisElegido() {
		return paisElegido;
	}

	public void setPaisElegido(String paisElegido) {
		this.paisElegido = paisElegido;
	}

	public String getDepartamentoElegido() {
		return departamentoElegido;
	}

	public void setDepartamentoElegido(String departamentoElegido) {
		this.departamentoElegido = departamentoElegido;
	}

	public String getCiudadElegido() {
		return ciudadElegido;
	}

	public void setCiudadElegido(String ciudadElegido) {
		this.ciudadElegido = ciudadElegido;
	}

	public SelectItem[] getPaises() {
		return paises;
	}

	public void setPaises(SelectItem[] paises) {
		this.paises = paises;
	}

	public SelectItem[] getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(SelectItem[] departamentos) {
		this.departamentos = departamentos;
	}

	public SelectItem[] getCiudades() {
		return ciudades;
	}

	public void setCiudades(SelectItem[] ciudades) {
		this.ciudades = ciudades;
	}

}