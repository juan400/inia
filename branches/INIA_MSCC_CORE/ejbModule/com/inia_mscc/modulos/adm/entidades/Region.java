package com.inia_mscc.modulos.adm.entidades;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Region")
@Table(name = "tl_adm_regi_regionclimatica")
public class Region  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "regi_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name = "regi_str_descripcion", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _descripcion;
	@Column(name = "regi_str_codigo", nullable = true, columnDefinition = "VARCHAR(6)")
	private String _codigo;
	@Column(name = "regi_str_nombre", nullable = false, columnDefinition = "VARCHAR(45)")
	private String _nombre;
	
	
	public Region() {
		_id = 0;
		_codigo = null;
		_nombre = null;
		_descripcion = null;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long get_id() {
		return _id;
	}

	public void set_codigo(String _codigo) {
		this._codigo = _codigo;
	}

	public String get_codigo() {
		return _codigo;
	}

	public void set_descripcion(String _descripcion) {
		this._descripcion = _descripcion;
	}

	public String get_descripcion() {
		return _descripcion;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public String get_nombre() {
		return _nombre;
	}
}
