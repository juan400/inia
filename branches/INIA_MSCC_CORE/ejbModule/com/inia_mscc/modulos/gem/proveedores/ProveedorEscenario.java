package com.inia_mscc.modulos.gem.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.servicios.ServicioEscenario;

public class ProveedorEscenario implements ServicioEscenario {

	private ServicioEscenario ejbEscenario;

	public ProveedorEscenario() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbEscenario = (ServicioEscenario) ctx.lookup("EJBEscenario");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void ActualizarEscenario(Escenario pEscenario) throws Exception {
		ejbEscenario.ActualizarEscenario(pEscenario);
	}

	@Override
	public Escenario ObtenerEscenario(Escenario pEscenario) {
		return ejbEscenario.ObtenerEscenario(pEscenario);
	}

	@Override
	public List<Escenario> ObtenerEscenarios(Escenario pEscenario) {
		return ejbEscenario.ObtenerEscenarios(pEscenario);
	}

	@Override
	public Escenario RegistrarEscenario(Escenario pEscenario) throws Exception {
		return ejbEscenario.RegistrarEscenario(pEscenario);
	}

}
