package com.bean.seg.pruebas;

import java.io.Serializable;

public class PanelMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String current;


	public void setCurrent(String current) {
		this.current = current;
	}


	public String getCurrent() {
		return current;
	}
	
	public void updateCurrent(String current){
		this.setCurrent(current);
		
	}
}
