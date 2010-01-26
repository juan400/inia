package com.inia_mscc.modulos.seg.ejb;

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
public class EJBPerfil implements ServicioPerfil{

	private DAOPerfil dao = new DAOPerfil();

	@Override
	public void ActualizarPerfil(Perfil pPerfil) {
		dao.ActualizarPerfil(pPerfil);
	}

	@Override
	public Perfil RegistrarPerfil(Perfil pPerfil) {
		return dao.RegistrarPerfil(pPerfil);
	}
}
