package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.adm.dao.DAOListaCriterio;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioListaCriterio;

@Stateless(name = "EJBListaCriterio", mappedName = "EJBListaCriterio")
@Remote(ServicioListaCriterio.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBListaCriterio implements ServicioListaCriterio {

	private DAOListaCriterio dao = new DAOListaCriterio();

	@Override
	public ListaCriterioSeleccion ObtenerCriterio(ListaCriterioSeleccion pCriterio){
		return dao.ObtenerCriterio(pCriterio);
	}

	@Override
	public List<ListaCriterioSeleccion> ObtenerListaCriterio(){
		return dao.ObtenerListaCriterio();
	}
	
	@Override
	public void ActualizarListaCriterio(ListaCriterioSeleccion pCriterio) {
		dao.ActualizarListaCriterio(pCriterio);
	}
	
}
