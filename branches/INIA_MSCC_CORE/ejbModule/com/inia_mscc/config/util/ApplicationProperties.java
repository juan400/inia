package com.inia_mscc.config.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase encargada de leer el archivo de propiedades de la aplicación
 * @author Administrador
 *
 */
public class ApplicationProperties {
	
	//Contiene la direccion del archivo de propiedades
	public static final String DIR_ARCHIVO_PROPIEDADES = "/propiedades.properties";
	
	public static String obtenerPropiedad(String nombrePropieddad){
		String resultado = null;
		Properties props = new Properties();
		
		try {
			if(nombrePropieddad != null && !nombrePropieddad.equals("")){
				props.load(Thread.currentThread().getContextClassLoader().getResource(DIR_ARCHIVO_PROPIEDADES).openStream());
				resultado = props.getProperty(nombrePropieddad);
			}
		}
		//El archivo de propiedades no se encuentra 
		catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		return resultado;
	}
}
