package com.bean.comun;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.inia_mscc.modulos.adm.ADMFachada;
import com.inia_mscc.modulos.comun.ComunFachada;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = null;
	private boolean logged = false;
	private String opcion;
	private ResourceBundle textBundle;
	private ResourceBundle config;
	private boolean reload = false;
	private boolean activado = false;
	private String exito;
	private String error;
	private TimeZone tz;
	private static final String PATH_Mensajes = "com.bean.comun.mensajes.Messages";
	private static final String PATH_Texto = "com.bean.text";

	/**
	 * Add a message
	 * 
	 * @param key
	 * @param bundle
	 * @param mySeverity
	 */
	protected void addGlobalMessage(String key, Severity mySeverity) {
		String message = getTextBundleKey(key);
		addGlobalMessageFromString(message, mySeverity);
	}

	/**
	 * Add a message
	 * 
	 * @param message
	 * @param mySeverity
	 */
	protected void addGlobalMessageFromString(String message,
			Severity mySeverity) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(mySeverity, message, message));
	}

	/**
	 * Search for the value of a given key in the application loaded bundle
	 * 
	 * @param key
	 * @return the description for the key
	 * 
	 */
	protected String getMensajesBundleKey(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(PATH_Mensajes,
				getFacesContext().getExternalContext().getRequestLocale());
		return bundle.getString(key);
	}

	/**
	 * Search for the value of a given key in the application loaded bundle
	 * 
	 * @param key
	 * @return the description for the key
	 */
	protected String getTextBundleKey(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(PATH_Texto,
				getFacesContext().getExternalContext().getRequestLocale());
		return bundle.getString(key);
	}

	/**
	 * Obtain the application faces context
	 * 
	 * @return the FacesContext
	 */
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@SuppressWarnings("unchecked")
	public static MaestroBean getInstance() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map session = context.getSessionMap();
		MaestroBean g = (MaestroBean) session.get("MaestroBean");
		if (g == null) {
			g = new MaestroBean();
			session.put("MaestroBean", g);
		}
		return g;
	}


	/**
	 * @return the time zone for the application
	 */
	public TimeZone getTz() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		tz = cal.getTimeZone();
		return tz;
	}

	@SuppressWarnings("unchecked")
	public Object getSesion(String pClave) {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map session = context.getSessionMap();
		Object g = session.get(pClave);
		return g;
	}

	/**
	 * @param tz
	 */
	public void setTz(TimeZone tz) {
		this.tz = tz;
	}

	@SuppressWarnings("unchecked")
	public void setSesion(String pClave, Object pObjeto) {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map session = context.getSessionMap();
		session.put(pClave, pObjeto);
	}

	@SuppressWarnings("unchecked")
	public void removerSesion(String pClave) {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map session = context.getSessionMap();
		session.remove(pClave);
	}

	@SuppressWarnings("unchecked")
	public void limpiarSesion() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map session = context.getSessionMap();
		session.clear();
	}
	
	/**
	 * Returns the default Locale according to the ISO 3166
	 * 
	 * @return the country code
	 */
	public Locale getLocale() {
		return getFacesContext().getExternalContext().getRequestLocale();
	}

	/**
	 * Gets the error Business Error Messages Localized for the Base Back Bean
	 * 
	 * @param be
	 * @return
	 * @MCUnnn
	 */
	public String getErrorMessage(String be) {
		return getMensajesBundleKey(be);
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public boolean isActivado() {
		return activado;
	}

	public ResourceBundle getTextBundle() {
		if (textBundle == null) {
			textBundle = ResourceBundle.getBundle("text", getFacesContext()
					.getExternalContext().getRequestLocale());
		}
		return textBundle;
	}

	public ResourceBundle getConfig() {
		if (config == null) {
			config = ResourceBundle.getBundle("qgerbil");
		}
		return config;
	}

	public SEGFachada getSegFachada(Servicio servicio) {
		return new SEGFachada(servicio);
	}

	public ADMFachada getAdmFachada(Servicio servicio) {
		return new ADMFachada(servicio);
	}

	public ComunFachada getComunFachada(Servicio servicio) throws IOException {
		return new ComunFachada(servicio);
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public void setTextBundle(ResourceBundle textBundle) {
		this.textBundle = textBundle;
	}

	public void setConfig(ResourceBundle config) {
		this.config = config;
	}

	public String getExito() {
		return exito;
	}

	public void setExito(String exito) {
		this.exito = exito;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
