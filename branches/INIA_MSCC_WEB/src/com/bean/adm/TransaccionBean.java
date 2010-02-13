package com.bean.adm;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;

public class TransaccionBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descripcion;
	private String estado;
	private Transaccion trans = new Transaccion();
	private List<Transaccion> transaccion;
	private List<Transaccion> transaccionesActivas;

	public boolean isInit() {
		this.setTransaccionesActivas(this.getAdmFachada(Servicio.Transaccion)
				.ObtenerTransaccionesActiva());
		this.setTransaccion(this.getAdmFachada(Servicio.Transaccion)
				.ObtenerTransacciones());
		return false;
	}

	public TransaccionBean() {
		this.estado = Estado.Activo.name();
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {
			trans.set_codigo(codigo);
			trans.set_descripcion(descripcion);
			trans.set_estado(Enumerados.Estado.valueOf(estado));

			Transaccion tran = this.getAdmFachada(Servicio.Transaccion)
					.ComprobarTransaccion(trans);
			if (tran == null) {
				setError("");
				this.getAdmFachada(Servicio.Transaccion).ActualizarTransaccion(
						trans);
				retorno = "registro-ok";
			} else {
				this
						.setError("Ya existe una Transacción con igual código, Por favor ingrese otro código.");
				retorno = "";
			}
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String verTransaccion() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String transaccionElegida = (String) paramMap.get("transaccionElegida");
		Iterator<Transaccion> it = transaccion.iterator();
		while (it.hasNext()) {
			Transaccion transaccionSeleccionada = (Transaccion) it.next();
			if (transaccionSeleccionada.get_id() == (long) Long
					.parseLong(transaccionElegida)) {
				trans = transaccionSeleccionada;

				codigo = trans.get_codigo();
				descripcion = trans.get_descripcion();
				estado = trans.get_estado().toString();
			}
		}
		return "resultados";
	}

	public void setTransaccionesActivas(List<Transaccion> transaccionesActivas) {
		this.transaccionesActivas = transaccionesActivas;
	}

	public List<Transaccion> getTransaccionesActivas() {
		return transaccionesActivas;
	}

	public void setTransaccion(List<Transaccion> transaccion) {
		this.transaccion = transaccion;
	}

	public List<Transaccion> getTransaccion() {
		return transaccion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

}
