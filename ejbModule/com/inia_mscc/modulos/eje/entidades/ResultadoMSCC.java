package com.inia_mscc.modulos.eje.entidades;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.collections.map.HashedMap;

import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Escenario;

public class ResultadoMSCC implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long _id;
	private Date _fecha;
	private Archivo _archivo;
	private HashedMap _matrizDatos;
	private Escenario _escenario;

	public ResultadoMSCC() {
		super();
		_fecha = new Date();
		_archivo = null;
		_matrizDatos = new HashedMap();
		_escenario = null;
	}

	public Date get_fecha() {
		return _fecha;
	}

	public void set_fecha(Date fecha) {
		_fecha = fecha;
	}

	public Archivo get_archivo() {
		return _archivo;
	}

	public void set_archivo(Archivo archivo) {
		_archivo = archivo;
	}
	
	public HashedMap get_matrizDatos() throws Exception {
		return _matrizDatos;
	}

	public void set_matrizDatos(HashedMap matrizDatos) {
		_matrizDatos = matrizDatos;
	}
	
	public Escenario get_escenario() {
		return _escenario;
	}

	public void set_escenario(Escenario escenario) {
		_escenario = escenario;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

}
