package com.inia_mscc.modulos.seg.dao;

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
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class DAOUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(DAOUsuario.class);

	public Usuario Login(String loginNombre, String password) {
		Usuario usuario = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("_login", loginNombre));
			c.add(Restrictions.eq("_password", password));
			usuario = (Usuario) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}

	public DatoUsuario RegistrarUsuario(DatoUsuario pDatosUsuario) {
		DatoUsuario usuario = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.save(pDatosUsuario);
//			Criteria c = session.createCriteria(DatoUsuario.class);
//			c.add(Restrictions.eq("_nombre", pDatosUsuario.get_nombre()));
//			usuario = (DatoUsuario) c.uniqueResult();
		} catch (Exception e){//(StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}

	public void saveUser(Session session, Usuario u) {
		 session.save(u);
	}

	public boolean isUser(String loginNombre, Session session) {
		boolean existe = false;
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("_login", loginNombre));
		if (c.uniqueResult() != null) {
			existe = true;
		}
		return existe;
	}

}

// public class Ejemplo1 {
// private final static Logger log = Logger.getLogger(Ejemplo1.class);
// /**
// * @param args
// */
// public static void main(String[] args) {
// BasicConfigurator.configure();
// Logger.getLogger("org.hibernate").setLevel(Level.WARN);
// new Ejemplo1();
// }
//
// public Ejemplo1() {
// createAndStoreEvent("El Event", new Date());
// listEvents();
// HibernateUtil.getSessionFactory().close();
// }
//
// private Long createAndStoreEvent(String title, Date theDate) {
// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
// session.beginTransaction();
// Event theEvent = new Event();
// theEvent.setTitle(title);
// theEvent.setDate(theDate);
// session.save(theEvent);
// session.getTransaction().commit();
// log.info("Insertado: "+theEvent);
// return theEvent.getId();
// }
//
// private List<Event> listEvents() {
// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
// session.beginTransaction();
// List<Event> result = (List<Event>)session.createQuery("from Event").list();
// session.getTransaction().commit();
// for (Event evento : result) {
// log.info("Leido: "+evento);
// }
// return result;
// }
//
// }

