package com.inia_mscc.modulos.adm;

import java.io.IOException;
import java.util.List;

import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.adm.proveedores.ProveedorRelacionPCD;
import com.inia_mscc.modulos.adm.servicios.ServicioRelacionPCD;
import com.inia_mscc.modulos.comun.entidades.Enumerados;

public class ADMFachada {

	private ServicioRelacionPCD srvRelacionPCD;
	
	public ADMFachada(Enumerados.Servicio servicio) {
		try {
			switch (servicio) {
			case RelacionPCD:
				srvRelacionPCD = new ProveedorRelacionPCD();
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Ciudad ObtenerCiudad(Ciudad pCiudad) {
		return srvRelacionPCD.ObtenerCiudad(pCiudad);
	}

	public List<Ciudad> ObtenerCiudades() {
		return srvRelacionPCD.ObtenerCiudades();
	}

	public Departamento ObtenerDepartamento(Departamento pDepartamento) {
		return srvRelacionPCD.ObtenerDepartamento(pDepartamento);
	}

	public List<Departamento> ObtenerDepartamentos() {
		return srvRelacionPCD.ObtenerDepartamentos();
	}

	public Pais ObtenerPais(Pais pPais) {
		return srvRelacionPCD.ObtenerPais(pPais);
	}

	public List<Pais> ObtenerPaises() {
		return srvRelacionPCD.ObtenerPaises();
	}

	
}
