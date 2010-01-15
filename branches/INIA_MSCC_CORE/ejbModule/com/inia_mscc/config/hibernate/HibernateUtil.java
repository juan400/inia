package com.inia_mscc.config.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.comun.entidades.Casa;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;
    /** 
     * Location of hibernate.cfg.xml file.
     * Location should be on the classpath as Hibernate uses  
     * #resourceAsStream style lookup for its configuration file. 
     * The default classpath location of the hibernate config file is 
     * in the default package. Use #setConfigFile() to update 
     * the location of the configuration file for the current session.   
     */
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static Configuration configuration = new Configuration();
    //private static SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    
    static {
        try {
        	
        	SESSION_FACTORY = new AnnotationConfiguration().configure(configFile).buildSessionFactory();
//        	.addPackage("com.inia_mscc.modulos.seg.entidades")
//        	.addAnnotatedClass(Usuario.class)
//        	.configure("hibernate.cfg.xml")
        	//.buildSessionFactory();
//        	configuration.configure(configFile);
//        	configuration.addClass(Usuario.class);
//        	SESSION_FACTORY = configuration.buildSessionFactory();
            
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
