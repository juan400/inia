package com.inia_mscc.modulos.eje.ejb;

import java.util.List;

import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;
import com.inia_mscc.modulos.gem.entidades.Propiedad;

public class EJBEjecucionMSCC implements ServicioEjecucionMSCC{

	@Override
	public void generarArchivoEscenario(EjecucionMSCC ejecucionMSCC) throws Exception {
		try{
			List<Propiedad> propiedades = ejecucionMSCC.get_escenario().get_cultivo().get_listaPropiedades();
			
			for (Propiedad propiedad : propiedades) {
				
			}
			
		}catch(Exception e){
			throw new Exception("Ha ocurrido un error al procesar el archivo de ejecución.");
		}
		
	}

}
