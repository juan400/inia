package com.inia_mscc.modulos.gem.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;

@Entity(name = "Cultivo")
@Table(name = "tl_gem_cult_cultivo")
public class Cultivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cult_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name = "cult_str_descripcion", nullable = true, columnDefinition = "VARCHAR(220)")
	private String _descripcion;
	@Enumerated(EnumType.STRING)
	@Column(name = "cult_num_id_estado", nullable = false, columnDefinition = "VARCHAR(10)")
	private Estado _estado;
	@Column(name = "cult_str_nombre", nullable = true, columnDefinition = "VARCHAR(50)")
	private String _nombre;
	@OneToMany(targetEntity = Propiedad.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "pruc_num_id_cultivo", referencedColumnName = "cult_num_id")
	private List<Propiedad> _listaPropiedades;

	public Cultivo() {
		super();
		_descripcion = null;
		_listaPropiedades = null;
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

	public String get_descripcion() {
		return _descripcion;
	}

	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}

	public Estado get_estado() {
		return _estado;
	}

	public void set_estado(Estado estado) {
		_estado = estado;
	}

	public List<Propiedad> get_listaPropiedades() {
		return _listaPropiedades;
	}

	public void set_listaPropiedades(List<Propiedad> listaPropiedades) {
		_listaPropiedades = listaPropiedades;
	}

}