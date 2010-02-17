package com.inia_mscc.modulos.gem.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOUbicacion;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;
import com.inia_mscc.modulos.gem.servicios.ServicioUbicacion;


@Stateless(name = "EJBUbicacion", mappedName = "EJBUbicacion")
@Remote(ServicioUbicacion.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBUbicacion implements ServicioUbicacion {

	private DAOUbicacion dao = new DAOUbicacion();

	@Override
	public void ActualizarUbicacion(Ubicacion pUbicacion) {
		dao.ActualizarUbicacion(pUbicacion);
	}

	@Override
	public Ubicacion ObtenerUbicacion(Ubicacion pUbicacion) {
		return dao.ObtenerUbicacion(pUbicacion);
	}

	@Override
	public List<Ubicacion> ObtenerUbicacions(Ubicacion pUbicacion) {
		return dao.ObtenerUbicacions(pUbicacion);
	}

	@Override
	public Ubicacion RegistrarUbicacion(Ubicacion pUbicacion) {
		return dao.RegistrarUbicacion(pUbicacion);
	}
}