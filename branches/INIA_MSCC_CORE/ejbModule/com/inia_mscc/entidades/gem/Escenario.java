package com.inia_mscc.entidades.gem;

import java.util.Date;

import com.inia_mscc.entidades.adm.Region;
import com.inia_mscc.entidades.comun.Objeto;
import com.inia_mscc.entidades.seg.Usuario;

public class Escenario extends Objeto {
	
	private Date _fechaHora;
	private Usuario _usuario;
	private Region _region;
	private Cultivo _cultivo;
	private Archivo _archivoEscenario;
	private Archivo _archivoMSCC;
	private Archivo _archivoResultado;
	private Archivo _resultaroMSCC;
	
	public Escenario() {
		super();
		_fechaHora = new Date();
		_usuario = null;
		_region = null;
		_cultivo = null;
		_archivoEscenario = null;
		_archivoMSCC = null;
		_archivoResultado = null;
		_resultaroMSCC = null;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Usuario get_usuario() {
		return _usuario;
	}

	public void set_usuario(Usuario usuario) {
		_usuario = usuario;
	}

	public Region get_region() {
		return _region;
	}

	public void set_region(Region region) {
		_region = region;
	}

	public Cultivo get_cultivo() {
		return _cultivo;
	}

	public void set_cultivo(Cultivo cultivo) {
		_cultivo = cultivo;
	}

	public Archivo get_archivoEscenario() {
		return _archivoEscenario;
	}

	public void set_archivoEscenario(Archivo archivoEscenario) {
		_archivoEscenario = archivoEscenario;
	}

	public Archivo get_archivoMSCC() {
		return _archivoMSCC;
	}

	public void set_archivoMSCC(Archivo archivoMSCC) {
		_archivoMSCC = archivoMSCC;
	}

	public Archivo get_archivoResultado() {
		return _archivoResultado;
	}

	public void set_archivoResultado(Archivo archivoResultado) {
		_archivoResultado = archivoResultado;
	}

	public Archivo get_resultaroMSCC() {
		return _resultaroMSCC;
	}

	public void set_resultaroMSCC(Archivo resultaroMSCC) {
		_resultaroMSCC = resultaroMSCC;
	}
	
}
