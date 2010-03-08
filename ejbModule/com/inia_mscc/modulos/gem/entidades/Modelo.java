package com.inia_mscc.modulos.gem.entidades;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.seg.entidades.Usuario;

@Entity(name = "Modelo")
@Table(name = "tl_gem_mscc_modelo")
public class Modelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mscc_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;

	@Column(name = "mscc_dte_fecha", nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaHora;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Usuario.class)
	@ForeignKey(name = "FK_mscc_num_id_usuario")
	@JoinColumn(name = "mscc_num_id_usuario", referencedColumnName = "usua_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Usuario _usuarioInvestigador;

	@Enumerated(EnumType.STRING)
	@Column(name = "mscc_str_estado", nullable = false, columnDefinition = "VARCHAR(45)")
	private Estado _estado;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Escenario.class)
	@ForeignKey(name = "FK_mscc_num_id_escenario")
	@JoinColumn(name = "mscc_num_id_escenario", referencedColumnName = "esce_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Escenario _escenario;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Archivo.class)
	@ForeignKey(name = "FK_mscc_num_id_archivo_mscc")
	@JoinColumn(name = "mscc_num_id_archivo_mscc", referencedColumnName = "arch_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Archivo _archivoMSCC;

	public Modelo() {
		super();
	}

	public Modelo(Date fechaHora, Usuario usuarioInvestigador, Estado estado,
			Escenario escenario, Archivo archivoMSCC) {
		super();
		_fechaHora = new Date();
		_usuarioInvestigador = usuarioInvestigador;
		_estado = estado;
		_escenario = escenario;
		_archivoMSCC = archivoMSCC;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long id) {
		_id = id;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Usuario get_usuarioInvestigador() {
		return _usuarioInvestigador;
	}

	public void set_usuarioInvestigador(Usuario usuarioInvestigador) {
		_usuarioInvestigador = usuarioInvestigador;
	}

	public Estado get_estado() {
		return _estado;
	}

	public void set_estado(Estado estado) {
		_estado = estado;
	}

	public Escenario get_escenario() {
		return _escenario;
	}

	public void set_escenario(Escenario escenario) {
		_escenario = escenario;
	}

	public Archivo get_archivoMSCC() {
		return _archivoMSCC;
	}

	public void set_archivoMSCC(Archivo archivoMSCC) {
		_archivoMSCC = archivoMSCC;
	}

}
