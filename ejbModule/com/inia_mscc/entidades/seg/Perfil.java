package com.inia_mscc.entidades.seg;

import java.util.List;

import com.inia_mscc.negocio.adm.Transaccion;
import com.inia_mscc.negocio.adm.ValorSeleccion;
import com.inia_mscc.negocio.comun.Enumerados;
import com.inia_mscc.negocio.comun.Objeto;

public class Perfil extends Objeto {
	private String _nombre;
	private String _descripcion;
	private ValorSeleccion _tipoPerfil; 
	private List<Transaccion> _transaccionesSistema;
	
	public Perfil() {
		super();
		_nombre = null;
		_descripcion = null;
		_tipoPerfil = null;
		_transaccionesSistema = null;
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String nombre) {
		_nombre = nombre;
	}
	public String get_descripcion() {
		return _descripcion;
	}
	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}
	public ValorSeleccion get_tipoPerfil() {
		return _tipoPerfil;
	}
	public void set_tipoPerfil(ValorSeleccion tipoPerfil) {
		_tipoPerfil = tipoPerfil;
	}
	public List<Transaccion> get_transaccionesSistema() {
		return _transaccionesSistema;
	}
	public void set_transaccionesSistema(List<Transaccion> transaccionesSistema) {
		_transaccionesSistema = transaccionesSistema;
	}
	
}
