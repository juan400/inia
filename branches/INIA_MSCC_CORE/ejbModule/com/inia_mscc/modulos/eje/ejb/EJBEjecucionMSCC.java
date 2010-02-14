package com.inia_mscc.modulos.eje.ejb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;
import com.inia_mscc.modulos.gem.entidades.Propiedad;

public class EJBEjecucionMSCC implements ServicioEjecucionMSCC{

	@Override
	public void generarArchivoEscenario(EjecucionMSCC ejecucionMSCC) throws Exception {
		try{
			File templateCultivo = ejecucionMSCC.get_escenario().get_archivoMSCC().get_datos();
			File archivoEjecucion = ejecucionMSCC.get_archivoEjecucion().get_datos();
			
			BufferedReader fileIn = new BufferedReader(new FileReader(templateCultivo));
			PrintWriter fileOut = new PrintWriter(new FileWriter(archivoEjecucion, true));
			
			List<Propiedad> propiedades = ejecucionMSCC.get_escenario().get_cultivo().get_listaPropiedades();
			
			String linea = "";
			while ((linea = fileIn.readLine()) != null) {
				
				for (Propiedad propiedad : propiedades) {
					String cadenaParametro = "$(" + propiedad.get_codigo() + ")"; 
					linea.replace(cadenaParametro, propiedad.get_valor());
				}
				
				fileOut.println(linea.trim());
			}
			fileOut.close();
			fileIn.close();
		
		}catch (FileNotFoundException fe){
			fe.printStackTrace();
			throw new Exception("No se encuentra el archivo");
		}catch(Exception e){
			throw new Exception("Ha ocurrido un error al procesar el archivo de ejecución.");
		}
		
	}

}
