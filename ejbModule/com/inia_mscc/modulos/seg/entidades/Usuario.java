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
import javax.persistence.Transient;
//import javax.persistence.FetchType;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
//import com.inia_mscc.modulos.comun.entidades.Objeto;


/**
 * @author Juan Andres Pio
 *	@AttributeOverride( name="id", column = @Column(name="usua_num_id") )
	Cuando tenemos que sobre escribir mapeos
	de la superclase en la clase actual se usa esto.
	Por ejemplo: como todas las clases heredan de Objeto, 
	todas van a implementar esto
 */
//@AttributeOverrides(
//	{
//		@AttributeOverride(name = "id",column = @Column(name = "usua_num_id"))
//		,@AttributeOverride(name = "timestamp",column = @Column(name = "usua_tst_timestamp"))
//	})
/**
 * @author Juan Andres Pio
 *  Para generar el mapeo de una asociacion que implementa una clase padre,
 *  y nosotros necesitamos mapearla con columnas de la endidad hija, usamos esto:
 */	
//@AssociationOverride( name="propulsion", joinColumns = @JoinColumn(name="fld_propulsion_fk") )

//public class Usuario extends Objeto implements Serializable
@Entity @Table(name="tl_seg_usua_usuario")
public class Usuario implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317547340768515297L;
	
	private long _id;
	private String _login;
	private String _password;
//	private boolean _activado;
//	private Date _ultimoAcceso;
//	private Enumerados.EstadoUsuario _estadoUsuario;
//	private DatoUsuario _datos;
	
	public Usuario() {
		super();
		_login = null;
		_password = null;
//		_activado = false;
//		_ultimoAcceso = new Date();
//		_estadoUsuario = Enumerados.EstadoUsuario.Ninguno;
//		_datos = null;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usua_num_id")
	public long get_id() {
		return _id;
	}


	public void set_id(long id) {
		_id = id;
	}
	
	@Column
	(name="usua_str_login")
//			,unique=true 
//			,nullable=false
//			,updatable=false
//			,length=220
//	)
	public String get_login() {
		return _login;
	}
	public void set_login(String login) {
		_login = login;
	}

	@Column (name="usua_str_password"
//		,nullable=false
//		,length=13
		)
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
