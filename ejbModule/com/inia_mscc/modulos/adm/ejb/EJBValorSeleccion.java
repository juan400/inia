package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import com.inia_mscc.modulos.adm.dao.DAOValorSeleccion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioValorSeleccion;

public class EJBValorSeleccion implements ServicioValorSeleccion {

	private DAOValorSeleccion dao = new DAOValorSeleccion();

	@Override
	public ValorSeleccion RegistrarValorSeleccion(ValorSeleccion pValor)  {
		return dao.RegistrarValorSeleccion(pValor);
	}
	
	@Override
	public void ActualizarValorSeleccion(ValorSeleccion pValor){
		dao.ActualizarValorSeleccion(pValor);
	}

	public ValorSeleccion ComprobarValorSeleccion(ValorSeleccion pValor){
		return dao.ComprobarValorSeleccion(pValor);
	}

	public  List<ValorSeleccion> ObtenerValores(){
		return dao.ObtenerValores();
	}
}
