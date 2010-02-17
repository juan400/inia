package com.inia_mscc.modulos.gem.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.servicios.ServicioArchivo;
import com.inia_mscc.modulos.gem.servicios.ServicioCultivo;

public class ProveedorArchivo implements ServicioArchivo {

	private ServicioArchivo ejbArchivo;

	public ProveedorArchivo() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbArchivo = (ProveedorArchivo) ctx.lookup("EJBArchivo");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void ActualizarArchivo(Archivo pArchivo) {
		ejbArchivo.ActualizarArchivo(pArchivo);
	}

	@Override
	public Archivo ObtenerArchivo(Archivo pArchivo) {
		return ejbArchivo.ObtenerArchivo(pArchivo);
	}

	@Override
	public List<Archivo> ObtenerArchivos(Archivo pArchivo) {
		return ejbArchivo.ObtenerArchivos(pArchivo);
	}

	@Override
	public Archivo RegistrarArchivo(Archivo pArchivo) {
		return ejbArchivo.RegistrarArchivo(pArchivo);
	}

}
