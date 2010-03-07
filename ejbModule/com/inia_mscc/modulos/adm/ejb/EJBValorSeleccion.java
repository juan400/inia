package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.adm.dao.DAOValorSeleccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioListaCriterio;
import com.inia_mscc.modulos.adm.servicios.ServicioValorSeleccion;

@Stateless(name = "EJBValorSeleccion", mappedName = "EJBValorSeleccion")
@Remote(ServicioValorSeleccion.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBValorSeleccion implements ServicioValorSeleccion {

	private DAOValorSeleccion dao = new DAOValorSeleccion();
	
	@Override
	public List<ValorSeleccion> ObtenerValores(ValorSeleccion pValor){
		return dao.ObtenerValores(pValor);
	}

	@Override
	public ValorSeleccion ObtenerValor(ValorSeleccion pValor){
		return dao.ObtenerValor(pValor);
	}
	
	@Override
	public void ActualizarValor(ValorSeleccion pValor){
		dao.ActualizarValor(pValor);
	}
	
	@Override
	public void EliminarValor(ValorSeleccion pValor){
		dao.EliminarValor(pValor);
	}
	
	@Override
	public void EliminarValores(List<ValorSeleccion> pValores) {
		for (ValorSeleccion valor : pValores) {
			dao.EliminarValor(valor);
		}
	}
}
