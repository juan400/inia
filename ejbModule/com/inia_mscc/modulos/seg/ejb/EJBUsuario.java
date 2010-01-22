package com.inia_mscc.modulos.seg.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.seg.dao.DAOUsuario;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

@Stateless(name="EJBUsuario", mappedName="EJBUsuario")
@Remote(ServicioUsuario.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBUsuario implements ServicioUsuario {

	private DAOUsuario dao = new DAOUsuario();
	
	@Override
	public Usuario Login(String pLogin, String pPassword) {
		return dao.Login(pLogin, pPassword);
	}

	@Override
	public DatoUsuario RegistrarUsuario(DatoUsuario pUsuario) {
		return dao.RegistrarUsuario(pUsuario);
	}	
}
