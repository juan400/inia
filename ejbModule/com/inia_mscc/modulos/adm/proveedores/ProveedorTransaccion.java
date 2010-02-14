package com.inia_mscc.modulos.adm.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.adm.servicios.ServicioTransaccion;

public class ProveedorTransaccion implements ServicioTransaccion {

	private ServicioTransaccion ejbTransaccion;

	public ProveedorTransaccion() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbTransaccion = (ServicioTransaccion) ctx.lookup("EJBTransaccion");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public List<Transaccion> ObtenerTransacciones() {
		return ejbTransaccion.ObtenerTransacciones();
	}

	@Override
	public void ActualizarTransaccion(Transaccion pTransaccion) {
		ejbTransaccion.ActualizarTransaccion(pTransaccion);
	}

	@Override
	public List<Transaccion> ObtenerTransaccionesActiva() {
		return ejbTransaccion.ObtenerTransaccionesActiva();
	}
	
	@Override
	public Transaccion ComprobarTransaccion(Transaccion pTransaccion) {
		return ejbTransaccion.ComprobarTransaccion(pTransaccion);
	}
	
}
