package com.inia_mscc.negocio.seg;

import java.util.Date;

import com.inia_mscc.negocio.comun.Enumerados;
import com.inia_mscc.negocio.comun.Objeto;
import com.inia_mscc.negocio.comun.Enumerados.EstadoUsuario;

public class Usuario extends Objeto
{
	private String _login;
	private String _password;
	private boolean _activado;
	private Date _ultimoAcceso;
	private Enumerados.EstadoUsuario _estadoUsuario;
	private DatoUsuario _datos;
	
	public Usuario() {
		super();
		_login = null;
		_password = null;
		_activado = false;
		_ultimoAcceso = new Date();
		_estadoUsuario = Enumerados.EstadoUsuario.Ninguno;
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
	public Enumerados.EstadoUsuario get_estadoUsuario() {
		return _estadoUsuario;
	}
	public void set_estadoUsuario(Enumerados.EstadoUsuario estadoUsuario) {
		_estadoUsuario = estadoUsuario;
	}
	public DatoUsuario get_datos() {
		return _datos;
	}
	public void set_datos(DatoUsuario datos) {
		_datos = datos;
	}
}
