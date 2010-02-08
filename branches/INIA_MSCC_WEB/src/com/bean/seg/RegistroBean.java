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
	private List<Pais> listPaises;
	private List<Departamento> listDepartamentos;
	private List<Ciudad> listCiudades;
	private Pais pais;
	private Departamento depto;
	private Ciudad ciudad;
	private Date fecha = new Date();

	public void takeSelectionEmail() {
		try {
			if (this.getUsuario() == null) {
				if (!this.getSegFachada(Servicio.Usuario)
						.ComprobarEmail(email)) {
					this
							.setError("El e-mail ingresado ya esta registrado en el sistema.");
					this.setEmail("");
				} else {
					this.setError("");
				}
			} else if (this.getUsuario().get_datos().get_mail().equals(email)) {
				if (!this.getSegFachada(Servicio.Usuario)
						.ComprobarEmail(email)) {
					this
							.setError("El e-mail ingresado ya esta registrado en el sistema.");
					this.setEmail("");
				} else {
					this.setError("");
				}
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	/**
	 * @param pUsuario
	 * @return
	 */
	public boolean enviarMailConfirmacion(Usuario pUsuario) {
		try {
			HttpServletRequest request = (HttpServletRequest) this
					.getFacesContext().getExternalContext().getRequest();
			StringBuffer path = request.getRequestURL();// http://localhost:8081/INIA_MSCC/Servicios/SEG/SEG002.jsf
			String server = path.toString().replaceFirst("SEG002", "SEG003")
					.toString();
			String body = "<br></br><br></br><br><center><i><b>Usted se a registrado stisfactoriamente en INIA - MSCC,</br>"
					+ "<br>para concluir con el registro aceda al siguiente link </b></i>.</br>"
					+ "<br><a href='"
					+ server
					+ "?codigoActivacion="
					+ pUsuario.get_codigoActivacion()
					+ "'>"
					+ "Concluir el registro de usuario</a></br><br></br>"
					+ "<br><i><b>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>";

			this.getComunFachada(Servicio.MailSender).enviarMailTextoPlano(
					pUsuario.get_datos().get_mail(), "INIA - MSCC Registro",
					body);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return true;
	}

	public void takeSelectionCiudad() {
		try {
			ciudad = new Ciudad();
			ciudad.set_nombre(getCiudadElegido());
			ciudad = this.getAdmFachada(Servicio.RelacionPCD).ObtenerCiudad(
					ciudad);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionDepartamento() {
		try {
			depto = new Departamento();
			depto.set_nombre(getDepartamentoElegido());
			depto = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamento(depto);
			listCiudades = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerCiudadesXDeptos(depto);
			ciudades = new SelectItem[listCiudades.size() + 1];
			ciudades[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (Ciudad c : listCiudades) {
				SelectItem si = new SelectItem(c.get_nombre());
				ciudades[l] = si;
				l++;
			}
			ciudadElegido = ciudades[0].getValue().toString();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionPais() {
		try {
			pais = new Pais();
			pais.set_nombre(getPaisElegido());
			pais = this.getAdmFachada(Servicio.RelacionPCD).ObtenerPais(pais);
			listDepartamentos = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamentosXPais(pais);
			departamentos = new SelectItem[listDepartamentos.size() + 1];
			departamentos[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int j = 1;
			for (Departamento d : listDepartamentos) {
				SelectItem si = new SelectItem(d.get_nombre());
				departamentos[j] = si;
				j++;
			}
			departamentoElegido = departamentos[0].getValue().toString();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public RegistroBean() throws Exception {
		try {
			listPaises = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerPaises();
			paises = new SelectItem[listPaises.size() + 1];
			paises[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Pais p : listPaises) {
				SelectItem si = new SelectItem(p.get_nombre());
				paises[i] = si;
				i++;
			}
			listDepartamentos = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamentos();
			departamentos = new SelectItem[listDepartamentos.size() + 1];
			departamentos[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int j = 1;
			for (Departamento d : listDepartamentos) {
				SelectItem si = new SelectItem(d.get_nombre());
				departamentos[j] = si;
				j++;
			}
			listCiudades = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerCiudades();
			ciudades = new SelectItem[listCiudades.size() + 1];
			ciudades[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (Ciudad c : listCiudades) {
				SelectItem si = new SelectItem(c.get_nombre());
				ciudades[l] = si;
				l++;
			}
			paisElegido = paises[0].getValue().toString();
			departamentoElegido = departamentos[0].getValue().toString();
			ciudadElegido = ciudades[0].getValue().toString();
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public boolean isInit() {
		boolean retrono = false;

		return retrono;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public String registrar() {
		String retorno = "";
		try {
			if (this.getSegFachada(Servicio.Usuario).ComprobarEmail(email)) {
				DatoUsuario datos = new DatoUsuario();
				datos.set_nombre(nombre);
				datos.set_apellido(apellido);
				datos.set_mail(email);
				datos.set_pais(pais);
				datos.set_departamento(depto);
				datos.set_ciudad(ciudad);
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
				Usuario u = this.getSegFachada(Servicio.Usuario)
						.RegistrarUsuario(pUsuario);
				if (u != null) {
					// if (!this.salvarNombre(pUsuario)) {
					if (!this.enviarMailConfirmacion(pUsuario)) {
						this
								.setError("No ha sido posible registrar el usuario, el e-mail proporcionado no esta disponible.");
					}
					this.setError("");
					this
							.setExito("Se a enviado un e-mail a su casilla de correo, lea el correo para confirmar el registro.");
				} else {
					this
							.setError("No ha sido posible registrar el usuario, revise los datos ingresados y intentelo nuevamente.");
				}
			} else {
				this.setError("El e-mail esta registrado para otro usuario.");
				MaestroBean.getInstance()
						.setOpcion("/Servicios/SEG/SEG002.jsp");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
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
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			// props.setProperty("mail.smtp.host", "smtp.live.com");// hotmail
			props.setProperty("mail.smtp.host", "smtp.gmail.com");// gmail
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

			HttpServletRequest request = (HttpServletRequest) this
					.getFacesContext().getExternalContext().getRequest();
			StringBuffer path = request.getRequestURL();// http://localhost:8081/INIA_MSCC/Servicios/SEG/SEG002.jsf
			String server = path.toString().replaceFirst("SEG002", "SEG003")
					.toString();

			message
					.setSubject("Activacion de usuario en el sistema INIA - MSCC");

			message
					.setText(
							"<br></br><br></br><br><center><i><b>Usted se a registrado stisfactoriamente en INIA - MSCC,</br>"
									+ "<br>para concluir con el registro aceda al siguiente link </b></i>.</br>"
									+ "<br><a href='"
									+ server
									+ "?codigoActivacion="
									+ pUsuario.get_codigoActivacion()
									+ "'>"
									+ "Concluir el registro de usuario</a></br><br></br>"
									+ "<br><i><b>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>",
							"ISO-8859-1", "html");

			Transport t = session.getTransport("smtp");
			t.connect("juan400SVN@gmail.com", "andres4003341");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return true;
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

	public List<Pais> getListPaises() {
		return listPaises;
	}

	public void setListPaises(List<Pais> listPaises) {
		this.listPaises = listPaises;
	}

	public List<Departamento> getListDepartamentos() {
		return listDepartamentos;
	}

	public void setListDepartamentos(List<Departamento> listDepartamentos) {
		this.listDepartamentos = listDepartamentos;
	}

	public List<Ciudad> getListCiudades() {
		return listCiudades;
	}

	public void setListCiudades(List<Ciudad> listCiudades) {
		this.listCiudades = listCiudades;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

}