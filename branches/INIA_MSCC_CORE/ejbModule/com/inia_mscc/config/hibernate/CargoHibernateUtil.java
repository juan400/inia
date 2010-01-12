package com.inia_mscc.config.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.negocio.comun.entidades.Casa;
import com.inia_mscc.util.LoggingUtilities;

public class CargoHibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    private static Logger logger = Logger.getLogger(CargoHibernateUtil.class);
    
    static {
        try {
        	
        	SESSION_FACTORY = new AnnotationConfiguration()
        	.addPackage("com.inia_mscc.entidades")
//        	.addAnnotatedClass(Carpeta.class)
//        	.addAnnotatedClass(Empresa.class)
//        	.addAnnotatedClass(TipoEmpresa.class)
//        	.addAnnotatedClass(Origen.class)
//        	.addAnnotatedClass(Pais.class)
//        	.addAnnotatedClass(Barco.class)
//        	.addAnnotatedClass(Camion.class)
//        	.addAnnotatedClass(Capacidad.class)
//        	.addAnnotatedClass(Ciudad.class)
//        	.addAnnotatedClass(Identificacion.class)
//        	.addAnnotatedClass(Marca.class)
//        	.addAnnotatedClass(Moneda.class)
//        	.addAnnotatedClass(Propietario.class)
//        	.addAnnotatedClass(Responsable.class)
//        	.addAnnotatedClass(TipoCapacidad.class)
//        	.addAnnotatedClass(TipoIdentificacion.class)
//        	.addAnnotatedClass(TipoPropietario.class)
//        	.addAnnotatedClass(TipoTransporte.class)
        	.addAnnotatedClass(Casa.class)
        	.configure("hibernate.cfg.xml")
        	.buildSessionFactory();
            
        } catch (Throwable ex) {
            logger.fatal("Creaci�n de la SessionFactory fallida." + ex);
            String stackTrace = LoggingUtilities.obtenerStackTrace(ex);
            logger.fatal(stackTrace);
            throw new IniaPersistenciaException(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void createSchema() {
        Configuration cfg = new Configuration().configure();
        SchemaExport schemaExport = new SchemaExport(cfg);
        schemaExport.create(true, true);
    }
    

}
