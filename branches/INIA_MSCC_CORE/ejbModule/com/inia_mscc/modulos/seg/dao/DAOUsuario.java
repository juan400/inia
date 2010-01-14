package com.inia_mscc.modulos.seg.dao;

import java.util.List;

//import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.Restrictions;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.seg.entidades.Usuario;
//import com.util.LoggingUtilities;

public class DAOUsuario {
	//private static final Logger logger = Logger.getLogger(DAOUsuario.class);

	public Usuario login(String login, String password) {
		Usuario usuario = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Criteria c;
            c = session.createCriteria(Usuario.class);
            c.add(Restrictions.eq("usua_str_login", login));
            c.add(Restrictions.eq("usua_str_password", password));
            usuario = (Usuario)c.uniqueResult();
			session.flush();

		} catch (StaleObjectStateException e) {
			//String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			//logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}

	public List<Usuario> getUsers() {
		List<Usuario> ret = null;

//		Transaction tx = null;
//		Session session = InitSessionFactory.getInstance().getCurrentSession();
//		try {
//			tx = session.beginTransaction();
//			ret = (List<Usuario>)session.createQuery("from Usuario").list();
//			tx.commit();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			if (tx != null && tx.isActive())
//				tx.rollback();
//		}
		return ret;
	}

	public void saveUser(Session session, Usuario u) {
//		session.saveOrUpdate(u);
	}

	public boolean isUser(String login, Session session) {
		boolean existe = false;
//		List res = session.createQuery(
//			"select u from tl_seg_usua_usuario as u " +
//			" where u.usua_str_login='" + login + "' "
//		).list();
//		existe = res.size() > 0;
		return existe;
	}
	
}
