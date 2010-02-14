package com.inia_mscc.modulos.gem;

import java.io.IOException;
import java.util.List;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.proveedores.ProveedorCultivo;
import com.inia_mscc.modulos.gem.proveedores.ProveedorPropiedad;
import com.inia_mscc.modulos.gem.servicios.ServicioCultivo;
import com.inia_mscc.modulos.gem.servicios.ServicioPropiedad;

public class GEMFachada {
	private ServicioCultivo srvCultivo;
	private ServicioPropiedad srvPropiedad;
	
	public GEMFachada(Enumerados.ServicioGEM servicio) {
		try {
			switch (servicio) {
			case Cultivo:
				srvCultivo = new ProveedorCultivo();
				break;
			case Propiedad:
				srvPropiedad = new ProveedorPropiedad();	
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ActualizarCultivo(Cultivo pCultivo) {
		srvCultivo.ActualizarCultivo(pCultivo);
		
	}

	public Cultivo ObtenerCultivo(Cultivo pCultivo) {
		return srvCultivo.ObtenerCultivo(pCultivo);
	}

	public List<Cultivo> ObtenerCultivoes(Propiedad pPropiedad) {
		return srvCultivo.ObtenerCultivoes(pPropiedad);
	}

	public Cultivo RegistrarCultivo(Cultivo pCultivo) {
		return srvCultivo.RegistrarCultivo(pCultivo);
	}
	
	public void ActualizarPropiedad(Propiedad pPropiedad) {
		srvPropiedad.ActualizarPropiedad(pPropiedad);
	}

	
	public Propiedad ObtenerPropiedad(Propiedad pPropiedad) {
		return srvPropiedad.ObtenerPropiedad(pPropiedad);
	}

	
	public List<Propiedad> ObtenerPropiedades(Propiedad pPropiedad) {
		return srvPropiedad.ObtenerPropiedades(pPropiedad);
	}
}