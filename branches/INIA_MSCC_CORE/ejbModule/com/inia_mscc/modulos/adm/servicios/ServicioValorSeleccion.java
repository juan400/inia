package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;

public interface ServicioValorSeleccion {

	public ValorSeleccion RegistrarValorSeleccion(ValorSeleccion pValor);
	public void  ActualizarValorSeleccion(ValorSeleccion pValor);
	public ValorSeleccion ComprobarValorSeleccion(ValorSeleccion pValor);
	public List<ValorSeleccion> ObtenerValores();
	
}
