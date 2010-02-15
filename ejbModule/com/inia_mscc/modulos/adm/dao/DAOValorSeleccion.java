package com.inia_mscc.modulos.adm.dao;

import java.io.Serializable;

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

	/**
	 * Recorre la tabla ValorSeleccion, filtrando por el Id.
	 * 
	 * @param pValor
	 * @return ValorSeleccion
	 */
	public ValorSeleccion ObtenerValorSeleccion(ValorSeleccion pValor) {
		ValorSeleccion unValor = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(ValorSeleccion.class);
			if (pValor.get_id() != 0) {
				c.add(Restrictions.eq("_id", pValor.get_id()));
			}
			if (!pValor.get_descripcion().isEmpty()) {
				c.add(Restrictions.eq("_descripcion", pValor.get_descripcion()));
			}
			unValor = (ValorSeleccion) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unValor;
	}

	/**
	 * Actualiza los datos de un Valor de Selección en el sitema.
	 * 
	 * @param pDatosUsuario
	 */
	public void ActualizarValorSeleccion(ValorSeleccion pValor) {
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
}
