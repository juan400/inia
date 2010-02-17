package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity(name = "ValorSeleccion")
@Table(name = "tl_adm_vase_valorseleccion")
@ForeignKey(name = "FK_vase_num_id_listacriterio")
public class ValorSeleccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vase_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;

	@Column(name = "vase_str_codigo", updatable = true, nullable = false, columnDefinition = "VARCHAR(10)")
	private String _codigo;

	@Column(name = "vase_str_descripcion", nullable = true, columnDefinition = "VARCHAR(220)")
	private String _descripcion;

	@Column(name = "vase_str_unidad", nullable = true, columnDefinition = "VARCHAR(45)")
	private String _unidadMedida;

	@ManyToOne(targetEntity = ListaCriterioSeleccion.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "vase_num_id_listacriterio", referencedColumnName = "licr_num_id")
	private ListaCriterioSeleccion _criterio;

	public ValorSeleccion() {
		super();
		_id = 0;
		_codigo = null;
		_descripcion = null;
		_unidadMedida = null;
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

	public String get_unidadMedida() {
		return _unidadMedida;
	}

	public void set_unidadMedida(String unidadMedida) {
		_unidadMedida = unidadMedida;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public ListaCriterioSeleccion get_criterio() {
		return _criterio;
	}

	public void set_criterio(ListaCriterioSeleccion criterio) {
		_criterio = criterio;
	}

}
