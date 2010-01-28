package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;

public interface ServicioRelacionPCD {
	
	public List<Pais> ObtenerPaises();
	public Pais ObtenerPais(Pais pPais);
	public List<Departamento> ObtenerDepartamentos();
	public Departamento ObtenerDepartamento(Departamento pDepartamento);
	public List<Ciudad> ObtenerCiudades();
	public Ciudad ObtenerCiudad(Ciudad pCiudad);

}
