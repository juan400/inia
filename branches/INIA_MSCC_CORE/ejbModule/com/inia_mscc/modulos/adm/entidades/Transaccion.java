package com.inia_mscc.modulos.adm.entidades;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;

@Entity(name = "Transaccion")
@Table(name = "tl_adm_tran_transaccion")
public class Transaccion implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tran_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Enumerated(EnumType.STRING)
	@Column(name = "tran_num_id_estado", nullable = false, columnDefinition = "VARCHAR(10)")
	private Estado _estado;
	@Column(name = "tran_str_codigo", nullable = true, columnDefinition = "VARCHAR(6)")
	private String _codigo;
	@Column(name = "tran_str_descripcion", nullable = true, columnDefinition = "VARCHAR(220)")
	private String _descripcion;
	@Column(name = "tran_bol_definida", nullable = false, columnDefinition = "TINYINT(1)")
	private Boolean _definida;
	@Column(name = "tran_str_url", nullable = false, columnDefinition = "VARCHAR(250)")
	private String _url;
	@Column(name = "tran_str_codigo_base", nullable = false, columnDefinition = "VARCHAR(6)")
	private String _codigoBase;
	@Column(name = "tran_str_descripcion_base", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _descripcionBase;
	@Column(name = "tran_dte_timestamp", nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _timestamp;
	@Transient
	private Boolean _asociada;
		
	public Transaccion() {
		_descripcionBase = null;
		_codigoBase = null;
		_codigo = null;
		_descripcion = null;
		_url = null;
		_definida = false;
		_asociada = false;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public Estado get_estado() {
		return _estado;
	}

	public void set_estado(Estado estado) {
		_estado = estado;
	}

	public String get_codigo() {
		return _codigo;
	}

	public void set_codigo(String codigo) {
		_codigo = codigo;
	}

	public String get_descripcion() {
		return _descripcion;
	}

	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}

	public Boolean get_definida() {
		return _definida;
	}

	public void set_definida(Boolean definida) {
		_definida = definida;
	}

	public String get_url() {
		return _url;
	}

	public void set_url(String url) {
		_url = url;
	}

	public String get_codigoBase() {
		return _codigoBase;
	}

	public void set_codigoBase(String codigoBase) {
		_codigoBase = codigoBase;
	}

	public String get_descripcionBase() {
		return _descripcionBase;
	}

	public void set_descripcionBase(String descripcionBase) {
		_descripcionBase = descripcionBase;
	}

	public Date get_timestamp() {
		return _timestamp;
	}

	public void set_timestamp(Date timestamp) {
		_timestamp = timestamp;
	}

	public void set_asociada(Boolean _asociada) {
		this._asociada = _asociada;
	}

	public Boolean get_asociada() {
		return _asociada;
	}

}
