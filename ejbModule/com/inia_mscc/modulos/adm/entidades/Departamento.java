package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

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
//	@OneToOne(cascade = CascadeType.ALL, targetEntity=Pais.class)
//	@PrimaryKeyJoinColumn(name="deto_num_id_pais",columnDefinition="BIGINT(20)") 
	@OneToOne(cascade = CascadeType.ALL, targetEntity=Pais.class)
	@ForeignKey (name="FK_pais_num_id")
	@JoinColumn(name="deto_num_id_pais", nullable=true, columnDefinition="BIGINT(20)")
	private Pais _pais;
    @OneToMany(targetEntity=Ciudad.class, mappedBy="_departamento",cascade=CascadeType.ALL)
	private Collection<Ciudad> _ciudades;
	
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

	public Collection<Ciudad> get_ciudades() {
		return _ciudades;
	}

	public void set_ciudades(Collection<Ciudad> ciudades) {
		_ciudades = ciudades;
	}
	
}
