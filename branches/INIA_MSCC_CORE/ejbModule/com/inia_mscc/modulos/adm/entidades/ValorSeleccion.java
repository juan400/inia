package com.inia_mscc.modulos.adm.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.comun.entidades.Objeto;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;

@Entity(name="ValorSeleccion")
@Table(name="tl_adm_vase_valorseleccion")
public class ValorSeleccion extends Objeto {
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

	@OneToOne(cascade = CascadeType.ALL, targetEntity=Pais.class)
	@ForeignKey (name="FK_vase_num_id_listacriterio")
	@JoinColumn(name="vase_num_id_listacriterio", nullable=true, columnDefinition="BIGINT(20)")
	private ListaCriterioSeleccion _lista;
	
	public ValorSeleccion() {
		super();
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

	public ListaCriterioSeleccion get_lista() {
		return _lista;
	}

	public void set_lista(ListaCriterioSeleccion lista) {
		_lista = lista;
	}
	
}
