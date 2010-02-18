package com.inia_mscc.modulos.gem.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;
import com.inia_mscc.modulos.gem.servicios.ServicioUbicacion;

public class ProveedorUbicacion implements ServicioUbicacion {
	
	private ServicioUbicacion ejbUbicacion;

	public ProveedorUbicacion() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbUbicacion = (ServicioUbicacion) ctx.lookup("EJBUbicacion");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void ActualizarUbicacion(Ubicacion pUbicacion) {
		ejbUbicacion.ActualizarUbicacion(pUbicacion);
	}

	@Override
	public Ubicacion ObtenerUbicacion(Ubicacion pUbicacion) {
		return ejbUbicacion.ObtenerUbicacion(pUbicacion);
	}

	@Override
	public List<Ubicacion> ObtenerUbicacions(Ubicacion pUbicacion) {
		return ejbUbicacion.ObtenerUbicacions(pUbicacion);
	}

	@Override
	public Ubicacion RegistrarUbicacion(Ubicacion pUbicacion) {
		return ejbUbicacion.RegistrarUbicacion(pUbicacion);
	}
	
}