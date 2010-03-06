package com.inia_mscc.modulos.eje.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.inia_mscc.modulos.gem.entidades.Archivo;

@Entity(name="Proyeccion")
@Table(name="tl_adc_proy_proyeccion")
public class Proyeccion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "proy_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long id;

	@Column(name = "proy_dte_fecha",  nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaHora;

	private int _periodoTiempo;
	private Archivo _archivoClimatologicoEntrada;
	private Archivo _archivoResultado;
	
	public Proyeccion(){
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date get_fechaHora() {
		return _fechaHora;
	}
	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}
	public int get_periodoTiempo() {
		return _periodoTiempo;
	}
	public void set_periodoTiempo(int periodoTiempo) {
		_periodoTiempo = periodoTiempo;
	}
	public Archivo get_archivoClimatologicoEntrada() {
		return _archivoClimatologicoEntrada;
	}
	public void set_archivoClimatologicoEntrada(Archivo archivoClimatologicoEntrada) {
		_archivoClimatologicoEntrada = archivoClimatologicoEntrada;
	}
	public Archivo get_archivoResultado() {
		return _archivoResultado;
	}
	public void set_archivoResultado(Archivo archivoResultado) {
		_archivoResultado = archivoResultado;
	}	
	
}
