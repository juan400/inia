package com.inia_mscc.modulos.adm.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.Restrictions;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.adm.entidades.Ciudad;

public class DAOCiudad  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOCiudad.class);


	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ciudad> ObtenerCiudades() {
		List<Ciudad> listaCiudad = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Ciudad.class);
			listaCiudad  = (List<Ciudad>) c.list();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaCiudad ;
	}
	
	
	/**
	 * @return
	 */
	public Ciudad ObtenerCiudad(Ciudad pCiudad) {
		Ciudad unCiudad = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Ciudad.class);
			if (pCiudad.get_id()!= 0){
				c.add(Restrictions.eq("_id", pCiudad.get_id()));
			}
			if (!pCiudad.get_nombre().isEmpty()){
				c.add(Restrictions.eq("_nombre", pCiudad.get_nombre()));
			}
			unCiudad = (Ciudad) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unCiudad;
	}
	
}
