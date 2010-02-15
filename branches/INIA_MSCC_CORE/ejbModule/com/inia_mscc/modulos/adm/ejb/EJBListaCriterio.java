package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import com.inia_mscc.modulos.adm.dao.DAOListaCriterio;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioListaCriterio;

public class EJBListaCriterio implements ServicioListaCriterio {

	private DAOListaCriterio dao = new DAOListaCriterio();

	@Override
	public void ActualizarCriterio(ListaCriterioSeleccion pCriterio) {
		dao.ActualizarCriterio(pCriterio);
	}

	@Override
	public List<ListaCriterioSeleccion> ObtenerCriterios() {
		return dao.ObtenerCriterios();
	}

	public ListaCriterioSeleccion ObtenerCriterioConValores(
			ListaCriterioSeleccion pCriterio) {
		return dao.ObtenerCriterioConValores(pCriterio);
	}
}
