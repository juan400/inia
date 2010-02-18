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

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(DAOValorSeleccion.class);

	@SuppressWarnings("unchecked")
	public List<ValorSeleccion> ObtenerValores(ValorSeleccion pValor) {
		List<ValorSeleccion> listaValor = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ValorSeleccion.class);

			listaValor = (List<ValorSeleccion>) c.list();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaValor;
	}

	public ValorSeleccion ObtenerValor(ValorSeleccion pValor) {
		ValorSeleccion unValor = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ValorSeleccion.class);
			if (pValor.get_id() != 0) {
				c.add(Restrictions.eq("_id", pValor.get_id()));
			}
			if (!pValor.get_descripcion().isEmpty()) {
				c.add(Restrictions.eq("_codigo", pValor.get_codigo()));
			}
			unValor = (ValorSeleccion) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unValor;
	}

	public void ActualizarValor(ValorSeleccion pValor) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("ValorSeleccion", pValor);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	public void EliminarValor(ValorSeleccion pValor) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.delete("ValorSelecccion", pValor);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

}
