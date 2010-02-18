package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity(name = "Ciudad")
@Table(name = "tl_adm_ciud_ciudad")
public class Ciudad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ciud_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name = "ciud_str_nombre", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _nombre;
	@OneToOne(cascade = CascadeType.ALL, targetEntity=Departamento.class)
	@ForeignKey (name="FK_deto_num_id")
	@JoinColumn(name="ciud_num_id_departamento", nullable=true, columnDefinition="BIGINT(20)")
	private Departamento _departamento;

	public Ciudad() {
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

	public Departamento get_departamento() {
		return _departamento;
	}

	public void set_departamento(Departamento departamento) {
		_departamento = departamento;
	}

}
