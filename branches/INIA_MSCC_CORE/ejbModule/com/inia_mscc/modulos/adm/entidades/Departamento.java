package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity (name="Departamento")
@Table (name="tl_adm_deto_departamento")
public class Departamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="deto_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name="deto_str_nombre", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _nombre;
	@OneToOne(cascade = CascadeType.ALL, targetEntity=Pais.class)
	@PrimaryKeyJoinColumn(name="deto_num_id_pais",columnDefinition="BIGINT(20)") 
	private Pais _pais;
	
	public Departamento() {
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

	public Pais get_pais() {
		return _pais;
	}

	public void set_pais(Pais pais) {
		_pais = pais;
	}
	
}
