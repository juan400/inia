package com.inia_mscc.modulos.gem.entidades;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Transient;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;

public class Archivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _nombre;
	private Enumerados.TipoArchivo _tipo;
	private Date _fechaHora;
	private Enumerados.EstadoArchivo _estadoArchivo;
	private Enumerados.TipoExtencionArchivo _extencion;
	private Ubicacion _ubicacion;
	@Transient
	private File _datos;

	public Archivo() {
		super();
		_nombre = null;
		_fechaHora = new Date();
		_estadoArchivo = Enumerados.EstadoArchivo.Ninguno;
	}

	public Archivo(String pNombreLogin, TipoArchivo tipo, Date fechaHora,
			EstadoArchivo estadoArchivo, TipoExtencionArchivo extencion,
			Ubicacion ubicacion) {
		super();
		set_nombre(pNombreLogin);
		_tipo = tipo;
		_fechaHora = fechaHora;
		_estadoArchivo = estadoArchivo;
		_extencion = extencion;
		_ubicacion = ubicacion;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String pNombreLogin) {
		Calendar pFecha = new GregorianCalendar();
		String fechaEscrita = pFecha.get(Calendar.YEAR) + "-"
				+ (pFecha.get(Calendar.MONTH) + 1) + "-"
				+ pFecha.get(Calendar.DAY_OF_MONTH) + " "
				+ pFecha.get(Calendar.HOUR_OF_DAY) + ":"
				+ pFecha.get(Calendar.MINUTE) + ":"
				+ pFecha.get(Calendar.SECOND);
		String nombreArchivo = _ubicacion.get_urlPaht().toString()
				+ pNombreLogin + "_" + fechaEscrita + ".py";
		_nombre = nombreArchivo;
	}

	public Enumerados.TipoArchivo get_tipo() {
		return _tipo;
	}

	public void set_tipo(Enumerados.TipoArchivo tipo) {
		_tipo = tipo;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Enumerados.EstadoArchivo get_estadoArchivo() {
		return _estadoArchivo;
	}

	public void set_estadoArchivo(Enumerados.EstadoArchivo estadoArchivo) {
		_estadoArchivo = estadoArchivo;
	}

	public Enumerados.TipoExtencionArchivo get_extencion() {
		return _extencion;
	}

	public void set_extencion(Enumerados.TipoExtencionArchivo extencion) {
		_extencion = extencion;
	}

	public Ubicacion get_ubicacion() {
		return _ubicacion;
	}

	public void set_ubicacion(Ubicacion ubicacion) {
		_ubicacion = ubicacion;
	}

	public File get_datos() {
		return _datos;
	}

	public void set_datos(File datos) {
		_datos = datos;
	}

}