package com.inia_mscc.modulos.seg.dao;

import java.io.Serializable;

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

/**
 * @author Juan Andres Pio
 *
 */
public class DAOUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOUsuario.class);

	/**
	 * Este metodo registra el usuario en el sistema.
	 * @param pUsuario
	 * @return 
	 * Devuelve el usuario con la clave generada.
	 */
	public Usuario RegistrarUsuario(Usuario pUsuario) {
		Usuario usuario = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			pUsuario.get_datos().set_id((Long)session.save("DatoUsuario", pUsuario.get_datos()));			
			Long id  = (Long)session.save("Usuario", pUsuario);
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("_id", id));
			usuario = (Usuario) c.uniqueResult();
		} catch(Exception e){ // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}
	
	/**
	 * Actualiza los datos de un usuario registrado en el sitema.
	 * @param pDatosUsuario
	 */
	public void ActualizarDatos(DatoUsuario pDatosUsuario) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.update("Usuario", pDatosUsuario);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	/**
	 * @param pClave
	 * @return
	 */
	public Usuario ComprobarClaveReigstro(String pClave){
		Usuario retorno=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("_password", pClave));
			retorno = (Usuario) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	/**
	 * Cambia la contrasenia de usauario.
	 * @param pUsuario
	 */
	public void CambiarPassword(Usuario pUsuario) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.update(pUsuario);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	/**
	 * @param loginNombre
	 * @param password
	 * @return 
	 */
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
	
}