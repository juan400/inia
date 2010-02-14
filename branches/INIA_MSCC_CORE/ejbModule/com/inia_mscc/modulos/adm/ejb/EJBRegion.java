package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.adm.dao.DAORegion;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.adm.servicios.ServicioRegion;

@Stateless(name = "EJBRegion", mappedName = "EJBRegion")
@Remote(ServicioRegion.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBRegion implements ServicioRegion {

	private DAORegion dao = new DAORegion();

	@Override
	public void ActualizarRegion(Region pRegion) {
		dao.ActualizarRegiones(pRegion);
	}

	@Override
	public Region RegistrarRegion(Region pRegion) {
		return dao.RegistrarRegion(pRegion);
	}

	@Override
	public List<Region> ObtenerRegiones() {
		return dao.ObtenerRegiones();
	}

	@Override
	public Region ComprobarRegion(Region pRegion) {
		return dao.ComprobarRegion(pRegion);
	}

	@Override
	public Region ComprobarRegionCodigo(Region pRegion) {
		return dao.ComprobarRegionCodigo(pRegion);
	}
	
	@Override
	public void EliminarRegion(Region pRegion){
		dao.EliminarRegion(pRegion);
	}
}
