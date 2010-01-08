package com.inia_mscc.config.hibernate;

import org.hibernate.Session;

public class CargoHibernateDAO {

//	private static Logger logger = Logger.getLogger(CargoHibernateDAO.class);

	protected Session getCargoSession() {
		return CargoHibernateUtil.getSessionFactory().getCurrentSession();
	}

//	@SuppressWarnings("unchecked")
//	public <T> T findById(Class<T> objectClass, Serializable uid) throws CargoPersistenciaException {
//		Session s = getCargoSession();
//		try {
//
//			T entity = (T) s.get(objectClass, uid);
//			if (entity == null) {
//				throw new CargoNoExisteEntidadException();
//			}
//			return entity;
//		} catch (HibernateException e) {
//			logger.info(e.getMessage());
//			throw new CargoPersistenciaException(e);
//		}
//	}
//
//	protected Object saveOrUpdate(Object o) {
//
//		Session s = getCargoSession();
//
//		try {
//			s.saveOrUpdate(o);
//		} catch (StaleObjectStateException e) {
//			logger.info(e.getMessage());
//			throw new CargoPersistenciaException(e);
//
//		} catch (ConstraintViolationException e) {
//			logger.info(e.getMessage());
//			throw new CargoPersistenciaException(e);
//
//		} catch (HibernateException e) {
//			logger.info(e.getMessage());
//			throw new CargoPersistenciaException(e);
//		}
//		return o;
//
//	}
//
//	protected void deleteObject(Object o) throws CargoPersistenciaException {
//
//		Session s = null;
//
//		try {
//			s = getCargoSession();
//			s.delete(o);
//		} catch (HibernateException he) {
//			throw new CargoPersistenciaException(he);
//		}
//
//	}
//
//	@SuppressWarnings("unchecked")
//	protected <T> List<T> findAll(Class<T> o) throws HibernateException {
//		List<T> results = new ArrayList<T>();
//		try {
//			Session s = this.getCargoSession();
//
//			results = s.createCriteria(o).list();
//
//		} catch (HibernateException objHibernateException) {
//			logger.info(objHibernateException.getMessage());
//			throw new CargoPersistenciaException(objHibernateException);
//		}
//		return results;
//
//	}
//
//	@SuppressWarnings("unchecked")
//	protected <T> List<T> findAll(Class<T> o, Order order) throws HibernateException {
//		List<T> results = new ArrayList<T>();
//		try {
//			Session s = this.getCargoSession();
//			Criteria c = s.createCriteria(o);
//			c.addOrder(order);
//			results = c.list();
//		} catch (HibernateException objHibernateException) {
//			logger.info(objHibernateException.getMessage());
//			throw new CargoPersistenciaException(objHibernateException);
//		}
//		return results;
//
//	}

}
