package com.inia_mscc.modulos.eje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;
import com.inia_mscc.modulos.eje.proveedores.ProveedorEjecucion;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;

public class EJEFachada {
	
	private ServicioEjecucionMSCC srvEjecucion;

	public EJEFachada(Enumerados.ServicioEJE servicio) {
		try {
			switch (servicio) {
			case Ejecucion:
				srvEjecucion = new ProveedorEjecucion();
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generarArchivoEjecucion(EjecucionMSCC pEjecucionMSCC) throws Exception{
		srvEjecucion.generarArchivoEscenario(pEjecucionMSCC);
	}

	public Map<String, ArrayList> obtenerMapaResultado(ResultadoMSCC pResultadoMSCC) throws Exception {
		return srvEjecucion.obtenerMapaResultado(pResultadoMSCC);
	}
}
