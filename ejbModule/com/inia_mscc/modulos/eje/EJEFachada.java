package com.inia_mscc.modulos.eje;

import java.io.IOException;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.proveedores.ProveedorEjecucion;
import com.inia_mscc.modulos.eje.servicios.ServicioEjecucionMSCC;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.proveedores.ProveedorPerfil;
import com.inia_mscc.modulos.seg.proveedores.ProveedorUsuario;

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
	
	public void generarArchivoEjecucion(EjecucionMSCC ejecucionMSCC) throws Exception{
		srvEjecucion.generarArchivoEscenario(ejecucionMSCC);
	}

}
