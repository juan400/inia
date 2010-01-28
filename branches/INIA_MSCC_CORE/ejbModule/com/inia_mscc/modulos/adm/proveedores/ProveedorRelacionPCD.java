package com.inia_mscc.modulos.adm.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.adm.servicios.ServicioRelacionPCD;

public class ProveedorRelacionPCD implements ServicioRelacionPCD {


	private ServicioRelacionPCD ejbRelacionPCD;

	public ProveedorRelacionPCD() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbRelacionPCD = (ServicioRelacionPCD) ctx.lookup("EJBRelacionPCD");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}
	
	@Override
	public Ciudad ObtenerCiudad(Ciudad pCiudad) {
		return ejbRelacionPCD.ObtenerCiudad(pCiudad);
	}

	@Override
	public List<Ciudad> ObtenerCiudades() {
		return ejbRelacionPCD.ObtenerCiudades();
	}

	@Override
	public Departamento ObtenerDepartamento(Departamento pDepartamento) {
		return ejbRelacionPCD.ObtenerDepartamento(pDepartamento);
	}

	@Override
	public List<Departamento> ObtenerDepartamentos() {
		return ejbRelacionPCD.ObtenerDepartamentos();
	}

	@Override
	public Pais ObtenerPais(Pais pPais) {
		return ejbRelacionPCD.ObtenerPais(pPais);
	}

	@Override
	public List<Pais> ObtenerPaises() {
		return ejbRelacionPCD.ObtenerPaises();
	}

	@Override
	public List<Departamento> ObtenerDepartamentosXPais(Pais pPais) {
		return ejbRelacionPCD.ObtenerDepartamentosXPais(pPais);
	}
	
	@Override
	public List<Ciudad> ObtenerCiudadesXDeptos(Departamento unDepto) {
		return ejbRelacionPCD.ObtenerCiudadesXDeptos(unDepto);
	}
}
