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
import com.inia_mscc.modulos.comun.entidades.Enumerados.ListaCriterio;


public class DAOListaCriterio implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOListaCriterio.class);


	public List<ListaCriterioSeleccion> ObtenerCriterios() {
		List<ListaCriterioSeleccion> listaCriterio = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ListaCriterio.class);
			listaCriterio = (List<ListaCriterioSeleccion>) c.list();
		} catch (Exception e) {// catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaCriterio;
	}
	
	public void ActualizarCriterio(ListaCriterioSeleccion pCriterio) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Criterio", pCriterio);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}
	
	public ListaCriterioSeleccion ObtenerCriterioConValores(ListaCriterioSeleccion pCriterio) {
		ListaCriterioSeleccion retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ListaCriterioSeleccion.class);
			if (pCriterio.get_id()!= 0) {
				c.add(Restrictions.eq("_id", pCriterio.get_id()));
			}
			retorno = (ListaCriterioSeleccion) c.uniqueResult();
			if (retorno != null) {
				retorno.get_listaValores().get(0);
			}
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}
	
}
