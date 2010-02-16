package com.inia_mscc.modulos.gem.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOCultivo;
import com.inia_mscc.modulos.gem.dao.DAOPropiedad;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.servicios.ServicioPropiedad;

@Stateless(name = "EJBPropiedad", mappedName = "EJBPropiedad")
@Remote(ServicioPropiedad.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBPropiedad implements ServicioPropiedad {

	private DAOPropiedad dao = new DAOPropiedad();

	@Override
	public void ActualizarPropiedad(Propiedad pPropiedad) {
		dao.ActualizarPropiedad(pPropiedad);
	}

	@Override
	public Propiedad ObtenerPropiedad(Propiedad pPropiedad) {
		return dao.ObtenerPropiedad(pPropiedad);
	}

	@Override
	public List<Propiedad> ObtenerPropiedades(Propiedad pPropiedad) {
		return dao.ObtenerPropiedades(pPropiedad);
	}

	@Override
	public void EliminarPropiedad(Propiedad pPropiedad) {
			dao.EliminarPropiedad(pPropiedad);
	}

	@Override
	public void EliminarPropiedades(List<Propiedad> pPropiedades) {
		for (Propiedad propiedad : pPropiedades) {
			dao.EliminarPropiedad(propiedad);
		}
	}
	
}