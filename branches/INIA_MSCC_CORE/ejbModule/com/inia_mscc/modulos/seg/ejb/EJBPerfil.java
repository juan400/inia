package com.inia_mscc.modulos.seg.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.seg.dao.DAOPerfil;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.servicios.ServicioPerfil;

@Stateless(name = "EJBPerfil", mappedName = "EJBPerfil")
@Remote(ServicioPerfil.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBPerfil implements ServicioPerfil {

	private DAOPerfil dao = new DAOPerfil();

	@Override
	public void ActualizarPerfil(Perfil pPerfil) {
		dao.ActualizarPerfil(pPerfil);
	}

	@Override
	public Perfil RegistrarPerfil(Perfil pPerfil) {
		return dao.RegistrarPerfil(pPerfil);
	}

	@Override
	public List<Perfil> ObtenerPerfiles() {
		return dao.ObtenerPerfiles();
	}

	@Override
	public Perfil ComprobarPerfil(Perfil pPerfil) {
		return dao.ComprobarPerfil(pPerfil);
	}

	@Override
	public String EliminarPerfil(Perfil pPerfil) {
		if (!dao.ComprobarPerfilEnUso(pPerfil)) {
			dao.EliminarPerfil(pPerfil);
		} else {
			return "No es posible eliminar este perfil,"
					+ " actualmente esta siendo usado por uno o más "
					+ "usuario en el sistema.";
		}
		return "";
	}

	@Override
	public Perfil ObtenerPerfilConTransAsociadas(Perfil pPerfil) {
		return dao.ObtenerPerfilConTransAcosiadas(pPerfil);
	}

}
