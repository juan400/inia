package com.inia_mscc.modulos.seg.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.seg.dao.DAOPerfil;
import com.inia_mscc.modulos.seg.dao.DAOUsuario;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

@Stateless(name = "EJBUsuario", mappedName = "EJBUsuario")
@Remote(ServicioUsuario.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBUsuario implements ServicioUsuario {

	private DAOUsuario dao = new DAOUsuario();

	@Override
	public void ActualizarUltimoAccesoUsuario(Usuario pUsuario){
		dao.ActualizarUltimoAcceso(pUsuario);
	}
	
	@Override
	public void ActualizarDatosUsuario(DatoUsuario pDatosUsuario) {
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
	public Usuario RegistrarUsuario(Usuario pUsuario) throws Exception {
		DAOPerfil daoPer = new DAOPerfil();
		Perfil perfilPublico = new Perfil();
		perfilPublico.set_nombre("Publico");
		perfilPublico = daoPer.ComprobarPerfil(perfilPublico);
		if (perfilPublico != null) {
			pUsuario.get_datos().set_perfil(perfilPublico);
			return dao.RegistrarUsuario(pUsuario);
		} else {
			throw new Exception("En ese momento no podemos generar el usuario porque no se ha gestionado el perfil público.");
		}
	}

	@Override
	public Usuario Login(String pLogin, String pPassword) {
		return dao.Login(pLogin, pPassword);
	}

	@Override
	public boolean ComprobarEmail(String pEmail) {
		return dao.ComprobarEmail(pEmail);
	}

	@Override
	public Usuario ObtenerUsuarioXDatos(String pLoginName, Date pUltimoAcceso,
			Perfil pPerfil, String pEmail, String pFrase) {
		return dao.ObtenerUsuarioXDatos(pLoginName, pUltimoAcceso, pPerfil, pEmail, pFrase);
	}

	@Override
	public void DarBajaBloquearUsuario(Usuario pUsuario) {
		dao.DarBajaBloquearUsuario(pUsuario);
	}
	
	@Override
	public void ActualizarUsuario(Usuario pUsuario) {
		dao.ActualizarUsuario(pUsuario);
	}

	@Override
	public List<Usuario> ObtenerUsuarios() {
		return dao.ObtenerUsuarios();
	}
	
}
