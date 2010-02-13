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
import com.inia_mscc.modulos.adm.entidades.Region;

public class DAORegion implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAORegion.class);

	public List<Region> ObtenerRegiones() {
		List<Region> retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Region.class);
			retorno = (List<Region>) c.list();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public void ActualizarRegiones(Region pRegion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.update("Region", pRegion);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	public Region RegistrarRegion(Region pRegion) {
		Region region = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Long id = (Long) session.save("Region", pRegion);
			Criteria c = session.createCriteria(Region.class);
			c.add(Restrictions.eq("_id", id));
			region = (Region) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return region;
	}

	public Region ComprobarRegion(Region pRegion) {
		Region retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Region.class);
			c.add(Restrictions.ilike("_nombre", pRegion.get_nombre()));
			retorno = (Region) c.uniqueResult();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}
	
	public void EliminarRegion(Region pRegion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.delete("Region", pRegion);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException("No se puede eliminar la región, porque esta siendo utilizada.");
		}
	}
}