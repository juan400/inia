package com.inia_mscc.negocio.adm.entidades;

import com.inia_mscc.negocio.comun.entidades.Objeto;

public class ValorSeleccion extends Objeto {
	private String _codigo; 
	private String _descripcion;
	private String _unidadMedida;
	private long _idListaCriterioSeleccion;
	
	public ValorSeleccion() {
		super();
		_codigo = null;
        _descripcion = null;
        _unidadMedida = null;
        _idListaCriterioSeleccion = 0;
	}
	
	public String get_codigo() {
		return _codigo;
	}
	public void set_codigo(String codigo) {
		_codigo = codigo;
	}
	public String get_descripcion() {
		return _descripcion;
	}
	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}
	public String get_unidadMedida() {
		return _unidadMedida;
	}
	public void set_unidadMedida(String unidadMedida) {
		_unidadMedida = unidadMedida;
	}
	public long get_idListaCriterioSeleccion() {
		return _idListaCriterioSeleccion;
	}
	public void set_idListaCriterioSeleccion(long idListaCriterioSeleccion) {
		_idListaCriterioSeleccion = idListaCriterioSeleccion;
	} 
	
}
