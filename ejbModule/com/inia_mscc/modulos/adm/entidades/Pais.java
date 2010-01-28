package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany(targetEntity=Departamento.class, mappedBy="deto_num_id_pais",cascade=CascadeType.ALL)
	private Collection<Departamento> _departamentos;
	
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

	public Collection<Departamento> get_departamentos() {
		return _departamentos;
	}

	public void set_departamentos(Collection<Departamento> departamentos) {
		_departamentos = departamentos;
	}


}
