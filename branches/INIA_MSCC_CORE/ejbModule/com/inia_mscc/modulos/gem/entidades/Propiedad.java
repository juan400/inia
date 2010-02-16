package com.inia_mscc.modulos.gem.entidades;

import java.io.Serializable;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.comun.entidades.Enumerados;

@Entity(name = "Propiedad")
@Table(name = "tl_gem_prcu_propiedadescultivo")
@ForeignKey(name = "FK_prcu_num_id_cultivo")
public class Propiedad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prcu_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Column(name = "prcu_str_codigo", nullable = false, columnDefinition = "VARCHAR(10)")
	private String _codigo;
	@Column(name = "prcu_str_nombre", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _nombre;
	@Column(name = "prcu_str_unidad_medida", nullable = true, columnDefinition = "VARCHAR(220)")
	private String _unidadMedida;
	@Enumerated(EnumType.STRING)
	@Column(name = "prcu_str_tipo", nullable = true, columnDefinition = "VARCHAR(45)")
	private Enumerados.TipoPropiedadCultivo _tipo;
	@Transient
	private String _valor;
	@ManyToOne(targetEntity = Cultivo.class, cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "prcu_num_id_cultivo", referencedColumnName = "cult_num_id")
	private Cultivo _cultivo;
	@Transient
	private boolean _grabada;

	public Propiedad() {
		super();
		_codigo = null;
		_nombre = null;
		_tipo = Enumerados.TipoPropiedadCultivo.Ninguno;
		_grabada = true;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public String get_codigo() {
		return _codigo;
	}

	public void set_codigo(String codigo) {
		_codigo = codigo;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String nombre) {
		_nombre = nombre;
	}

	public String get_unidadMedida() {
		return _unidadMedida;
	}

	public void set_unidadMedida(String unidadMedida) {
		_unidadMedida = unidadMedida;
	}

	public Enumerados.TipoPropiedadCultivo get_tipo() {
		return _tipo;
	}

	public void set_tipo(Enumerados.TipoPropiedadCultivo tipo) {
		_tipo = tipo;
	}

	public String get_valor() {
		return _valor;
	}

	public void set_valor(String valor) {
		_valor = valor;
	}

	public Cultivo get_cultivo() {
		return _cultivo;
	}

	public void set_cultivo(Cultivo cultivo) {
		_cultivo = cultivo;
	}

	public boolean is_grabada() {
		return _grabada;
	}

	public void set_grabada(boolean grabada) {
		_grabada = grabada;
	}
}