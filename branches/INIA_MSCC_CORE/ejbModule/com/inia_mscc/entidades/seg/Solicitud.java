package com.inia_mscc.entidades.seg;

import java.util.Date;

import com.inia_mscc.entidades.comun.Enumerados;
import com.inia_mscc.entidades.comun.Objeto;
import com.inia_mscc.entidades.comun.ValorSeleccion;
import com.inia_mscc.entidades.comun.Enumerados.EstadoSolicitud;
import com.inia_mscc.entidades.comun.Enumerados.TipoSolicitud;

public class Solicitud extends Objeto {
	private String _codigo;
	private String _detalleMotivo;
	private Date _fechaRegistro;
	private Date _fechaEstado;
	private String _detalleDenegacion;
	private Enumerados.TipoSolicitud _tipoSolicitud;
	private Usuario _usuarioSolicitante;
	private Usuario _usuarioAdministrador;
	private Enumerados.EstadoSolicitud _estadoSolicitud; 
	private ValorSeleccion _tipoPerfilSolicitado;
	private Transaccion _listaTransaccionesSolicitadas;
	
	
	public Solicitud() {
		super();
		_codigo = null;
		_detalleMotivo = null;
		_fechaRegistro = new Date();
		_fechaEstado = new Date();
		_detalleDenegacion = null;
		_tipoSolicitud = Enumerados.TipoSolicitud.Ninguno;
		_usuarioSolicitante = null;
		_usuarioAdministrador = null;
		_estadoSolicitud = Enumerados.EstadoSolicitud.Registrada;
		_tipoPerfilSolicitado = null;
		_listaTransaccionesSolicitadas = null;
	}
	public String get_codigo() {
		return _codigo;
	}
	public void set_codigo(String codigo) {
		_codigo = codigo;
	}
	public String get_detalleMotivo() {
		return _detalleMotivo;
	}
	public void set_detalleMotivo(String detalleMotivo) {
		_detalleMotivo = detalleMotivo;
	}
	public Date get_fechaRegistro() {
		return _fechaRegistro;
	}
	public void set_fechaRegistro(Date fechaRegistro) {
		_fechaRegistro = fechaRegistro;
	}
	public Date get_fechaEstado() {
		return _fechaEstado;
	}
	public void set_fechaEstado(Date fechaEstado) {
		_fechaEstado = fechaEstado;
	}
	public String get_detalleDenegacion() {
		return _detalleDenegacion;
	}
	public void set_detalleDenegacion(String detalleDenegacion) {
		_detalleDenegacion = detalleDenegacion;
	}
	public Enumerados.TipoSolicitud get_tipoSolicitud() {
		return _tipoSolicitud;
	}
	public void set_tipoSolicitud(Enumerados.TipoSolicitud tipoSolicitud) {
		_tipoSolicitud = tipoSolicitud;
	}
	public Usuario get_usuarioSolicitante() {
		return _usuarioSolicitante;
	}
	public void set_usuarioSolicitante(Usuario usuarioSolicitante) {
		_usuarioSolicitante = usuarioSolicitante;
	}
	public Usuario get_usuarioAdministrador() {
		return _usuarioAdministrador;
	}
	public void set_usuarioAdministrador(Usuario usuarioAdministrador) {
		_usuarioAdministrador = usuarioAdministrador;
	}
	public Enumerados.EstadoSolicitud get_estadoSolicitud() {
		return _estadoSolicitud;
	}
	public void set_estadoSolicitud(Enumerados.EstadoSolicitud estadoSolicitud) {
		_estadoSolicitud = estadoSolicitud;
	}
	public ValorSeleccion get_tipoPerfilSolicitado() {
		return _tipoPerfilSolicitado;
	}
	public void set_tipoPerfilSolicitado(ValorSeleccion tipoPerfilSolicitado) {
		_tipoPerfilSolicitado = tipoPerfilSolicitado;
	}
	public Transaccion get_listaTransaccionesSolicitadas() {
		return _listaTransaccionesSolicitadas;
	}
	public void set_listaTransaccionesSolicitadas(
			Transaccion listaTransaccionesSolicitadas) {
		_listaTransaccionesSolicitadas = listaTransaccionesSolicitadas;
	}

}
