package com.inia_mscc.entidades.seg;

import java.util.Date;

import com.inia_mscc.entidades.comun.Enumerados;
import com.inia_mscc.entidades.comun.Objeto;
import com.inia_mscc.entidades.comun.Enumerados.EstadoUsuario;

public class Usuario extends Objeto
{
	private String _login;
	private String _password;
	private boolean _activado;
	private Date _ultimoAcceso;
	private Enumerados.EstadoUsuario _estado;
	private DatoUsuario _datos;
	
	public Usuario() {
		super();
		_login = null;
		_password = null;
		_activado = false;
		_ultimoAcceso = new Date();
		_estado = Enumerados.EstadoUsuario.Ninguno;
		_datos = null;
	}
	
	public String get_login() {
		return _login;
	}
	public void set_login(String login) {
		_login = login;
	}
	public String get_password() {
		return _password;
	}
	public void set_password(String password) {
		_password = password;
	}
	public boolean is_activado() {
		return _activado;
	}
	public void set_activado(boolean activado) {
		_activado = activado;
	}
	public Date get_ultimoAcceso() {
		return _ultimoAcceso;
	}
	public void set_ultimoAcceso(Date ultimoAcceso) {
		_ultimoAcceso = ultimoAcceso;
	}
	public Enumerados.EstadoUsuario get_estado() {
		return _estado;
	}
	public void set_estado(Enumerados.EstadoUsuario estado) {
		_estado = estado;
	}
	public DatoUsuario get_datos() {
		return _datos;
	}
	public void set_datos(DatoUsuario datos) {
		_datos = datos;
	}
}
