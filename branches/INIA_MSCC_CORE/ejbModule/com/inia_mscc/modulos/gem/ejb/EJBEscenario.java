package com.inia_mscc.modulos.gem.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOEscenario;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.servicios.ServicioEscenario;

@Stateless(name = "EJBEscenario", mappedName = "EJBEscenario")
@Remote(ServicioEscenario.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBEscenario implements ServicioEscenario {

	private DAOEscenario dao = new DAOEscenario();
	private EJBArchivo ejbArchivo = new EJBArchivo();

	@Override
	public void ActualizarEscenario(Escenario pEscenario) throws Exception {
		ejbArchivo.ActualizarArchivo(pEscenario.get_archivoEscenario());
		if (pEscenario.get_archivoEscenario() == null) {
			throw new Exception("No se pudo actualizar el archivo");
		}
		dao.ActualizarEscenario(pEscenario);
	}

	@Override
	public Escenario ObtenerEscenario(Escenario pEscenario) {
		return dao.ObtenerEscenario(pEscenario);
	}

	@Override
	public List<Escenario> ObtenerEscenarios(Escenario pEscenario) {
		return dao.ObtenerEscenarios(pEscenario);
	}

	@Override
	public Escenario RegistrarEscenario(Escenario pEscenario) throws Exception {
		pEscenario.set_archivoEscenario(ejbArchivo.RegistrarArchivo(pEscenario.get_archivoEscenario()));
		if (pEscenario.get_archivoEscenario() == null) {
			throw new Exception("No se pudo registrar el archivo");
		}
		return dao.RegistrarEscenario(pEscenario);
	}

}
