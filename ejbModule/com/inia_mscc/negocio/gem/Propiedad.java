package com.inia_mscc.negocio.gem;

import com.inia_mscc.negocio.comun.entidades.Enumerados;
import com.inia_mscc.negocio.comun.entidades.Objeto;

public class Propiedad extends Objeto {
	private String _codigo;
	private String _nombre;
	private Enumerados.TipoPropiedadCultivo _tipo;
	
	public Propiedad() {
		super();
		_codigo = null;
		_nombre = null;
		_tipo = Enumerados.TipoPropiedadCultivo.Ninguno;
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
	public Enumerados.TipoPropiedadCultivo get_tipo() {
		return _tipo;
	}
	public void set_tipo(Enumerados.TipoPropiedadCultivo tipo) {
		_tipo = tipo;
	}

}
