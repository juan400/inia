package com.inia_mscc.negocio.eje.entidades;

import java.util.Date;

import com.inia_mscc.negocio.comun.entidades.Objeto;
import com.inia_mscc.negocio.gem.entidades.Archivo;
import com.inia_mscc.negocio.gem.entidades.Escenario;
import com.inia_mscc.negocio.seg.entidades.Usuario;

public class EjecucionMSCC extends Objeto {

	private Date _fechaHora;
	private Usuario _usuarioEjecutante;
	private Archivo _archivoEjecucion;
	private Escenario _escenario;
	private Archivo _archivoResultado;
	private ResultadoMSCC _resultaroMSCC;
	
	public EjecucionMSCC() {
		super();
		_fechaHora = new Date();
		_usuarioEjecutante = null;
		_archivoEjecucion = null;
		_escenario = null;
		_archivoResultado = null;
		_resultaroMSCC = null;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Usuario get_usuarioEjecutante() {
		return _usuarioEjecutante;
	}

	public void set_usuarioEjecutante(Usuario usuarioEjecutante) {
		_usuarioEjecutante = usuarioEjecutante;
	}

	public Archivo get_archivoEjecucion() {
		return _archivoEjecucion;
	}

	public void set_archivoEjecucion(Archivo archivoEjecucion) {
		_archivoEjecucion = archivoEjecucion;
	}

	public Escenario get_escenario() {
		return _escenario;
	}

	public void set_escenario(Escenario escenario) {
		_escenario = escenario;
	}

	public Archivo get_archivoResultado() {
		return _archivoResultado;
	}

	public void set_archivoResultado(Archivo archivoResultado) {
		_archivoResultado = archivoResultado;
	}

	public ResultadoMSCC get_resultaroMSCC() {
		return _resultaroMSCC;
	}

	public void set_resultaroMSCC(ResultadoMSCC resultaroMSCC) {
		_resultaroMSCC = resultaroMSCC;
	}
	
}
