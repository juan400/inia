package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.Transaccion;

public interface ServicioTransaccion {
	
	public List<Transaccion> ObtenerTransacciones();
	public void ActualizarTransaccion(Transaccion pTransaccion);
	public List<Transaccion> ObtenerTransaccionesActiva();
	public Transaccion ComprobarTransaccion(Transaccion pTransaccion);
		
}
