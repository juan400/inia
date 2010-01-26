package com.bean.pruebas;

import java.io.Serializable;
import java.net.URI;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.richfaces.component.UIPanelMenuItem;

public class PanelMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private URI current;
	private boolean singleMode;
	
	public boolean isSingleMode() {
		return singleMode;
	}

	public void setSingleMode(boolean singleMode) {
		this.singleMode = singleMode;
	}

	public PanelMenu() {
		singleMode = true;
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
		System.out.println("fake called.");
		setSingleMode(true);
		return null;
	}
	public void updateCurrent(ActionEvent event) {
		setCurrent(URI.create(((UIPanelMenuItem)event.getComponent()).getLabel().toString()));
	}
}
