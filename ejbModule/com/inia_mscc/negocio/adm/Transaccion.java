package com.inia_mscc.negocio.adm;

import com.inia_mscc.negocio.comun.Objeto;


public class Transaccion extends Objeto {
	
	private String _nombreBase;
	private String _codigoBase;
	private String _codigo;
	private String _nombre;
	private String _url;
	private Boolean _definida;
	
		
	public Transaccion() {
		super();
		_nombreBase = null;
		_codigoBase = null;
		_codigo = null;
		_nombre = null;
		_url = null;
		_definida = null;
	}

	/**
	 * @author Juan Andres Pio
	 *
	 */
	public String get_nombreBase() {
		return _nombreBase;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public void set_nombreBase(String nombreBase) {
		_nombreBase = nombreBase;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public String get_codigoBase() {
		return _codigoBase;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public void set_codigoBase(String codigoBase) {
		_codigoBase = codigoBase;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public String get_codigo() {
		return _codigo;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public void set_codigo(String codigo) {
		_codigo = codigo;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public String get_nombre() {
		return _nombre;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public void set_nombre(String nombre) {
		_nombre = nombre;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public String get_url() {
		return _url;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public void set_url(String url) {
		_url = url;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public Boolean get_definida() {
		return _definida;
	}
	
	/**
	 * @author Juan Andres Pio
	 *
	 */
	public void set_definida(Boolean definida) {
		_definida = definida;
	}
}
