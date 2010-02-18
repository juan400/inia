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
import com.inia_mscc.modulos.gem.entidades.Archivo;

public class DAOArchivo  implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOArchivo.class);

	@SuppressWarnings("unchecked")
	public List<Archivo> ObtenerArchivos(Archivo pArchivo) {
		List<Archivo> listaArchivo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Archivo.class);
			listaArchivo = (List<Archivo>) c.list();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaArchivo;
	}

	public Archivo ObtenerArchivo(Archivo pArchivo) {
		Archivo unArchivo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Archivo.class);
			if (pArchivo.get_id() != 0) {
				c.add(Restrictions.eq("_id", pArchivo.get_id()));
			}
			if (!pArchivo.get_nombre().isEmpty()) {
				c.add(Restrictions.eq("_nombre", pArchivo.get_nombre()));
			}
			unArchivo = (Archivo) c.uniqueResult();
		}catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unArchivo;
	}

	public Archivo RegistrarArchivo(Archivo pArchivo) {
		Archivo cultivo = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Long id = (Long) session.save("Archivo", pArchivo);
			Criteria c = session.createCriteria(Archivo.class);
			c.add(Restrictions.eq("_id", id));
			cultivo = (Archivo) c.uniqueResult();
		}catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return cultivo;
	}

	public void ActualizarArchivo(Archivo pArchivo) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Archivo", pArchivo);
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

}
