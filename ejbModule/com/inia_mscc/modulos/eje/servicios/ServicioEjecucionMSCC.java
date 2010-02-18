package com.inia_mscc.modulos.eje.servicios;

import java.util.ArrayList;
import java.util.Map;

import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;

public interface ServicioEjecucionMSCC {
	
	public void generarArchivoEscenario(EjecucionMSCC ejecucionMSCC) throws Exception;

	public Map<String, ArrayList> obtenerMapaResultado(ResultadoMSCC resultadoMSCC) throws Exception;
}
