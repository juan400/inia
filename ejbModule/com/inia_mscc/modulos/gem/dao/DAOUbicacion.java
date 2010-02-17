package com.inia_mscc.modulos.gem.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;

public class DAOUbicacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOUbicacion.class);

	@SuppressWarnings("unchecked")
	public List<Ubicacion> ObtenerUbicacions(Ubicacion pUbicacion) {
		List<Ubicacion> listaUbicacion = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Ubicacion.class);
			if (pUbicacion != null) {
				if (pUbicacion.get_tipoArchivo() != null) {
					c.add(Restrictions
							.eq("_tipo", pUbicacion.get_tipoArchivo()));
				}
			}
			listaUbicacion = (List<Ubicacion>) c.list();
		} catch (Exception e) {// catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaUbicacion;
	}

	public Ubicacion ObtenerUbicacion(Ubicacion pUbicacion) {
		Ubicacion unUbicacion = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Ubicacion.class);
			if (pUbicacion != null) {
				if (pUbicacion.get_id() != 0) {
					c.add(Restrictions.eq("_id", pUbicacion.get_id()));
				}
				if (pUbicacion.get_tipoArchivo() != null) {
					c.add(Restrictions
							.eq("_tipo", pUbicacion.get_tipoArchivo()));
				}
			}
			unUbicacion = (Ubicacion) c.uniqueResult();
		} catch (Exception e) {// catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unUbicacion;
	}

	public Ubicacion RegistrarUbicacion(Ubicacion pUbicacion) {
		Ubicacion cultivo = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Long id = (Long) session.save("Ubicacion", pUbicacion);
			Criteria c = session.createCriteria(Ubicacion.class);
			c.add(Restrictions.eq("_id", id));
			cultivo = (Ubicacion) c.uniqueResult();
		} catch (Exception e) {// catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return cultivo;
	}

	public void ActualizarUbicacion(Ubicacion pUbicacion) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Ubicacion", pUbicacion);
		} catch (Exception e) {// catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}
}
