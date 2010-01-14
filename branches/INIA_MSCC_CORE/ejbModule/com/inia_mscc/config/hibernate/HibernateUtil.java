package com.inia_mscc.config.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.seg.entidades.Usuario;
//import com.inia_mscc.modulos.comun.entidades.Casa;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    
    static {
        try {
        	
        	SESSION_FACTORY = new AnnotationConfiguration()
//        	.addPackage("com.inia_mscc.modulos.adm.entidades")
//        	.addPackage("com.inia_mscc.modulos.comun.entidades")
//        	.addPackage("com.inia_mscc.modulos.eje.entidades")
//        	.addPackage("com.inia_mscc.modulos.gem.entidades")
//        	.addPackage("com.inia_mscc.modulos.lut.entidades")
//        	.addPackage("com.inia_mscc.modulos.seg.entidades.Usuario")
        	.addAnnotatedClass(Usuario.class)
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
