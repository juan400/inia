package com.inia_mscc.modulos.seg.dao;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.Restrictions;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;
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
	 * Actualiza los datos de un usuario registrado en el sitema.
	 * 
	 * @param pDatosUsuario
	 */
	public void ActualizarUltimoAcceso(Usuario pUsuario) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Usuario", pUsuario);
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
	public Usuario ObtenerUsuarioXDatos(String pLoginName, Date pUltimoAcceso,
			Perfil pPerfil, String pEmail, String pFrase) {
		Usuario usuario = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Criteria c = session.createCriteria(Usuario.class);
			if (pLoginName != null) {
				c.add(Restrictions.eq("_login", pLoginName));

			}
			if (pUltimoAcceso != null) {
				c.add(Restrictions.eq("_ultimoAcceso", pUltimoAcceso));

			}
			if (pPerfil != null) {
				Criteria cDU = session.createCriteria(DatoUsuario.class);
				if (!cDU.add(Restrictions.eq("_perfil", pPerfil)).list()
						.isEmpty()) {
					c.add(Restrictions.eq("_datos", (DatoUsuario) c
							.uniqueResult()));
				}
			}
			if (pEmail != null) {
				Criteria cDU = session.createCriteria(DatoUsuario.class);
				cDU.add(Restrictions.eq("_mail", pEmail));
				if (cDU.uniqueResult() != null) {
					c.add(Restrictions.eq("_datos", (DatoUsuario) cDU
							.uniqueResult()));
				}
			}
			if (pFrase != null) {
				c.add(Restrictions.ilike("_frase", pFrase));
			}
			usuario = (Usuario) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}

	/**
	 * @param pClave
	 * @return
	 */
	public boolean ComprobarEmail(String pEmail) {
		boolean retorno = false;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Criteria c = session.createCriteria(DatoUsuario.class);
			retorno = c.add(Restrictions.eq("_mail", pEmail)).list().isEmpty();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	/**
	 * Este metodo registra el usuario en el sistema.
	 * 
	 * @param pUsuario
	 * @return Devuelve el usuario con la clave generada.
	 */
	public Usuario RegistrarUsuario(Usuario pUsuario) {
		Usuario usuario = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			pUsuario.get_datos().set_id(
					(Long) session.save("DatoUsuario", pUsuario.get_datos()));
			Long id = (Long) session.save("Usuario", pUsuario);
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("_id", id));
			usuario = (Usuario) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}

	/**
	 * Actualiza los datos de un usuario registrado en el sitema.
	 * 
	 * @param pDatosUsuario
	 */
	public void ActualizarDatos(DatoUsuario pDatosUsuario) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("DatoUsuario", pDatosUsuario);
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
	public Usuario ComprobarClaveReigstro(String pClave) {
		Usuario retorno = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("_codigoActivacion", pClave));
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
	 * 
	 * @param pUsuario
	 */
	public void CambiarPassword(Usuario pUsuario) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
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
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("_login", loginNombre));
			c.add(Restrictions.eq("_password", password));
			usuario = (Usuario) c.uniqueResult();
			if (usuario != null) {
				usuario.get_datos().get_perfil().get_transaccionesSistema()
						.get(1);
			}
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return usuario;
	}

}