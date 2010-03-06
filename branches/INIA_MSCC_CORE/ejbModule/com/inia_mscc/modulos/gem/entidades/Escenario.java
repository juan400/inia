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

import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.seg.entidades.Usuario;

@Entity(name = "Escenario")
@Table(name = "tl_eje_esce_escenacio")
public class Escenario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "esce_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long id;

	@Column(name = "esce_dte_fecha",  nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaHora;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Usuario.class)
	@ForeignKey(name = "FK_esce_num_id_usuario")
	@JoinColumn(name = "esce_num_id_usuario", referencedColumnName = "usua_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Usuario _usuarioInvestigador;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Region.class)
	@ForeignKey(name = "FK_arch_num_id_cultivo")
	@JoinColumn(name = "esce_num_id_region_climatica", referencedColumnName = "regi_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Region _region;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Cultivo.class)
	@ForeignKey(name = "FK_esce_num_id_cultivo")
	@JoinColumn(name = "esce_num_id_cultivo", referencedColumnName = "cult_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Cultivo _cultivo;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Archivo.class)
	@ForeignKey(name = "FK_esce_num_id_archivo_escenario")
	@JoinColumn(name = "esce_num_id_archivo_escenario", referencedColumnName = "arch_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Archivo _archivoEscenario;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Archivo.class)
	@ForeignKey(name = "FK_arch_num_id_cultivo")
	@JoinColumn(name = "esce_num_id_archivo_mscc", referencedColumnName = "arch_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Archivo _archivoMSCC;
	
	public Escenario() {
		super();
		_fechaHora = new Date();
		_usuarioInvestigador = null;
		_region = null;
		_cultivo = null;
		_archivoEscenario = null;
		_archivoMSCC = null;
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

	public Region get_region() {
		return _region;
	}

	public void set_region(Region region) {
		_region = region;
	}

	public Cultivo get_cultivo() {
		return _cultivo;
	}

	public void set_cultivo(Cultivo cultivo) {
		_cultivo = cultivo;
	}

	public Archivo get_archivoEscenario() {
		return _archivoEscenario;
	}

	public void set_archivoEscenario(Archivo archivoEscenario) {
		_archivoEscenario = archivoEscenario;
	}

	public Archivo get_archivoMSCC() {
		return _archivoMSCC;
	}

	public void set_archivoMSCC(Archivo archivoMSCC) {
		_archivoMSCC = archivoMSCC;
	}
	
}
