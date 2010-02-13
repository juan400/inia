package com.bean.gem;

import java.io.Serializable;
import java.util.Date;

import com.bean.comun.MaestroBean;

public class CultivoBean extends MaestroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date fechaCreación;
	private String nombre;
	private String descripción;
	private String estado;
	
	

	
}
