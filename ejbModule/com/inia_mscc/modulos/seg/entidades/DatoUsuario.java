package com.inia_mscc.modulos.seg.entidades;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

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
	@Column(name = "daus_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Enumerated(EnumType.STRING)
	@Column(name = "daus_num_id_estado", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.Estado _estado;
	@Column(name = "daus_str_nombre", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _nombre;
//	@Version 
//    @Column(name="daus_dte_timestamp", nullable = false, columnDefinition = "TIMESTAMP")
//	private Timestamp _timeStamp;
//	@Column(name = "daus_str_apellido", nullable = false, columnDefinition = "VARCHAR(220)")
//	private String _apellido;
//	@Column(name = "daus_dte_fecha_registro", nullable = false, columnDefinition = "DATETIME")
//	private Date _fechaRegistro;
//	@Column(name = "daus_str_email", nullable = false, columnDefinition = "VARCHAR(220)")
//	private String _mail;
//	@Column(name = "daus_str_telefono", nullable = false, columnDefinition = "VARCHAR(45)")
//	private String _tele;
//	@Column(name = "daus_str_celular", nullable = true, columnDefinition = "VARCHAR(9)")
//	private String _cel;
//	@Column(name = "daus_str_direccion", nullable = true, columnDefinition = "VARCHAR(250)")
//	private String _direccion;
	
	// private Pais _pais;
	// private Estado _estado;
	// private Ciudad _ciudad;
	// private Perfil _perfil;

	public DatoUsuario() {
		super();
		_id = 0;
		_estado = Enumerados.Estado.Activo;
		_nombre = null;
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


}