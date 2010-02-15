package com.inia_mscc.modulos.adm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;

@Entity(name="ValorSeleccion")
@Table(name="tl_adm_vase_valorseleccion")
public class ValorSeleccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vase_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "licr_num_id_estado", nullable = false, columnDefinition = "VARCHAR(10)")
	private Estado _estado;
	
	@Column(name = "vase_str_codigo", nullable = false, columnDefinition = "VARCHAR(6)")
	private String _codigo;
	
	@Column(name = "vase_str_descripcion", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _descripcion;
	
	@Column(name = "vase_str_unidad", nullable = false, columnDefinition = "VARCHAR(46)")
	private String _unidadMedida;

	
	public ValorSeleccion() {
		super();
		_id = 0;
		_estado = Estado.Activo;
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

	public Estado get_estado() {
		return _estado; 
	}

	public void set_estado(Estado estado) {
		_estado = estado;
	}

}
