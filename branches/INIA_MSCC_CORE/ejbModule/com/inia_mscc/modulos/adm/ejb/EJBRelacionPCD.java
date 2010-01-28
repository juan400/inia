package com.inia_mscc.modulos.adm.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.adm.dao.DAOCiudad;
import com.inia_mscc.modulos.adm.dao.DAODepartamento;
import com.inia_mscc.modulos.adm.dao.DAOPais;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.adm.servicios.ServicioRelacionPCD;

@Stateless(name = "EJBRelacionPCD", mappedName = "EJBRelacionPCD")
@Remote(ServicioRelacionPCD.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBRelacionPCD implements ServicioRelacionPCD {

	private DAOCiudad daoCiudad = new DAOCiudad();
	private DAODepartamento daoDepto = new DAODepartamento();
	private DAOPais daoPais = new DAOPais();
	
	@Override
	public Ciudad ObtenerCiudad(Ciudad pCiudad) {
		return daoCiudad.ObtenerCiudad(pCiudad);
	}
	@Override
	public List<Ciudad> ObtenerCiudades() {
		return daoCiudad.ObtenerCiudades();
	}
	@Override
	public Departamento ObtenerDepartamento(Departamento pDepartamento) {
		return daoDepto.ObtenerDepartamento(pDepartamento);
	}
	@Override
	public List<Departamento> ObtenerDepartamentos() {
		return daoDepto.ObtenerDepartamentos();
	}
	@Override
	public List<Departamento> ObtenerDepartamentosXPais(Pais pPais) {
		return daoDepto.ObtenerDepartamentosXPais(pPais);
	}
	@Override
	public Pais ObtenerPais(Pais pPais) {
		return daoPais.ObtenerPais(pPais);
	}
	@Override
	public List<Pais> ObtenerPaises() {
		return daoPais.ObtenerPaises();
	}
	@Override
	public List<Ciudad> ObtenerCiudadesXDeptos(Departamento unDepto) {
		return daoCiudad.ObtenerCiudadesXDeptos(unDepto);
	}

}
