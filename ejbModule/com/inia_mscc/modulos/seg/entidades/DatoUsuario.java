package com.inia_mscc.modulos.seg.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.comun.entidades.Enumerados;

@Entity(name = "DatoUsuario")
@Table(name = "tl_seg_daus_datosusuario")
public class DatoUsuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "daus_num_id", updatable= false, nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Enumerated(EnumType.STRING)
	@Column(name = "daus_num_id_estado", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.Estado _estado;
	@Column(name = "daus_str_nombre", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _nombre;
	@Column(name = "daus_str_apellido", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _apellido;
	@Column(name = "daus_str_email", nullable = false, columnDefinition = "VARCHAR")
	private String _mail;
	@Column(name = "daus_str_telefono", nullable = true, columnDefinition = "VARCHAR(45)")
	private String _tele;
	@Column(name = "daus_str_celular", nullable = true, columnDefinition = "VARCHAR(9)")
	private String _cel;
	@Column(name = "daus_str_direccion", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _direccion;
	@OneToOne (targetEntity=Pais.class)
	@ForeignKey (name="FK_daus_num_id_pais")
	@JoinColumn (name="daus_num_id_pais", nullable=true, columnDefinition="BIGINT(20)")
	private Pais _pais;
	@OneToOne(targetEntity=Departamento.class)
	@ForeignKey (name="FK_daus_num_id_departamento")
	@JoinColumn(name="daus_num_id_departamento", nullable=true, columnDefinition="BIGINT(20)")  
	private Departamento _departamento;
	@OneToOne(targetEntity=Ciudad.class)
	@ForeignKey (name="FK_daus_num_id_ciudad")
	@JoinColumn(name="daus_num_id_ciudad", nullable=true, columnDefinition="BIGINT(20)") 
	private Ciudad _ciudad;
	@Column(name = "daus_dte_fecha_registro", updatable= false, nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaRegistro;
	@OneToOne(targetEntity=Perfil.class)
	@ForeignKey (name="FK_daus_num_id_perfil")
	@JoinColumn(name="daus_num_id_perfil", nullable=true, columnDefinition="BIGINT(20)") 
	private Perfil _perfil;
//	@Version 
	@Column(name="daus_dte_timestamp", nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _timeStamp;
  
	public DatoUsuario() {
		_id = 0;
		_estado = Enumerados.Estado.Activo;
		_nombre = null;
		_fechaRegistro = new Date();
		_timeStamp = new Date();
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public Enumerados.Estado get_estado() {
		return _estado;
	}

	public void set_estado(Enumerados.Estado estado) {
		_estado = estado;
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
	
	public Date get_timeStamp() {
		return _timeStamp;
	}

	public void set_timeStamp(Date timeStamp) {
		_timeStamp = timeStamp;
	}

	public Pais get_pais() {
		return _pais;
	}

	public void set_pais(Pais pais) {
		_pais = pais;
	}

	public Departamento get_departamento() {
		return _departamento;
	}

	public void set_departamento(Departamento departamento) {
		_departamento = departamento;
	}

	public Ciudad get_ciudad() {
		return _ciudad;
	}

	public void set_ciudad(Ciudad ciudad) {
		_ciudad = ciudad;
	}

	public Date get_fechaRegistro() {
		return _fechaRegistro;
	}

	public void set_fechaRegistro(Date fechaRegistro) {
		_fechaRegistro = fechaRegistro;
	}

	public Perfil get_perfil() {
		return _perfil;
	}

	public void set_perfil(Perfil perfil) {
		_perfil = perfil;
	}

}