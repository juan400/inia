package com.inia_mscc.modulos.seg.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.seg.dao.DAOUsuario;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

@Stateless(name = "EJBUsuario", mappedName = "EJBUsuario")
@Remote(ServicioUsuario.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBUsuario implements ServicioUsuario {

	private DAOUsuario dao = new DAOUsuario();

	@Override
	public void ActualizarDatos(DatoUsuario pDatosUsuario) {
		dao.ActualizarDatos(pDatosUsuario);
	}

	@Override
	public void CambiarPassword(Usuario pUsuario) {
		dao.CambiarPassword(pUsuario);
	}

	@Override
	public Usuario ComprobarClaveReigstro(String pClave) {
		return dao.ComprobarClaveReigstro(pClave);
	}

	@Override
	public Usuario RegistrarUsuario(Usuario pUsuario) {
		return dao.RegistrarUsuario(pUsuario);
	}

	@Override
	public Usuario Login(String pLogin, String pPassword) {
		return dao.Login(pLogin, pPassword);
	}
}
