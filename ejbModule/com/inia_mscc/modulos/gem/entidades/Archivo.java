package com.inia_mscc.modulos.gem.entidades;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Basic;
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
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoExtencionArchivo;
import com.inia_mscc.modulos.seg.entidades.Usuario;

@Entity(name = "Archivo")
@Table(name = "tl_gem_arch_archivo")
public class Archivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "arch_num_id", updatable = false, nullable = false, columnDefinition = "BIGINT(20)")
	private long _id;

	@Column(name = "arch_str_nombre", nullable = true, columnDefinition = "VARCHAR(220)")
	private String _nombre;
	
	@Column(name = "arch_dte_fecha", nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date _fechaHora;

	@Enumerated(EnumType.STRING)
	@Column(name = "arch_str_estado", nullable = false, columnDefinition = "VARCHAR(45)")
	private Estado _estado;

	@Enumerated(EnumType.STRING)
	@Column(name = "arch_str_tipo", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.TipoArchivo _tipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "arch_str_extencion", nullable = false, columnDefinition = "VARCHAR(45)")
	private Enumerados.TipoExtencionArchivo _extencion;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Ubicacion.class)
	@ForeignKey(name = "FK_arch_num_id_ubicacion_archivo")
	@JoinColumn(name = "arch_num_id_ubicacion_archivo", referencedColumnName = "ubar_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Ubicacion _ubicacion;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Cultivo.class)
	@ForeignKey(name = "FK_arch_num_id_cultivo")
	@JoinColumn(name = "arch_num_id_cultivo", referencedColumnName = "cult_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Cultivo _cultivo;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = Usuario.class)
	@ForeignKey(name = "FK_arch_num_id_usuario")
	@JoinColumn(name = "arch_num_id_usuario", referencedColumnName = "usua_num_id", updatable = true, nullable = false, columnDefinition = "BIGINT(20)")
	private Usuario _usuario;
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
	@Column(name = "arch_dat_dato", nullable = false, columnDefinition = "blob NOT NULL")
	private File _datos;

	public Archivo() {
		super();
		_nombre = null;
		_fechaHora = new Date();
		_estado = Estado.Activo;
	}

	public Archivo(String pNombreLogin, TipoArchivo tipo, Date fechaHora,
			Estado estadoArchivo, TipoExtencionArchivo extencion,
			Ubicacion ubicacion) {
		super();
		_tipo = tipo;
		_estado = estadoArchivo;
		_fechaHora = fechaHora;
		_extencion = extencion;
		_ubicacion = ubicacion;
		_nombre = pNombreLogin;
		set_nombre(pNombreLogin);
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String pNombreLogin) {
		Calendar pFecha = new GregorianCalendar();
		if (_fechaHora != null) {
			pFecha.setTime(_fechaHora);
		}
		String fechaEscrita = pFecha.get(Calendar.YEAR) + "-"
				+ (pFecha.get(Calendar.MONTH) + 1) + "-"
				+ pFecha.get(Calendar.DAY_OF_MONTH) + "_"
				+ pFecha.get(Calendar.HOUR_OF_DAY) + ""
				+ pFecha.get(Calendar.MINUTE) + ""
				+ pFecha.get(Calendar.SECOND) + ""
				+ pFecha.get(Calendar.MILLISECOND);
		String nombreArchivo = _tipo + "_" + pNombreLogin + "_" + fechaEscrita
				+ ".py";
		_nombre = nombreArchivo;
	}

	public Enumerados.TipoArchivo get_tipo() {
		return _tipo;
	}

	public void set_tipo(Enumerados.TipoArchivo tipo) {
		_tipo = tipo;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Enumerados.TipoExtencionArchivo get_extencion() {
		return _extencion;
	}

	public void set_extencion(Enumerados.TipoExtencionArchivo extencion) {
		_extencion = extencion;
	}

	public Ubicacion get_ubicacion() {
		return _ubicacion;
	}

	public void set_ubicacion(Ubicacion ubicacion) {
		_ubicacion = ubicacion;
	}

	public File get_datos() {
		return _datos;
	}

	public void set_datos(File datos) {
		_datos = datos;
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

	public Cultivo get_cultivo() {
		return _cultivo;
	}

	public void set_cultivo(Cultivo cultivo) {
		_cultivo = cultivo;
	}

	public Usuario get_usuario() {
		return _usuario;
	}

	public void set_usuario(Usuario usuario) {
		_usuario = usuario;
	}

}