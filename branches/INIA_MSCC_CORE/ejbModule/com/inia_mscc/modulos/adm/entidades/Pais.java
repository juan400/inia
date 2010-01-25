package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Pais")
@Table(name = "tl_adm_pais_pais")
public class Pais implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pais_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name = "pais_str_nombre", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _nombre;

	public Pais() {
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String nombre) {
		_nombre = nombre;
	}

}
