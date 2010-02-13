package com.inia_mscc.modulos.eje.proveedores;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;
import com.inia_mscc.modulos.seg.servicios.ServicioPerfil;

public class ProveedorEjecucion implements ServicioEjecucionMSCC {

	private ServicioEjecucionMSCC ejbEjeccucion;

	public ProveedorEjecucion() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbEjeccucion = (ServicioEjecucionMSCC) ctx.lookup("EJBEjecucionMSCC");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void generarArchivoEscenario(EjecucionMSCC ejecucionMSCC) throws Exception {
		ejbEjeccucion.generarArchivoEscenario(ejecucionMSCC);
	}
	
}
