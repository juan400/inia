package com.inia_mscc.modulos.seg.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Usuario")
@Table(name = "tl_seg_usua_usuario")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317547340768515297L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usua_num_id")
	private long _id;
	@Column(name = "usua_str_login")//, columnDefinition = "VARCHAR(20)")
	private String _login;
	@Column(name = "usua_str_password")//, columnDefinition = "VARCHAR(13)")
	private String _password;
//	@Column(name = "usua_bol_activado", columnDefinition = "TINYINT(1)")
//	private boolean _activado;

	// @Column(name = "usua_dte_ultimo_acceso")
	// @Temporal(TemporalType.TIMESTAMP)
	// @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
	// private Date _ultimoAcceso;
	// @Enumerated(EnumType.STRING)
	// @Column (name="usua_str_estado_usuario")
	// private Enumerados.EstadoUsuario _estadoUsuario;
	// private DatoUsuario _datos;

	public Usuario() {
		super();
		_login = null;
		_password = null;
//		_activado = false;
		// _ultimoAcceso = new Date();
		// _estadoUsuario = Enumerados.EstadoUsuario.Ninguno;
		// _datos = null;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
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

//	public boolean is_activado() {
//		return _activado;
//	}
//
//	public void set_activado(boolean activado) {
//		_activado = activado;
//	}

}
