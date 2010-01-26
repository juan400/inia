package com.inia_mscc.modulos.comun.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.comun.dao.DAOComun;
import com.inia_mscc.modulos.comun.servicios.CommonsServices;


@Stateless(name="EJBComun", mappedName="EJBComun")
@Remote(CommonsServices.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.NEVER)
public class EJBComun {
	
	//private static final Logger logger = Logger.getLogger(DaoCommons.class);
	
	private DAOComun daoComun = new DAOComun();
	
	public <T> T obtenerEntidad(Class<T> clazz){
		return daoComun.obtenerEntidad(clazz);
	}
	
	
	
}
