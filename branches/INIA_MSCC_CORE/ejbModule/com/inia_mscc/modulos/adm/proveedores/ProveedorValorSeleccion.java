package com.inia_mscc.modulos.adm.proveedores;

import java.io.IOException;

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
	public void ActualizarValorSeleccion(ValorSeleccion pValor) {
		ejbValorSeleccion.ActualizarValorSeleccion(pValor);
	}
	
	@Override
	public  ValorSeleccion ObtenerValorSeleccion(ValorSeleccion pValor){
		return ejbValorSeleccion.ObtenerValorSeleccion(pValor);
	}
}
