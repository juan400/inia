package com.inia_mscc.modulos.eje.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.seg.entidades.Usuario;

@Entity(name = "EjecucionMSCC")
@Table(name = "tl_eje_ejec_ejecucion")
public class EjecucionMSCC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ejec_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long id;

	@Column(name = "ejec_dte_fecha", nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaHora;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Usuario.class)
	@ForeignKey(name = "FK_ejec_num_id_usua_eje")
	@JoinColumn(name = "ejec_num_id_usua_eje", referencedColumnName = "usua_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Usuario _usuarioEjecutante;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Archivo.class)
	@ForeignKey(name = "FK_ejec_num_id_archivo_eje")
	@JoinColumn(name = "ejec_num_id_archivo_eje", referencedColumnName = "arch_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Archivo _archivoEjecucion;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Modelo.class)
	@ForeignKey(name = "FK_ejec_num_id_mscc")
	@JoinColumn(name = "ejec_num_id_mscc", referencedColumnName = "mscc_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Modelo _modelo;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = ResultadoMSCC.class)
	@ForeignKey(name = "FK_ejec_num_id_resultado")
	@JoinColumn(name = "ejec_num_id_resultado", referencedColumnName = "resu_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private ResultadoMSCC _resultadoMSCC;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Proyeccion.class)
	@ForeignKey(name = "FK_ejec_num_id_proyeccion")
	@JoinColumn(name = "ejec_num_id_proyeccion", referencedColumnName = "proy_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Proyeccion _proyeccion;

	public EjecucionMSCC() {
		super();
		_fechaHora = new Date();
		_usuarioEjecutante = null;
		_archivoEjecucion = null;
		_resultadoMSCC = null;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Usuario get_usuarioEjecutante() {
		return _usuarioEjecutante;
	}

	public void set_usuarioEjecutante(Usuario usuarioEjecutante) {
		_usuarioEjecutante = usuarioEjecutante;
	}

	public Archivo get_archivoEjecucion() {
		return _archivoEjecucion;
	}

	public void set_archivoEjecucion(Archivo archivoEjecucion) {
		_archivoEjecucion = archivoEjecucion;
	}

	public void set_modelo(Modelo _modelo) {
		this._modelo = _modelo;
	}

	public Modelo get_modelo() {
		return _modelo;
	}

	public ResultadoMSCC get_resultadoMSCC() {
		return _resultadoMSCC;
	}

	public void set_resultadoMSCC(ResultadoMSCC resultaroMSCC) {
		_resultadoMSCC = resultaroMSCC;
	}

	public void set_proyeccion(Proyeccion _proyeccion) {
		this._proyeccion = _proyeccion;
	}

	public Proyeccion get_proyeccion() {
		return _proyeccion;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
