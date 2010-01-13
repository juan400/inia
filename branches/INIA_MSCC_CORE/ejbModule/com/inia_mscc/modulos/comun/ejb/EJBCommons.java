package com.inia_mscc.modulos.comun.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.comun.dao.DaoCommons;
import com.inia_mscc.modulos.comun.servicios.CommonsServices;


@Stateless(name="EJBCommons", mappedName="EJBCommons")
@Remote(CommonsServices.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.NEVER)
public class EJBCommons {

	private DaoCommons daoCommons = new DaoCommons();
	
	public <T> T obtenerEntidad(Class<T> clazz){
		return daoCommons.obtenerEntidad(clazz);
	}
	
	
	
}
