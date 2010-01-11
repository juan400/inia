package com.inia_mscc.entidades.gem;

import java.util.List;

import com.inia_mscc.entidades.comun.Objeto;

public class Culvito extends Objeto{
	
	private String _descripcion;
	private List<Propiedad> _listaPropiedades;
	
	public Culvito() {
		super();
		_descripcion = null;
		_listaPropiedades = null;
	}
	
	public String get_descripcion() {
		return _descripcion;
	}
	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}
	public List<Propiedad> get_listaPropiedades() {
		return _listaPropiedades;
	}
	public void set_listaPropiedades(List<Propiedad> listaPropiedades) {
		_listaPropiedades = listaPropiedades;
	}
	
}
