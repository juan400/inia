package com.inia_mscc.modulos.seg.ejb;

import javax.ejb.MessageDriven;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.seg.dao.DAOUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

@Stateless(name="EJBUsuario", mappedName="EJBUsuario")
@Remote(ServicioUsuario.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
//@TransactionAttribute(value = TransactionAttributeType.NEVER)//Solo para crear la base de datos!!!!
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public @MessageDriven class EJBUsuario implements ServicioUsuario {

	private DAOUsuario dao = new DAOUsuario();
	
	@Override
	public Usuario login(String login, String password) {
		return dao.login(login, password);
	}

}
