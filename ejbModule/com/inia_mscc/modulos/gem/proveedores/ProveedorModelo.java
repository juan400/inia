package com.inia_mscc.modulos.gem.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.gem.servicios.ServicioModelo;

public class ProveedorModelo implements ServicioModelo {

	private ServicioModelo ejbModelo;

	public ProveedorModelo() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbModelo = (ServicioModelo) ctx.lookup("EJBModelo");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void ActualizarModelo(Modelo pModelo) throws Exception {
		ejbModelo.ActualizarModelo(pModelo);
	}

	@Override
	public Modelo ObtenerModelo(Modelo pModelo) {
		return ejbModelo.ObtenerModelo(pModelo);
	}

	@Override
	public List<Modelo> ObtenerModelos(Modelo pModelo) {
		return ejbModelo.ObtenerModelos(pModelo);
	}

	@Override
	public Modelo RegistrarModelo(Modelo pModelo) throws Exception {
		return ejbModelo.RegistrarModelo(pModelo);
	}

}
