package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;

public interface ServicioListaCriterio {

	public ListaCriterioSeleccion ObtenerCriterio(
			ListaCriterioSeleccion pCriterio);

	public List<ListaCriterioSeleccion> ObtenerListaCriterio();

	public ListaCriterioSeleccion RegistrarListaCriterio(
			ListaCriterioSeleccion pCriterio);

	public void ActualizarListaCriterio(ListaCriterioSeleccion pCriterio);
}
