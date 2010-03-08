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
import com.inia_mscc.modulos.gem.entidades.Modelo;

public class DAOModelo implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOArchivo.class);

	@SuppressWarnings("unchecked")
	public List<Modelo> ObtenerModelos(Modelo pModelo) {
		List<Modelo> listaModelo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Modelo.class);
			if (pModelo != null) {
				if (pModelo.get_id() != 0) {
					c.add(Restrictions.eq("_id", pModelo.get_id()));
				}
				if (pModelo.get_escenario() != null) {
					if (pModelo.get_id() != 0) {
						c.add(Restrictions.eq("_escenario", pModelo
								.get_escenario()));
					}
					// else {
					// if (pModelo.get_escenario().get_cultivo() != null) {
					// c.add(Restrictions.eq("_cultivo", pModelo
					// .get_escenario().get_cultivo()));
					// }
					// if (pModelo.get_escenario().get_region() != null) {
					// c.add(Restrictions.eq("_region", pModelo
					// .get_escenario().get_region()));
					// }
					// }
				}
				if (pModelo.get_usuarioInvestigador() != null) {
					c.add(Restrictions.eq("_usuarioInvestigador", pModelo
							.get_usuarioInvestigador()));
				}
				if (pModelo.get_fechaHora() != null) {
					c.add(Restrictions
							.eq("_fechaHora", pModelo.get_fechaHora()));
				}
			}
			listaModelo = (List<Modelo>) c.list();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaModelo;
	}

	public Modelo ObtenerModelo(Modelo pModelo) {
		Modelo unModelo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Modelo.class);
			if (pModelo != null) {
				if (pModelo.get_id() != 0) {
					c.add(Restrictions.eq("_id", pModelo.get_id()));
				}
				if (pModelo.get_escenario() != null) {
					if (pModelo.get_escenario().get_id() != 0) {
						c.add(Restrictions.eq("_escenario", pModelo
								.get_escenario()));
					}
					// else {
					// Criteria ce = session.createCriteria(Escenario.class);
					// if (pModelo.get_escenario().get_cultivo() != null) {
					// ce.add(Restrictions.eq("_cultivo", pModelo
					// .get_escenario().get_cultivo()));
					// }
					// if (pModelo.get_escenario().get_region() != null) {
					// ce.add(Restrictions.eq("_region", pModelo
					// .get_escenario().get_region()));
					// }
					// }
				}
				if (pModelo.get_usuarioInvestigador() != null) {
					c.add(Restrictions.eq("_usuarioInvestigador", pModelo
							.get_usuarioInvestigador()));
				}
				if (pModelo.get_fechaHora() != null) {
					c.add(Restrictions
							.eq("_fechaHora", pModelo.get_fechaHora()));
				}
//				c.setProjection(Projections.max("_fechaHora"));
			}
			unModelo = (Modelo) c.list().get(0);
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unModelo;
	}

	public Modelo RegistrarModelo(Modelo pModelo) {
		Modelo cultivo = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Long id = (Long) session.save("Modelo", pModelo);
			Criteria c = session.createCriteria(Modelo.class);
			c.add(Restrictions.eq("_id", id));
			cultivo = (Modelo) c.uniqueResult();
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return cultivo;
	}

	public void ActualizarModelo(Modelo pModelo) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Modelo", pModelo);
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

}