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
import javax.persistence.Transient;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.gem.entidades.Archivo;

@Entity(name="ResultadoMSCC")
@Table(name="tl_eje_resu_resultadoO")
public class ResultadoMSCC implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "resu_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long id;

	@Column(name = "resu_dte_fecha",  nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaHora;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Archivo.class)
	@ForeignKey(name = "FK_ejec_num_id_archivo_eje")
	@JoinColumn(name = "ejec_num_id_archivo_eje", referencedColumnName = "arch_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Archivo _archivo;

	@Transient
	private HashedMap _matrizDatos;
	
	public ResultadoMSCC() {
		super();
		_fechaHora = new Date();
		_archivo = null;
		_matrizDatos = new HashedMap();
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fecha) {
		_fechaHora = fecha;
	}

	public Archivo get_archivo() {
		return _archivo;
	}

	public void set_archivo(Archivo archivo) {
		_archivo = archivo;
	}
	
	public HashedMap get_matrizDatos() throws Exception {
		return _matrizDatos;
	}

	public void set_matrizDatos(HashedMap matrizDatos) {
		_matrizDatos = matrizDatos;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
