package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;

public interface ServicioListaCriterio {

	public void ActualizarCriterio(ListaCriterioSeleccion pCriterio);
	public List<ListaCriterioSeleccion> ObtenerCriterios();
	public ListaCriterioSeleccion ObtenerCriterioConValores(ListaCriterioSeleccion pCriterio);
}
