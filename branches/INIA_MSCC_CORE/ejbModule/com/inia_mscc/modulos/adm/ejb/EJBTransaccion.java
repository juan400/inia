package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.adm.dao.DAOTransaccion;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.adm.servicios.ServicioTransaccion;

@Stateless(name = "EJBTransaccion", mappedName = "EJBTransaccion")
@Remote(ServicioTransaccion.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBTransaccion implements ServicioTransaccion {

	private DAOTransaccion dao = new DAOTransaccion();

	@Override
	public List<Transaccion> ObtenerTransacciones() {
		return dao.ObtenerTransacciones();
	}

	@Override
	public void ActualizarTransaccion(Transaccion pTransaccion) {
		dao.ActualizarTransaccion(pTransaccion);
	}


}
