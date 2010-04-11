package com.inia_mscc.modulos.eje.ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.adm.dao.DAOUbicacion;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioADM;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;
import com.inia_mscc.modulos.gem.entidades.ArchivosTexto;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.pyhton.EjecutarPyhton;

@Stateless(name = "EJBEjecucionMSCC", mappedName = "EJBEjecucionMSCC")
@Remote(ServicioEjecucionMSCC.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBEjecucionMSCC implements ServicioEjecucionMSCC {

	private static final String CARACTER_DE_SEPARACION = ",";
	private DAOUbicacion daoUbicacion = new DAOUbicacion();

	@Override
	public void generarArchivoEscenario(EjecucionMSCC ejecucionMSCC)
			throws Exception {
		try {
			Ubicacion ubicacionEJE = new Ubicacion();
			ubicacionEJE.set_tipoArchivo(TipoArchivo.Ejecucion);
			ubicacionEJE = daoUbicacion.ObtenerUbicacion(ubicacionEJE);
			if (ubicacionEJE == null) {
				throw new Exception(
						"No hay definida una ubicación para los archivos de ejecución.");
			}
			// File templateCultivo = ejecucionMSCC.get_modelo().get_escenario()
			// .get_archivoEscenario().get_datos();

			// Creamos el nombre de un directorio mediante un objeto File
			// El directorio raiz debe de existir -- mkdir --
			// String[] nombreTemporal =
			// ejecucionMSCC.get_archivoEjecucion().get_nombre().split(".py");
			String nombreDirectorioTemporal = ejecucionMSCC
					.get_archivoEjecucion().get_nombre();
			nombreDirectorioTemporal = ejecucionMSCC.get_archivoEjecucion()
					.get_ubicacion().get_urlPaht()
					+ "/" + nombreDirectorioTemporal.replace(".py", "");
			File directorioTemporal = new File(nombreDirectorioTemporal);
			// File directorioTemporal = new
			// File("c:\\ArchivosSubidos\\directorio");
			if (!directorioTemporal.mkdir()) {
				throw new Exception("No es posible crear el "
						+ "directorio temporal para la ejecución.");
			} else if (!directorioTemporal.isDirectory()) {
				throw new Exception("No es posible crear el "
						+ "directorio temporal para la ejecución.");
			}

			// Copiamos el gcros_for
			// TODO Hacer la validacion para win 32 o 64
			ejecucionMSCC.get_modelo().get_archivoMSCC().set_datos(
					new File(ubicacionEJE.get_urlPaht() + "/gecros_for.so"));
			File gcrosTemporal = new File(nombreDirectorioTemporal
					+ "/gecros_for.so");
			ArchivosTexto.copiarArchio(ejecucionMSCC.get_modelo()
					.get_archivoMSCC().get_datos(), gcrosTemporal);
			// Copiamos el weather_data
			ejecucionMSCC.get_modelo().get_archivoMSCC().set_datos(
					new File(ubicacionEJE.get_urlPaht() + "/weather_data.txt"));
			File weatherTemporal = new File(nombreDirectorioTemporal
					+ "/weather_data.txt");
			ArchivosTexto.copiarArchio(ejecucionMSCC.get_modelo()
					.get_archivoMSCC().get_datos(), weatherTemporal);
			// Copiamos el mscc
			ejecucionMSCC.get_modelo().get_archivoMSCC().set_datos(
					new File(ejecucionMSCC.get_modelo().get_archivoMSCC()
							.get_ubicacion().get_urlPaht()
							+ "/"
							+ ejecucionMSCC.get_modelo().get_archivoMSCC()
									.get_nombre()));
			File msccTemporal = new File(nombreDirectorioTemporal + "/mscc.py");
			ArchivosTexto.copiarArchio(ejecucionMSCC.get_modelo()
					.get_archivoMSCC().get_datos(), msccTemporal);
			// Copiamos el escenario cargado
			ejecucionMSCC.get_modelo().get_escenario().get_archivoEscenario()
					.set_datos(
							new File(ejecucionMSCC.get_modelo().get_escenario()
									.get_archivoEscenario().get_ubicacion()
									.get_urlPaht()
									+ "/"
									+ ejecucionMSCC.get_modelo()
											.get_escenario()
											.get_archivoEscenario()
											.get_nombre()));

			File escenarioTemporal = new File(nombreDirectorioTemporal
					+ "/escenario.py");

			// leer el escenario y lo carga
			BufferedReader fileIn = new BufferedReader(new FileReader(
					ejecucionMSCC.get_modelo().get_escenario()
							.get_archivoEscenario().get_ubicacion()
							.get_urlPaht()
							+ "/"
							+ ejecucionMSCC.get_modelo().get_escenario()
									.get_archivoEscenario().get_nombre()));
			PrintWriter fileOut = new PrintWriter(new FileWriter(
					escenarioTemporal, true));

			List<Propiedad> propiedades = ejecucionMSCC.get_modelo()
					.get_escenario().get_cultivo().get_listaPropiedades();
			if (propiedades == null) {
				throw new Exception("El cultivo no tiene propiedades.");
			}
			String linea = "";
			while ((linea = fileIn.readLine()) != null) {

				for (Propiedad propiedad : propiedades) {
					String cadenaParametro = "$(" + propiedad.get_codigo()
							+ ")";
					linea = linea.replace(cadenaParametro, propiedad
							.get_valor());
				}

				fileOut.println(linea);
			}
			fileOut.close();
			fileIn.close();

			// Copiamos el archivo escenario dentro del archivo de ejecucion
			ejecucionMSCC.get_archivoEjecucion()
					.set_datos(
							new File(ejecucionMSCC.get_archivoEjecucion()
									.get_ubicacion().get_urlPaht()
									+ "/"
									+ ejecucionMSCC.get_archivoEjecucion()
											.get_nombre()));
			ArchivosTexto.copiarArchio(escenarioTemporal, ejecucionMSCC
					.get_archivoEjecucion().get_datos());

			// Ejecutar el python
			String comandoPyhton = nombreDirectorioTemporal + "/mscc.py";
			ArrayList<String> parametros = new ArrayList<String>();
			parametros.add(nombreDirectorioTemporal + "/escenario.py");
			EjecutarPyhton ejecutar = new EjecutarPyhton(comandoPyhton,
					parametros);
			
			//ejecutar.ejecutarPython();
			ejecutar.ejecutarComando();
			
			
			// Borrar el directorio y todos sus archivos
			File[] ficheros = directorioTemporal.listFiles();

			for (int x = 0; x < ficheros.length; x++) {
				// leer el ouput y copiarlo

				// para borrar todo recursivamente hacer una funcion
				// borrarDirectorio
				// if (ficheros[x].isDirectory()) {
				// borrarDirectorio(ficheros[x]);
				// }
				// ficheros[x].delete();
			}
			if (!directorioTemporal.delete()) {
				throw new Exception("El directorio temporal "
						+ directorioTemporal + " ha sido borrado correctamente");
			}

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			throw new Exception("No se encuentra el archivo");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"Ha ocurrido un error al procesar el archivo de ejecuci�n.");
		}

	}

	@Override
	public Map<String, ArrayList> obtenerMapaResultado(
			ResultadoMSCC resultadoMSCC) throws Exception {
		Map<String, ArrayList> resultado = null;
		if (resultadoMSCC != null && resultadoMSCC.get_archivo() != null) {

			File archivoResultado = resultadoMSCC.get_archivo().get_datos();
			BufferedReader fileIn = new BufferedReader(new FileReader(
					archivoResultado));

			resultado = new HashMap<String, ArrayList>();

			String linea = "";
			int numeroLinea = 0;
			ArrayList referencia = new ArrayList();
			while ((linea = fileIn.readLine()) != null) {
				String[] columnas = linea.split(CARACTER_DE_SEPARACION);
				for (int i = 0; i < columnas.length; i++) {
					String valor = columnas[i];
					if (numeroLinea == 0) {
						referencia.add(valor);
						ArrayList columnaTabla = new ArrayList();
						resultado.put(valor, columnaTabla);
					} else {
						ArrayList columnaActual = resultado.get(referencia
								.get(i));
						if (isNumeric(valor)) {
							columnaActual.add(Double.valueOf(valor));
						} else {
							columnaActual.add(null);
						}
					}

				}

				numeroLinea++;
			}

		} else {
			throw new Exception(
					"Error al procesar el resultado de la ejecuci�n");
		}

		return resultado;
	}

	private static boolean isNumeric(String cadena) {
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
