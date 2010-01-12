package com.inia_mscc.negocio.gem;

import java.net.URL;
import java.util.Date;

import com.inia_mscc.negocio.comun.Objeto;

public class Ubicacion extends Objeto {
	
	private String _descripcion;
	private Date _fecha;
	private URL _urlPaht;

	public Ubicacion() {
		super();
		_descripcion = null;
		_fecha = null;
		_urlPaht = null;
	}

	public String get_descripcion() {
		return _descripcion;
	}

	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}

	public Date get_fecha() {
		return _fecha;
	}

	public void set_fecha(Date fecha) {
		_fecha = fecha;
	}

	public URL get_urlPaht() {
		return _urlPaht;
	}

	public void set_urlPaht(URL urlPaht) {
		_urlPaht = urlPaht;
	}

}
