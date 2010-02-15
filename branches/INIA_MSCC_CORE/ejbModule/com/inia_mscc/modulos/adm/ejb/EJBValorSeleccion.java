package com.inia_mscc.modulos.adm.ejb;

import com.inia_mscc.modulos.adm.dao.DAOValorSeleccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioValorSeleccion;

public class EJBValorSeleccion implements ServicioValorSeleccion {

	private DAOValorSeleccion dao = new DAOValorSeleccion();
	
	@Override
	public void ActualizarValorSeleccion(ValorSeleccion pValor){
		dao.ActualizarValorSeleccion(pValor);
	}

	public  ValorSeleccion ObtenerValorSeleccion(ValorSeleccion pValor){
		return dao.ObtenerValorSeleccion(pValor);
	}

}
