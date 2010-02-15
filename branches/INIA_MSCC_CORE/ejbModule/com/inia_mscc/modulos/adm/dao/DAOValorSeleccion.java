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
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;

public class DAOValorSeleccion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(DAOValorSeleccion.class);


	public ValorSeleccion RegistrarValorSeleccion(ValorSeleccion pValor) {
		ValorSeleccion valor = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Long id = (Long) session.save("ValorSeleccion", pValor);
			Criteria c = session.createCriteria(ValorSeleccion.class);
			c.add(Restrictions.eq("_id", id));
			valor = (ValorSeleccion) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return valor;
	}
	
	public void ActualizarValorSeleccion(ValorSeleccion pValor) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.update("ValorSeleccion", pValor);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	public ValorSeleccion ComprobarValorSeleccion(ValorSeleccion pValor) {
		ValorSeleccion retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ValorSeleccion.class);
			c.add(Restrictions.ilike("_descripcion", pValor.get_descripcion())); 
			retorno = (ValorSeleccion) c.uniqueResult();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public List<ValorSeleccion> ObtenerValores() {
		List<ValorSeleccion> retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ValorSeleccion.class);
			retorno = (List<ValorSeleccion>) c.list();
		} catch (Exception e) { 
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}
}