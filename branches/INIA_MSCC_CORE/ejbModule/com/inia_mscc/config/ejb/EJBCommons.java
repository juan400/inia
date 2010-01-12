package com.inia_mscc.config.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.config.servicios.CommonsServices;
import com.inia_mscc.dao.comun.DaoCommons;


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
