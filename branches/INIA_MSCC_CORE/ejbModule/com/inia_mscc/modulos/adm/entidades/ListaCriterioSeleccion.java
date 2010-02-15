/**
 * 
 */
package com.inia_mscc.modulos.adm.entidades;

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

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.gem.entidades.Propiedad;

@Entity(name = "ListaCriterioSeleccion")
@Table(name = "tl_adm_licr_listascriterio")
public class ListaCriterioSeleccion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "licr_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Enumerated(EnumType.STRING)
	@Column(name = "licr_str_estado", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.Estado _estado;
	@Column(name = "licr_str_descripcion", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _descripcion;
	@Column(name = "licr_str_codigo", nullable = false, columnDefinition = "VARCHAR(3)")
	private String _codigo;

	@OneToMany(targetEntity = Propiedad.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "prcu_num_id_cultivo", referencedColumnName = "cult_num_id")
	private List<ValorSeleccion> _listaValores;

	public ListaCriterioSeleccion() {
		super();
		_estado = Estado.Activo;
		_listaValores = null;
	}

	public String get_codigo() {
		return _codigo;
	}

	public void set_codigo(String codigo) {
		_codigo = codigo;
	}

	public List<ValorSeleccion> get_listaValores() {
		return _listaValores;
	}

	public void set_listaValores(List<ValorSeleccion> listaValores) {
		_listaValores = listaValores;
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

	public String get_descripcion() {
		return _descripcion;
	}

	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}

}
