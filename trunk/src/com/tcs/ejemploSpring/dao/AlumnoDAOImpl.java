package com.tcs.ejemploSpring.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tcs.ejemploSpring.modelo.Alumno;

public class AlumnoDAOImpl implements IAlumnoDAO  {
	
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	
	public void agregarAlumno(Alumno alumno) {
		// Se agrega a la BD
		try {
			this.saveOrUpdate(alumno);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<Alumno> obtenerAlumnos() {
		List lst = (List) this.hibernateTemplate.execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List result = session.createCriteria(Alumno.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.addOrder(Order.asc("nombre"))
					.list();
				session.evict(result);
				return result;
			}
			
		});
		
		return lst;
	}	

	public Alumno saveOrUpdate(final Alumno alumno) {
		this.hibernateTemplate.execute(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				try {
					session.beginTransaction();
					session.saveOrUpdate(alumno);
					session.getTransaction().commit();
				} catch (RuntimeException e) {
					session.getTransaction().rollback();
				}
				return alumno;
			}
			
		});
		return alumno;
	}
}
