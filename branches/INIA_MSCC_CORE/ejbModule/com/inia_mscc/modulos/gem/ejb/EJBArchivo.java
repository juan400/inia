package com.inia_mscc.modulos.gem.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOArchivo;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.servicios.ServicioArchivo;

@Stateless(name = "EJBArchivo", mappedName = "EJBArchivo")
@Remote(ServicioArchivo.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBArchivo implements ServicioArchivo {

	private DAOArchivo dao = new DAOArchivo();

	@Override
	public void ActualizarArchivo(Archivo pArchivo) {
		dao.ActualizarArchivo(pArchivo);
	}

	@Override
	public Archivo ObtenerArchivo(Archivo pArchivo) {
		return dao.ObtenerArchivo(pArchivo);
	}

	@Override
	public List<Archivo> ObtenerArchivos(Archivo pArchivo) {
		return dao.ObtenerArchivos(pArchivo);
	}

	@Override
	public Archivo RegistrarArchivo(Archivo pArchivo) throws Exception {
		Archivo archivo = null;
		archivo = dao.RegistrarArchivo(pArchivo);
		if (archivo==null){
			throw new Exception("No se puedo subir el archivo");
		}
//		File file = new File(pArchivo.get_ubicacion().get_urlPaht() +"/"+ pArchivo.get_nombre());
//		file.setExecutable(true);
//		if (!file.createNewFile()) {
//			throw new Exception("Se a subido el archivo");
//		}
//		ArchivosTexto.copiarArchio(pArchivo.get_datos(), file);
//		pArchivo.set_datos(file);
//		archivo = dao.RegistrarArchivo(pArchivo);
		return archivo;
	}

}

// /**
// * * Lee el archivo de texto caracter por caracter * * @param f Un objeto
// * File con el archivo que se desea leer * @throws
// * java.io.FileNotFoundException En caso de que el archivo no exista * el
// * metodo lanza esta excepcion * @throws java.io.IOException En caso de que
// * el archivo no se pueda leer se * lanza esta excepcion
// */
// public static void readChars(File f) throws FileNotFoundException,
// IOException {
// try {
// /** Creo un BufferedReader */
// BufferedReader fileIn = new BufferedReader(new FileReader(f));
// /**
// * * Creo una variable temporal que va a guardar si hay fin de
// * archivo. * La variable debe de ser de tipo int
// */
// int read = 0;
// while (read != -1) {
// /** Leo un nuevo caracter del BufferedReader */
// read = fileIn.read();
// /** Guardo el caracter en una variable char */
// char c = (char) read;
// /** TODO Usar el caracter para algo */
// /**
// * * Por ejemplo, para contar vocales: * if (c == 'a' || c ==
// * 'e' || c == 'i' || c == 'o' || c == 'u') { * vocales++; * }
// */
// }
// /** Cierro el archivo */
// fileIn.close();
// } catch (FileNotFoundException ex) {
// throw ex;
// } catch (IOException ex) {
// throw ex;
// }
// }
//
// /**
// * * Lee el archivo por lineas * * @param f Un objeto File con el archivo
// * que se desea leer * @throws java.io.FileNotFoundException En caso de que
// * el archivo no exista * el metodo lanza esta excepcion * @throws
// * java.io.IOException En caso de que el archivo no se pueda leer se * lanza
// * esta excepcion
// */
// public static ArrayList<String> readLines(File f) throws
// FileNotFoundException,
// IOException {
// ArrayList<String> SArchivo=new ArrayList<String>();
// try {
// BufferedReader fileIn = new BufferedReader(new FileReader(f));
// String linea = "";
// while ((linea = fileIn.readLine()) != null) {
// /** TODO Usar la linea para algo */
// /**
// * * Por ejemplo, para sacar un valor después del igual (muy
// * usado en * archivos de configuracion) * String setting =
// * linea.substring(linea.indexOf('='));
// */
// SArchivo.add(linea.trim());
// }
// /** Cierro el archivo */
// fileIn.close();
// } catch (FileNotFoundException ex) {
// throw ex;
// } catch (IOException ex) {
// throw ex;
// }
// return null;
// }
//
// /**
// * * Guarda el String pasado como parametro a un archivo sobreescribiendo
// * todo * lo que tenia antes * * @param f Un objeto File con el archivo al
// * que se desea guardar * @param s El String a guardar * @throws
// * java.io.IOException En caso de que el archivo no se pueda * escribir se
// * lanza esta excepcion
// */
// public static void saveString(File f, ArrayList<String> s) throws IOException
// {
// try {
// /** Abro el archivo */
// PrintWriter fileOut = new PrintWriter(new FileWriter(f));
// /** Guardo la linea */
// for (String linea : s) {
// fileOut.println(linea);
// }
// // for (int i = 0; i < s.; i++) {
// // fileOut.println(s[i]);
// // }
// /** Cierro el archivo */
// fileOut.close();
// } catch (IOException ex) {
// throw ex;
// }
// }
//
// /**
// * * Guarda el String pasado como parametro al final del archivo pasado como
// * * parametro * @param f Un objeto File con el archivo al que se desea
// * guardar * @param s El String a guardar * @throws java.io.IOException En
// * caso de que el archivo no se pueda * escribir se lanza esta excepcion
// */
// public static void appendString(File f, ArrayList<String> s) throws
// IOException {
// try {
// /**
// * * Abro el archivo, fijate que ahora puse un valor booleano como
// * el * segundo parametro del FileWriter, eso quiere decir si va a
// * ser * para append o no
// */
// PrintWriter fileOut = new PrintWriter(new FileWriter(f, true));
// /** Guardo la linea */
// for (String linea : s) {
// fileOut.println(linea);
// }
// // for (int i = 0; i < s.length; i++) {
// // fileOut.println(s[i]);
// // }
// /**
// * * Aqui puedo seguir guardando y guardando mas lineas o caracteres
// * * hasta que el archivo contenga lo que me interesa
// */
// /** Cierro el archivo */
// fileOut.close();
// } catch (IOException ex) {
// throw ex;
// }
// }
// */