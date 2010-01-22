package com.inia_mscc.modulos.seg.entidades;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.inia_mscc.modulos.comun.entidades.Enumerados;

@Entity(name = "Usuario")
@Table(name = "tl_seg_usua_usuario")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usua_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name = "usua_str_login", nullable = false, columnDefinition = "VARCHAR(20)")
	private String _login;
	@Column(name = "usua_str_password", nullable = false, columnDefinition = "VARCHAR(13)")
	private String _password;
	@Column(name = "usua_bol_activado", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean _activado;
	@Column(name = "usua_dte_ultimo_acceso", nullable = false, columnDefinition = "DATETIME")
	private Date _ultimoAcceso;
	@Enumerated(EnumType.STRING)
	@Column(name = "usua_str_estado_usuario", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.EstadoUsuario _estadoUsuario;
	@OneToOne(cascade = CascadeType.ALL, targetEntity=DatoUsuario.class)
	@PrimaryKeyJoinColumn(name="usua_num_id_dato_usuario",columnDefinition="BIGINT(20)") 
	private DatoUsuario _datos;

	public Usuario() {
		super();
		_login = null;
		_password = null;
		_activado = false;
		//_ultimoAcceso = new 
		_estadoUsuario = Enumerados.EstadoUsuario.Ninguno;
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

	public void set_activado(boolean _activado) {
		this._activado = _activado;
	}

	public boolean is_activado() {
		return _activado;
	}

	public void set_estadoUsuario(Enumerados.EstadoUsuario _estadoUsuario) {
		this._estadoUsuario = _estadoUsuario;
	}

	public Enumerados.EstadoUsuario get_estadoUsuario() {
		return _estadoUsuario;
	}

	public void set_ultimoAcceso(Date _ultimoAcceso) {
		this._ultimoAcceso = _ultimoAcceso;
	}

	public Date get_ultimoAcceso() {
		return _ultimoAcceso;
	}
	
	public DatoUsuario get_datos() {
		return _datos;
	}

	public void set_datos(DatoUsuario datos) {
		_datos = datos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.get_login()+this.get_ultimoAcceso()+ this.get_estadoUsuario();
	}
	
}
