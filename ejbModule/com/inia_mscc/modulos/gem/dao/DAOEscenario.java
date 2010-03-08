package com.inia_mscc.modulos.gem.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.entidades.Modelo;

public class DAOEscenario implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOArchivo.class);

	@SuppressWarnings("unchecked")
	public List<Escenario> ObtenerEscenarios(Escenario pEscenario) {
		List<Escenario> listaEscenario = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Escenario.class);
			if (pEscenario.get_id() != 0) {
				c.add(Restrictions.eq("_id", pEscenario.get_id()));
			}
			if (pEscenario.get_cultivo() != null) {
				c.add(Restrictions.eq("_cultivo", pEscenario.get_cultivo()));
			}
			if (pEscenario.get_region() != null) {
				c.add(Restrictions.eq("_region", pEscenario.get_region()));
			}
			if (pEscenario.get_usuarioInvestigador() != null) {
				c.add(Restrictions.eq("_usuarioInvestigador", pEscenario
						.get_usuarioInvestigador()));
			}
			if (pEscenario.get_fechaHora() != null) {
				c
						.add(Restrictions.eq("_fechaHora", pEscenario
								.get_fechaHora()));
			}
			listaEscenario = (List<Escenario>) c.list();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaEscenario;
	}

	public Escenario ObtenerEscenario(Escenario pEscenario) {
		Escenario unEscenario = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Escenario.class);
			if (pEscenario.get_id() != 0) {
				c.add(Restrictions.eq("_id", pEscenario.get_id()));
			}
			if (pEscenario.get_cultivo() != null) {
				c.add(Restrictions.eq("_cultivo", pEscenario.get_cultivo()));
			}
			if (pEscenario.get_region() != null) {
				c.add(Restrictions.eq("_region", pEscenario.get_region()));
			}
			if (pEscenario.get_usuarioInvestigador() != null) {
				c.add(Restrictions.eq("_usuarioInvestigador", pEscenario
						.get_usuarioInvestigador()));
			}
			if (pEscenario.get_fechaHora() != null) {
				c
						.add(Restrictions.eq("_fechaHora", pEscenario
								.get_fechaHora()));
			}
//			c.setProjection(Projections.max("_fechaHora"));
			unEscenario = (Escenario) c.list().get(0);
//			unEscenario = (Escenario) c.uniqueResult();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unEscenario;
	}

	public Escenario RegistrarEscenario(Escenario pEscenario) {
		Escenario cultivo = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Long id = (Long) session.save("Escenario", pEscenario);
			Criteria c = session.createCriteria(Escenario.class);
			c.add(Restrictions.eq("_id", id));
			cultivo = (Escenario) c.uniqueResult();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return cultivo;
	}

	public void ActualizarEscenario(Escenario pEscenario) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Escenario", pEscenario);
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

}
