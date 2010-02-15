package com.inia_mscc.modulos.gem.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOCultivo;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.servicios.ServicioCultivo;
@Stateless(name = "EJBCultivo", mappedName = "EJBCultivo")
@Remote(ServicioCultivo.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBCultivo implements ServicioCultivo {

	private DAOCultivo dao = new DAOCultivo();

	@Override
	public void ActualizarCultivo(Cultivo pCultivo) {
		dao.ActualizarCultivo(pCultivo);
	}

	@Override
	public Cultivo ObtenerCultivo(Cultivo pCultivo) {
		return dao.ObtenerCultivo(pCultivo);
	}

	@Override
	public List<Cultivo> ObtenerCultivos(Cultivo pCultivo) {
		return dao.ObtenerCultivos(pCultivo);
	}

	@Override
	public Cultivo RegistrarCultivo(Cultivo pCultivo) {
		return dao.RegistrarCultivo(pCultivo);
	}
}
