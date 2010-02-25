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

import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;
import com.inia_mscc.modulos.gem.entidades.Propiedad;

@Stateless(name = "EJBEjecucionMSCC", mappedName = "EJBEjecucionMSCC")
@Remote(ServicioEjecucionMSCC.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBEjecucionMSCC implements ServicioEjecucionMSCC{
	
	private static final String CARACTER_DE_SEPARACION = ","; 

	@Override
	public void generarArchivoEscenario(EjecucionMSCC ejecucionMSCC) throws Exception {
		try{
			File templateCultivo = ejecucionMSCC.get_escenario().get_archivoEscenario().get_datos();
			File archivoEjecucion = ejecucionMSCC.get_archivoEjecucion().get_datos();
			
			BufferedReader fileIn = new BufferedReader(new FileReader(templateCultivo));
			PrintWriter fileOut = new PrintWriter(new FileWriter(archivoEjecucion, true));
			
			List<Propiedad> propiedades = ejecucionMSCC.get_escenario().get_cultivo().get_listaPropiedades();
			
			String linea = "";
			while ((linea = fileIn.readLine()) != null) {
				
				for (Propiedad propiedad : propiedades) {
					String cadenaParametro = "$(" + propiedad.get_codigo() + ")"; 
					linea = linea.replace(cadenaParametro, propiedad.get_valor());
				}
				
				fileOut.println(linea);
			}
			fileOut.close();
			fileIn.close();
		
		}catch (FileNotFoundException fe){
			fe.printStackTrace();
			throw new Exception("No se encuentra el archivo");
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Ha ocurrido un error al procesar el archivo de ejecución.");
		}
		
	}

	@Override
	public Map<String, ArrayList> obtenerMapaResultado(ResultadoMSCC resultadoMSCC) throws Exception {
		Map<String, ArrayList> resultado = null;
		if(resultadoMSCC != null && resultadoMSCC.get_archivo() != null){
			
			File archivoResultado = resultadoMSCC.get_archivo().get_datos();
			BufferedReader fileIn = new BufferedReader(new FileReader(archivoResultado));
			
			resultado = new HashMap<String, ArrayList>();
			
			String linea = "";
			int numeroLinea = 0;
			ArrayList referencia = new ArrayList();
			while ((linea = fileIn.readLine()) != null) {
				String[] columnas = linea.split(CARACTER_DE_SEPARACION);
				for(int i=0; i<columnas.length; i++){
					String valor = columnas[i];
					if(numeroLinea == 0){
						referencia.add(valor);
						ArrayList columnaTabla = new ArrayList();
						resultado.put(valor, columnaTabla);
					}else{
						ArrayList columnaActual = resultado.get(referencia.get(i));
						if(isNumeric(valor)){
							columnaActual.add(Double.valueOf(valor));
						}else{
							columnaActual.add(null);
						}
					}
					
				}
	
				numeroLinea++;
			}
			
		}else{
			throw new Exception("Error al procesar el resultado de la ejecución");
		}
		
		return resultado;
	}

	private static boolean isNumeric(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
}
