package com.inia_mscc.negocio.comun.entidades;

public class Objeto {
	private long _id; 
	private Enumerados.Estado _estado;
	private Enumerados.EstadoModificacion _estadoModificacion;
	private byte[] _timeStamp;

	
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


	public byte[] get_timeStamp() {
		return _timeStamp;
	}


	public void set_timeStamp(byte[] timeStamp) {
		_timeStamp = timeStamp;
	}

}
