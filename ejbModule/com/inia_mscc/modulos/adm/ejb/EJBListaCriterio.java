package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import com.inia_mscc.modulos.adm.dao.DAOListaCriterio;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioListaCriterio;

public class EJBListaCriterio implements ServicioListaCriterio {

	private DAOListaCriterio dao = new DAOListaCriterio();

	@Override
	public ListaCriterioSeleccion ObtenerCriterio(ListaCriterioSeleccion pCriterio){
		return dao.ObtenerCriterio(pCriterio);
	}

	public List<ListaCriterioSeleccion> ObtenerListaCriterio(ListaCriterioSeleccion pCriterio){
		return dao.ObtenerListaCriterio(pCriterio);
	}

	public ListaCriterioSeleccion RegistrarListaCriterio(ListaCriterioSeleccion pCriterio){
		return dao.RegistrarListaCriterio(pCriterio);
	}
	
	public void ActualizarListaCriterio(ListaCriterioSeleccion pCriterio) {
		dao.ActualizarListaCriterio(pCriterio);
	}
}
