package com.inia_mscc.modulos.comun.entidades;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public class Objeto{
	 
	/**
	 * 
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long _id; 
	
	@Enumerated(EnumType.STRING)
	@Column (name="estado")
	private Enumerados.Estado _estado;
	
	@Transient
	private Enumerados.EstadoModificacion _estadoModificacion;
	
	@Version
    @Column(name="timestamp")
	private Timestamp _timeStamp;

	
	public Objeto() {
		super();
		_id = 0;
		_estado = Enumerados.Estado.Activo;
		_estadoModificacion = Enumerados.EstadoModificacion.Ninguno;
	}

	public long get_id() {
		return _id;
	}


	public void set_id(long id) {
		_id = id;
	}


	public Enumerados.Estado get_estado() {
		return _estado;
	}


	public void set_estado(Enumerados.Estado estado) {
		_estado = estado;
	}

	
	public Enumerados.EstadoModificacion get_estadoModificacion() {
		return _estadoModificacion;
	}


	public void set_estadoModificacion(
			Enumerados.EstadoModificacion estadoModificacion) {
		_estadoModificacion = estadoModificacion;
	}

	public Timestamp get_timeStamp() {
		return _timeStamp;
	}

	public void set_timeStamp(Timestamp timeStamp) {
		_timeStamp = timeStamp;
	}

}
