package com.bean.seg;

import java.io.Serializable;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;

public class RegistroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SEGFachada segFachada = new SEGFachada();
	/*
	 * Region de atributos y variables
	 */
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

	private String contrasenia;
	private String confirmacion;
	private String frase;

	private String error;
	private Long codigoActivacion;

	/*
	 * Region de Metodos
	 */
	public boolean isInit() {
		return false;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public String registrar() throws Exception{
		// MaestroBean.getInstance().getTextBundle();
		String retorno="";
		try {
			DatoUsuario pUsuario = new DatoUsuario();
			pUsuario.set_nombre(this.getNombre());
			DatoUsuario u = segFachada.RegistrarUsuario(pUsuario);
//			 Usuario u = segFachada.Login(this.getNombre(),this.getApellido());
			if (u != null) {
				StringBuffer p = new StringBuffer();
				for (int i = 0; i < 8; i++) {
					String c = "" + (int) (Math.random() * 10);
					p.append(c);
				}
				error = "";
				MaestroBean.getInstance().setOpcion(
						"/Servicios/SEG/menuRich.jsp");
				retorno = "registro-ok";
			} else {
				error = "El nombre de usuario y password no concuerdan";
				retorno = "registro-error";
			}
		} catch (Exception ex) {
			error  = ex.getMessage();
		}
		return retorno;
	}

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

	public void setCodigoActivacion(Long codigoActivacion) {
		this.codigoActivacion = codigoActivacion;
	}

	public Long getCodigoActivacion() {
		return codigoActivacion;
	}

}
