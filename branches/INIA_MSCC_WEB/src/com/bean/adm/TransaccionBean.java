package com.bean.adm;

import java.io.Serializable;
import java.util.List;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;

public class TransaccionBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Transaccion> transaccion;
	private List<Transaccion> transaccionesActivas;

	public boolean isInit() {
		this.setTransaccionesActivas(this.getAdmFachada(Servicio.Transaccion)
				.ObtenerTransaccionesActiva());
		this.setTransaccion(this.getAdmFachada(Servicio.Transaccion)
				.ObtenerTransacciones());
		return false;
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

}
