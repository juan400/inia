package com.inia_mscc.config.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;

import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.excepciones.ObjetoNoEncontradoException;

public class HibernateDAO {

	private static Logger logger = Logger.getLogger(HibernateDAO.class);

	
	protected Session getCargoSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	
	public <T> T findById(Class<T> objectClass, Serializable uid) throws IniaPersistenciaException {
		Session s = getCargoSession();
		try {

			T entity = (T) s.get(objectClass, uid);
			if (entity == null) {
				throw new ObjetoNoEncontradoException();
			}
			return entity;
		} catch (HibernateException e) {
			logger.info(e.getMessage());
			throw new IniaPersistenciaException(e);
		}
	}

	protected Object saveOrUpdate(Object o) {

		Session s = getCargoSession();

		try {
			s.saveOrUpdate(o);
		} catch (StaleObjectStateException e) {
			logger.info(e.getMessage());
			throw new IniaPersistenciaException(e);

		} catch (ConstraintViolationException e) {
			logger.info(e.getMessage());
			throw new IniaPersistenciaException(e);

		} catch (HibernateException e) {
			logger.info(e.getMessage());
			throw new IniaPersistenciaException(e);
		}
		return o;

	}

	protected void deleteObject(Object o) throws IniaPersistenciaException {

		Session s = null;

		try {
			s = getCargoSession();
			s.delete(o);
		} catch (HibernateException he) {
			throw new IniaPersistenciaException(he);
		}

	}

	
	protected <T> List<T> findAll(Class<T> o) throws HibernateException {
		List<T> results = new ArrayList<T>();
		try {
			Session s = this.getCargoSession();

			results = s.createCriteria(o).list();

		} catch (HibernateException objHibernateException) {
			logger.info(objHibernateException.getMessage());
			throw new IniaPersistenciaException(objHibernateException);
		}
		return results;

	}

	
	protected <T> List<T> findAll(Class<T> o, Order order) throws HibernateException {
		List<T> results = new ArrayList<T>();
		try {
			Session s = this.getCargoSession();
			Criteria c = s.createCriteria(o);
			c.addOrder(order);
			results = c.list();
		} catch (HibernateException objHibernateException) {
			logger.info(objHibernateException.getMessage());
			throw new IniaPersistenciaException(objHibernateException);
		}
		return results;

	}

}
