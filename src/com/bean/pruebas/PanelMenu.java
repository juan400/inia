package com.bean.pruebas;

import java.io.Serializable;
import java.net.URI;

import javax.faces.context.FacesContext;

public class PanelMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String current;
	private String pagina;
	private boolean singleMode;
	private boolean navegacion;
	
	
	public PanelMenu() {
		singleMode = true;
		current = "/Servicios/Precentacion.jsp";
	}

	public boolean isSingleMode() {
		return singleMode;
	}

	public void setSingleMode(boolean singleMode) {
		this.singleMode = singleMode;
	}
	

	public String updateCurrent() {
		navegacion = true;
		FacesContext context=FacesContext.getCurrentInstance();
		setCurrent(URI.create(context.getExternalContext().getRequestParameterMap().get("current")).toString());
		setPagina(context.getExternalContext().getRequestParameterMap().get("current").toString());
		System.out.println(this.getCurrent());
		setSingleMode(true);
		return "navegar";
	}
//	public void updateCurrent(ActionEvent event) {
//		setCurrent(URI.create(((UIPanelMenuItem)event.getComponent()).getLabel().toString()).toString());
//	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public boolean isNavegacion() {
		return navegacion;
	}

	public void setNavegacion(boolean navegacion) {
		this.navegacion = navegacion;
	}
}
