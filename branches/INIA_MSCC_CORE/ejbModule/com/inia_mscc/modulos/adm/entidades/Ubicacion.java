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

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;

@Entity(name = "Ubicacion")
@Table(name = "tl_gem_ubar_ubicacionarchivo")
public class Ubicacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ubar_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;
 
	@Enumerated(EnumType.STRING)
	@Column(name = "ubar_str_tipo_archivo", nullable = false, columnDefinition = "VARCHAR(220)")
	private Enumerados.TipoArchivo _tipoArchivo;

	@Column(name = "ubar_str_path", nullable = false, columnDefinition = "TEXT")
	private String _urlPaht;

	public Ubicacion(long id, TipoArchivo tipoArchivo, String urlPaht) {
		super();
		_id = id;
		_tipoArchivo = tipoArchivo;
		_urlPaht = urlPaht;
	}
	
	public Ubicacion() {
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public Enumerados.TipoArchivo get_tipoArchivo() {
		return _tipoArchivo;
	}

	public void set_tipoArchivo(Enumerados.TipoArchivo tipoArchivo) {
		_tipoArchivo = tipoArchivo;
	}

	public String get_urlPaht() {
		return _urlPaht;
	}

	public void set_urlPaht(String urlPaht) {
		_urlPaht = urlPaht;
	}

}