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
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;

public class DAODepartamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(DAODepartamento.class);

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Departamento> ObtenerDepartamentosXPais(Pais pPais) {
		List<Departamento> listDepartamento = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Departamento.class);
			c.add(Restrictions.eq("_pais", pPais));
			listDepartamento = (List<Departamento>) c.list();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listDepartamento;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Departamento> ObtenerDepartamentos() {
		List<Departamento> listaDepartamento = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Departamento.class);
			listaDepartamento = (List<Departamento>) c.list();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaDepartamento;
	}

	/**
	 * @return
	 */
	public Departamento ObtenerDepartamento(Departamento pDepartamento) {
		Departamento unDepartamento = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Departamento.class);
			if (pDepartamento.get_id() != 0) {
				c.add(Restrictions.eq("_id", pDepartamento.get_id()));
			}
			if (!pDepartamento.get_nombre().isEmpty()) {
				c.add(Restrictions.eq("_nombre", pDepartamento.get_nombre()));
			}
			unDepartamento = (Departamento) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unDepartamento;
	}

}
