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
	public List<ValorSeleccion> ObtenerValores(ValorSeleccion pValor){
		return ejbValorSeleccion.ObtenerValores(pValor);
	}

	@Override
	public ValorSeleccion ObtenerValor(ValorSeleccion pValor){
		return ejbValorSeleccion.ObtenerValor(pValor);
	}
	
	@Override
	public void ActualizarValor(ValorSeleccion pValor){
		ejbValorSeleccion.ActualizarValor(pValor);
	}
	
	@Override
	public void EliminarValor(ValorSeleccion pValor){
		ejbValorSeleccion.EliminarValor(pValor);
	}
	
	@Override
	public void EliminarValores(List<ValorSeleccion> pValores) {
		ejbValorSeleccion.EliminarValores(pValores);
	}
}
