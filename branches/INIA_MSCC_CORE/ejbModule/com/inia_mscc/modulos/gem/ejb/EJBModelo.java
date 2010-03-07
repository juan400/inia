package com.inia_mscc.modulos.gem.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOModelo;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.gem.servicios.ServicioModelo;

@Stateless(name = "EJBModelo", mappedName = "EJBModelo")
@Remote(ServicioModelo.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBModelo implements ServicioModelo {


	private DAOModelo dao = new DAOModelo();
	private EJBArchivo ejbArchivo = new EJBArchivo();

	
	@Override
	public void ActualizarModelo(Modelo pModelo) throws Exception {
		ejbArchivo.ActualizarArchivo(pModelo.get_archivoMSCC());
		if (pModelo.get_archivoMSCC() == null) {
			throw new Exception("No se pudo actualizar el archivo");
		}
		dao.ActualizarModelo(pModelo);
	}

	@Override
	public Modelo ObtenerModelo(Modelo pModelo) {
		return dao.ObtenerModelo(pModelo);
	}

	@Override
	public List<Modelo> ObtenerModelos(Modelo pModelo) {
		return dao.ObtenerModelos(pModelo);
	}

	@Override
	public Modelo RegistrarModelo(Modelo pModelo) throws Exception {
		pModelo.set_archivoMSCC(ejbArchivo.RegistrarArchivo(pModelo.get_archivoMSCC()));
		if (pModelo.get_archivoMSCC() == null) {
			throw new Exception("No se pudo registrar el archivo");
		}
		return dao.RegistrarModelo(pModelo);
	}

}
