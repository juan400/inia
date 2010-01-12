package com.inia_mscc.entidades.seg;

import com.inia_mscc.negocio.comun.Objeto;

public class DatoUsuario extends Objeto {
	private String _nombre;
	private String _apellido;
	private String _mail;
	private String _tele;
	private String _cel;
	private String _direccion;
	private Perfil _perfil;
	
	public DatoUsuario() {
		super();
		_nombre = null;
		_apellido = null;
		_mail = null;
		_tele = null;
		_cel = null;
		_direccion = null;
		_perfil = null;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String nombre) {
		_nombre = nombre;
	}

	public String get_apellido() {
		return _apellido;
	}

	public void set_apellido(String apellido) {
		_apellido = apellido;
	}

	public String get_mail() {
		return _mail;
	}

	public void set_mail(String mail) {
		_mail = mail;
	}

	public String get_tele() {
		return _tele;
	}

	public void set_tele(String tele) {
		_tele = tele;
	}

	public String get_cel() {
		return _cel;
	}

	public void set_cel(String cel) {
		_cel = cel;
	}

	public String get_direccion() {
		return _direccion;
	}

	public void set_direccion(String direccion) {
		_direccion = direccion;
	}

	public Perfil get_perfil() {
		return _perfil;
	}

	public void set_perfil(Perfil perfil) {
		_perfil = perfil;
	}
}
