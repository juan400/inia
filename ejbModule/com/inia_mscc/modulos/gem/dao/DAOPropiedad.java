package com.inia_mscc.modulos.gem.dao;

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
import com.inia_mscc.modulos.gem.entidades.Propiedad;

public class DAOPropiedad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(DAOPropiedad.class);

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Propiedad> ObtenerPropiedades(Propiedad pPropiedad) {
		List<Propiedad> listaPropiedad = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Propiedad.class);
			if (pPropiedad.get_tipo() != null) {
				c.add(Restrictions.eq("_tipo", pPropiedad.get_tipo()));
			}
			listaPropiedad = (List<Propiedad>) c.list();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaPropiedad;
	}

	/**
	 * @return
	 */
	public Propiedad ObtenerPropiedad(Propiedad pPropiedad) {
		Propiedad unPropiedad = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Propiedad.class);
			if (pPropiedad.get_id() != 0) {
				c.add(Restrictions.eq("_id", pPropiedad.get_id()));
			}
			if (!pPropiedad.get_nombre().isEmpty()) {
				c.add(Restrictions.eq("_codigo", pPropiedad.get_codigo()));
			}
			unPropiedad = (Propiedad) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unPropiedad;
	}

	/**
	 * Actualiza los datos de una propiedad de un cultivoa en el sitema.
	 * 
	 * @param pDatosUsuario
	 */
	public void ActualizarPropiedad(Propiedad pPropiedad) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Propiedad", pPropiedad);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}


	/**
	 * Actualiza los datos de una propiedad de un cultivoa en el sitema.
	 * 
	 * @param pDatosUsuario
	 */
	public void EliminarPropiedad(Propiedad pPropiedad) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.delete("Propiedad", pPropiedad);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}
	
}