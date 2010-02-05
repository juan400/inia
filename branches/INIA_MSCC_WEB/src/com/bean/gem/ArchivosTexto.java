package com.bean.gem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * * Contiene un grupo de metodos que nos pueden ser utiles al utilizar archivos
 * * de texto * * @author Magus
 */
public abstract class ArchivosTexto {
	/**
	 * * Lee el archivo de texto caracter por caracter * * @param f Un objeto
	 * File con el archivo que se desea leer * @throws
	 * java.io.FileNotFoundException En caso de que el archivo no exista * el
	 * metodo lanza esta excepcion * @throws java.io.IOException En caso de que
	 * el archivo no se pueda leer se * lanza esta excepcion
	 */
	public static void readChars(File f) throws FileNotFoundException,
			IOException {
		try {
			/** Creo un BufferedReader */
			BufferedReader fileIn = new BufferedReader(new FileReader(f));
			/**
			 * * Creo una variable temporal que va a guardar si hay fin de
			 * archivo. * La variable debe de ser de tipo int
			 */
			int read = 0;
			while (read != -1) {
				/** Leo un nuevo caracter del BufferedReader */
				read = fileIn.read();
				/** Guardo el caracter en una variable char */
				char c = (char) read;
				/** TODO Usar el caracter para algo */
				/**
				 * * Por ejemplo, para contar vocales: * if (c == 'a' || c ==
				 * 'e' || c == 'i' || c == 'o' || c == 'u') { * vocales++; * }
				 */
			}
			/** Cierro el archivo */
			fileIn.close();
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		}
	}

	/**
	 * * Lee el archivo por lineas * * @param f Un objeto File con el archivo
	 * que se desea leer * @throws java.io.FileNotFoundException En caso de que
	 * el archivo no exista * el metodo lanza esta excepcion * @throws
	 * java.io.IOException En caso de que el archivo no se pueda leer se * lanza
	 * esta excepcion
	 */
	public static void readLines(File f) throws FileNotFoundException,
			IOException {
		try {
			BufferedReader fileIn = new BufferedReader(new FileReader(f));
			String line = "";
			while ((line = fileIn.readLine()) != null) {
				/** TODO Usar la linea para algo */
				/**
				 * * Por ejemplo, para sacar un valor después del igual (muy
				 * usado en * archivos de configuracion) * String setting =
				 * line.substring(line.indexOf('='));
				 */
			}
			/** Cierro el archivo */
			fileIn.close();
		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		}
	}

	/**
	 * * Guarda el String pasado como parametro a un archivo sobreescribiendo
	 * todo * lo que tenia antes * * @param f Un objeto File con el archivo al
	 * que se desea guardar * @param s El String a guardar * @throws
	 * java.io.IOException En caso de que el archivo no se pueda * escribir se
	 * lanza esta excepcion
	 */
	public static void saveString(File f, String s) throws IOException {
		try {
			/** Abro el archivo */
			PrintWriter fileOut = new PrintWriter(new FileWriter(f));
			/** Guardo la linea */
			fileOut.println(s);
			/** Cierro el archivo */
			fileOut.close();
		} catch (IOException ex) {
			throw ex;
		}
	}

	/**
	 * * Guarda el String pasado como parametro al final del archivo pasado como
	 * * parametro * @param f Un objeto File con el archivo al que se desea
	 * guardar * @param s El String a guardar * @throws java.io.IOException En
	 * caso de que el archivo no se pueda * escribir se lanza esta excepcion
	 */
	public static void appendString(File f, String s) throws IOException {
		try {
			/**
			 * * Abro el archivo, fijate que ahora puse un valor booleano como
			 * el * segundo parametro del FileWriter, eso quiere decir si va a
			 * ser * para append o no
			 */
			PrintWriter fileOut = new PrintWriter(new FileWriter(f, true));
			/** Guardo la linea */
			fileOut.println(s);
			/**
			 * * Aqui puedo seguir guardando y guardando mas lineas o caracteres
			 * * hasta que el archivo contenga lo que me interesa
			 */
			/** Cierro el archivo */
			fileOut.close();
		} catch (IOException ex) {
			throw ex;
		}
	}
}