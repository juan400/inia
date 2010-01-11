package com.inia_mscc.entidades.gem;

import java.util.Date;

import com.inia_mscc.entidades.comun.Enumerados;
import com.inia_mscc.entidades.comun.Objeto;
import com.inia_mscc.entidades.comun.Enumerados.EstadoArchivo;
import com.inia_mscc.entidades.comun.Enumerados.TipoArchivo;
import com.inia_mscc.entidades.comun.Enumerados.TipoExtencionArchivo;

public class Archivo extends Objeto {

	private String _nombre;
	private Enumerados.TipoArchivo _tipo;
	private Date _fechaHora;
	private Enumerados.EstadoArchivo _estadoArchivo;
	private Enumerados.TipoExtencionArchivo _extencion;
	//private Ubicacion _ubicacion;
	public Archivo() {
		super();
		_nombre = null;
		_tipo = Enumerados.TipoArchivo.Ninguno;
		_fechaHora = new Date();
		_estadoArchivo = Enumerados.EstadoArchivo.Ninguno;
		_extencion = Enumerados.TipoExtencionArchivo.txt;
		
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String nombre) {
		_nombre = nombre;
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
	
}
