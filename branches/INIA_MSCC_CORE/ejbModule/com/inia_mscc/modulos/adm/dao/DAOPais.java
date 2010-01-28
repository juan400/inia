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
import com.inia_mscc.modulos.adm.entidades.Pais;

public class DAOPais  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOPais.class);


	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pais> ObtenerPaises() {
		List<Pais> listaPais = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Pais.class);
			listaPais = (List<Pais>) c.list();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaPais;
	}
	
	/**
	 * @return
	 */
	public Pais ObtenerPais(Pais pPais) {
		Pais unPais = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Pais.class);
			if (pPais.get_id()!= 0){
				c.add(Restrictions.eq("_id", pPais.get_id()));
			}
			if (!pPais.get_nombre().isEmpty()){
				c.add(Restrictions.eq("_nombre", pPais.get_nombre()));
			}
			unPais = (Pais) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unPais;
	}
	
}
