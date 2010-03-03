package com.bean.adm;

/**
 * @file CrearDirectorio.java
 * @version 1.1
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date   16-julio-2007
 * @url    http://lineadecodigo.com/2007/07/16/crear-un-directorio-con-java-io/
 * @description Creación de directorios mediante la clase File
 */

import java.io.File;

public class CrearDirectorio {

	public static void main(String[] args) {

		// Creamos el nombre de un directorio mediante un objeto File
		   // El directorio raiz debe de existir -- mkdir --
		   File directorio = new File("c:\\ArchivosSubidos\\directorio");
		   if (!directorio.isDirectory()){
			   System.out.println("La ruta ingresada no es un directorio valido.");
		   }else{
			   System.out.println("Puede continuar");
		   }
		   
		   if (directorio.mkdir())
		     System.out.println("Se ha creado directorio");
		   else
		     System.out.println("No se ha podido crear el directorio");

		   // El directorio raiz no tiene pq existir -- mkdirs    --
		   File directorio2 = new File("c:\\ArchivosSubidos\\directorio\\lineadecodigo\\java_io\\hola");
		   if (directorio2.mkdirs())
		     System.out.println("Se ha creado directorio");
		   else
		     System.out.println("No se ha podido crear el directorio"); 
	}

}
