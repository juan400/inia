package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;

public interface ServicioValorSeleccion {

	public List<ValorSeleccion> ObtenerValores(ValorSeleccion pValor);
	public ValorSeleccion ObtenerValor(ValorSeleccion pValor);
	public void ActualizarValor(ValorSeleccion pValor);
	public void EliminarValor(ValorSeleccion pValor);
	public void EliminarValores(List<ValorSeleccion> pValores);
	
}
