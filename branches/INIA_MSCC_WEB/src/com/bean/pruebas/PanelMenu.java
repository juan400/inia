package com.bean.pruebas;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIPanelMenuItem;

public class PanelMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private URI current;
	private String pagina;
	private boolean singleMode;
	
	
	public PanelMenu() {
		super();
		singleMode = true;
		pagina = "Precentacion.jsp";
	}

	public boolean isSingleMode() {
		return singleMode;
	}

	public void setSingleMode(boolean singleMode) {
		this.singleMode = singleMode;
	}
	
	public URI getCurrent() {
		return this.current;
	}
	
	public void setCurrent(URI current) {
		this.current = current;
	}
	public String updateCurrent() {
		FacesContext context=FacesContext.getCurrentInstance();
		setCurrent(URI.create(context.getExternalContext().getRequestParameterMap().get("current")));
		setPagina(context.getExternalContext().getRequestParameterMap().get("current"));
		System.out.println("fake called.");
		setSingleMode(true);
		return null;
	}
	public void updateCurrent(ActionEvent event) {
		setCurrent(URI.create(((UIPanelMenuItem)event.getComponent()).getLabel().toString()));
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
}
