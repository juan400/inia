package com.inia_mscc.modulos.seg.entidades;

import java.io.Serializable;
import java.util.Date;

//import javax.persistence.AssociationOverride;
//import javax.persistence.AttributeOverride;
//import javax.persistence.AttributeOverrides;
//import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
//import javax.persistence.FetchType;
//import javax.persistence.OneToOne;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

import com.inia_mscc.modulos.comun.entidades.Enumerados;


@Entity
	@Table(appliesTo="tl_seg_usua_usuario")
public class Usuario implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317547340768515297L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="usua_num_id")
	private long _id;
	@Column	(name="usua_str_login")
	private String _login;
	@Column (name="usua_str_password")
	private String _password;
	@Column (name="usua_bol_activado")
	private boolean _activado;
	@Column(name = "usua_dte_ultimo_acceso")
	@Temporal(TemporalType.TIMESTAMP)
	@org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
	private Date _ultimoAcceso;
	@Enumerated(EnumType.STRING)
	@Column (name="usua_str_estado_usuario")
	private Enumerados.EstadoUsuario _estadoUsuario;
//	private DatoUsuario _datos;
	
	public Usuario() {
		super();
		_login = null;
		_password = null;
		_activado = false;
		_ultimoAcceso = new Date();
		_estadoUsuario = Enumerados.EstadoUsuario.Ninguno;
//		_datos = null;
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

//	@Column (name="usua_bol_activado" 
//			,nullable=false
//			)
//	public boolean is_activado() {
//		return _activado;
//	}
//	
//	public void set_activado(boolean activado) {
//		_activado = activado;
//	}
//
//	@Column (name="usua_dte_ultimo_acceso"		
//			,nullable=false
//			)
//	public Date get_ultimoAcceso() {
//		return _ultimoAcceso;
//	}
//	
//	public void set_ultimoAcceso(Date ultimoAcceso) {
//		_ultimoAcceso = ultimoAcceso;
//	}
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="usua_estado_usuario"
//		,nullable=false
//		)
//	public Enumerados.EstadoUsuario get_estadoUsuario() {
//		return _estadoUsuario;
//	}
//	
//	public void set_estadoUsuario(Enumerados.EstadoUsuario estadoUsuario) {
//		_estadoUsuario = estadoUsuario;
//	}
//
//	//@Column (name="daus_num_ultimo_acceso")
////	@Basic(optional=true, fetch = FetchType.LAZY)
////	@OneToOne
//	@Transient
//	public DatoUsuario get_datos() {
//		return _datos;
//	}
//	
//	public void set_datos(DatoUsuario datos) {
//		_datos = datos;
//	}

}
