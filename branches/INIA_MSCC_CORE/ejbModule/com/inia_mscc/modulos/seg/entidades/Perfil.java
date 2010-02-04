package com.inia_mscc.modulos.seg.entidades;

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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;

@Entity(name = "Perfil")
@Table(name = "tl_seg_perf_perfil")
public class Perfil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "perf_num_id", nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
	@Enumerated(EnumType.STRING)
	@Column(name = "perf_str_estado", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.Estado _estado;
	@Column(name = "perf_str_nombre", nullable = false, columnDefinition = "VARCHAR(45)")
	private String _nombre;
	@Column(name = "perf_str_descripcion", nullable = false, columnDefinition = "VARCHAR(220)")
	private String _descripcion;
	@Transient
	private ValorSeleccion _tipoPerfil;
	@Column(name = "perf_bol_fijo", updatable = false, nullable = false, columnDefinition = "TINYINT(1)")
	private boolean _fijo;

	@OneToMany(targetEntity = Transaccion.class, cascade = CascadeType.ALL)
	@JoinTable(name = "tl_seg_trpe_transaccionperfil", 
			uniqueConstraints={@UniqueConstraint(columnNames={"trpe_num_id_perfile", "trpe_num_id_transaccion"})},
			joinColumns = { @JoinColumn(name = "trpe_num_id_perfile",nullable=false, referencedColumnName = "perf_num_id", columnDefinition = "BIGINT(20)") }, 
			inverseJoinColumns = { @JoinColumn(name = "trpe_num_id_transaccion",nullable=false, referencedColumnName = "tran_num_id", columnDefinition = "BIGINT(20)") })
	private List<Transaccion> _transaccionesSistema;

	public Perfil() {
		_id = 0;
		_estado = Estado.Activo;
		_nombre = null;
		_descripcion = null;
		_tipoPerfil = null;
		_transaccionesSistema = null;
		_fijo = false;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public Enumerados.Estado get_estado() {
		return _estado;
	}

	public void set_estado(Enumerados.Estado estado) {
		_estado = estado;
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

	public ValorSeleccion get_tipoPerfil() {
		return _tipoPerfil;
	}

	public void set_tipoPerfil(ValorSeleccion tipoPerfil) {
		_tipoPerfil = tipoPerfil;
	}
	
	public List<Transaccion> get_transaccionesSistema() {
		return _transaccionesSistema;
	}

	public void set_transaccionesSistema(List<Transaccion> transaccionesSistema) {
		_transaccionesSistema = transaccionesSistema;
	}

	public void set_fijo(boolean _fijo) {
		this._fijo = _fijo;
	}

	public boolean is_fijo() {
		return _fijo;
	}

}
