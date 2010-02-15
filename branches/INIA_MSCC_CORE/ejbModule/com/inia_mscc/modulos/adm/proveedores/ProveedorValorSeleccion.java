package com.inia_mscc.modulos.adm.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioValorSeleccion;

public class ProveedorValorSeleccion implements ServicioValorSeleccion {

	private ServicioValorSeleccion ejbValorSeleccion;

	public ProveedorValorSeleccion() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbValorSeleccion = (ServicioValorSeleccion) ctx.lookup("EJBValorSeleccion");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public ValorSeleccion RegistrarValorSeleccion(ValorSeleccion pValor) {
		return ejbValorSeleccion.RegistrarValorSeleccion(pValor);
	}

	@Override
	public void ActualizarValorSeleccion(ValorSeleccion pValor) {
		ejbValorSeleccion.ActualizarValorSeleccion(pValor);
	}

	@Override
	public List<ValorSeleccion> ObtenerValores() {
		return ejbValorSeleccion.ObtenerValores();
	}
	
	@Override
	public ValorSeleccion ComprobarValorSeleccion(ValorSeleccion pValor) {
		return ejbValorSeleccion.ComprobarValorSeleccion(pValor);
	}
}
