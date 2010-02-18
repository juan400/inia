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
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;


public class DAOListaCriterio implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOListaCriterio.class);
	
	
	public List<ListaCriterioSeleccion> ObtenerListaCriterio() {
		List<ListaCriterioSeleccion> listaCriterio = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ListaCriterioSeleccion.class);
			listaCriterio = (List<ListaCriterioSeleccion>) c.list();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaCriterio;
	}

	public ListaCriterioSeleccion ObtenerCriterio(ListaCriterioSeleccion pCriterio) {
		ListaCriterioSeleccion unCriterio = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ListaCriterioSeleccion.class);
			if (pCriterio.get_id() != 0) {
				c.add(Restrictions.eq("_id", pCriterio.get_id()));
				System.out.println(pCriterio.get_id());
			}
			if (!pCriterio.get_descripcion().isEmpty()) {
				c.add(Restrictions.eq("_descripcion", pCriterio.get_descripcion()));
				System.out.println(pCriterio.get_descripcion());
			}
			unCriterio = (ListaCriterioSeleccion) c.uniqueResult();
			if (unCriterio != null
					&& unCriterio.get_listaValores().size() != 0) {
				unCriterio.get_listaValores().get(0);
			}
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unCriterio;
	}

	public void ActualizarListaCriterio(ListaCriterioSeleccion pCriterio) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("ListaCriterioSeleccion", pCriterio);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}
}
