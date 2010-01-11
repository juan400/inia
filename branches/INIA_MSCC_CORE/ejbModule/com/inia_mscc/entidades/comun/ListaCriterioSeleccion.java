/**
 * 
 */
package com.inia_mscc.entidades.comun;

import java.util.List;

/**
 * @author Juan Andres Pio
 *
 */
public class ListaCriterioSeleccion extends Objeto {
	private String _codigo;
	private String _nombre;
	private boolean _modificable;
	private List<ValorSeleccion> _listaValores;
	
	public ListaCriterioSeleccion() {
		super();
		_codigo = null;
		_nombre = null;
		_modificable = false;
		_listaValores = null;
	}
	
	public String get_codigo() {
		return _codigo;
	}
	public void set_codigo(String codigo) {
		_codigo = codigo;
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String nombre) {
		_nombre = nombre;
	}
	public boolean is_modificable() {
		return _modificable;
	}
	public void set_modificable(boolean modificable) {
		_modificable = modificable;
	}
	public List<ValorSeleccion> get_listaValores() {
		return _listaValores;
	}
	public void set_listaValores(List<ValorSeleccion> listaValores) {
		_listaValores = listaValores;
	}
	
	
	
}
