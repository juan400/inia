package com.bean.seg;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class DatosUsuarioBean extends MaestroBean implements Serializable {

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
	
	public DatosUsuarioBean() throws Exception {
		try {
			listPaises = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerPaises();
			paises = new SelectItem[listPaises.size() + 1];
			paises[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int i = 1;
			for (Pais p : listPaises) {
				SelectItem si = new SelectItem(p.get_nombre());
				paises[i] = si;
				i++;
			}
			listDepartamentos = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamentos();
			departamentos = new SelectItem[listDepartamentos.size() + 1];
			departamentos[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int j = 1;
			for (Departamento d : listDepartamentos) {
				SelectItem si = new SelectItem(d.get_nombre());
				departamentos[j] = si;
				j++;
			}
			listCiudades = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerCiudades();
			ciudades = new SelectItem[listCiudades.size() + 1];
			ciudades[0] = new SelectItem(super
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
			super.setError(ex.getMessage());
		}
	}
	
	public void takeSelectionEmail() {
		try {
			if (super.getUsuario() == null) {
				if (!super.getSegFachada(Servicio.Usuario)
						.ComprobarEmail(email)) {
					super
							.setError("El e-mail ingresado ya esta registrado en el sistema.");
				} else {
					super.setError("");
				}
			} else if (super.getUsuario().get_datos().get_mail().equals(email)) {
				if (!super.getSegFachada(Servicio.Usuario)
						.ComprobarEmail(email)) {
					super
							.setError("El e-mail ingresado ya esta registrado en el sistema.");
				} else {
					super.setError("");
				}
			}
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	public boolean enviarMailConfirmacion(Usuario pUsuario) {
		try {
			HttpServletRequest request = (HttpServletRequest) super
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

			super.getComunFachada(Servicio.MailSender).enviarMailTextoPlano(
					"juan400SVN@gmail.com", "INIA - MSCC Registro", body);
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
		return true;
	}

	public void takeSelectionCiudad() {
		try {
			ciudad = new Ciudad();
			ciudad.set_nombre(getDepartamentoElegido());
			ciudad = super.getAdmFachada(Servicio.RelacionPCD).ObtenerCiudad(
					ciudad);
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	public void takeSelectionDepartamento() {
		try {
			depto = new Departamento();
			depto.set_nombre(getDepartamentoElegido());
			depto = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamento(depto);
			listCiudades = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerCiudadesXDeptos(depto);
			ciudades = new SelectItem[listCiudades.size() + 1];
			ciudades[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (Ciudad c : listCiudades) {
				SelectItem si = new SelectItem(c.get_nombre());
				ciudades[l] = si;
				l++;
			}
			ciudadElegido = ciudades[0].getValue().toString();

		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	public void takeSelectionPais() {
		try {
			pais = new Pais();
			pais.set_nombre(getPaisElegido());
			pais = super.getAdmFachada(Servicio.RelacionPCD).ObtenerPais(pais);
			listDepartamentos = super.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamentosXPais(pais);
			departamentos = new SelectItem[listDepartamentos.size() + 1];
			departamentos[0] = new SelectItem(super
					.getTextBundleKey("combo_seleccione"));
			int j = 1;
			for (Departamento d : listDepartamentos) {
				SelectItem si = new SelectItem(d.get_nombre());
				departamentos[j] = si;
				j++;
			}
			departamentoElegido = departamentos[0].getValue().toString();

		} catch (Exception ex) {
			super.setError(ex.getMessage());
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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Departamento getDepto() {
		return depto;
	}

	public void setDepto(Departamento depto) {
		this.depto = depto;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


}
