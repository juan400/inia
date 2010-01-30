package com.inia_mscc.modulos.seg.ejb;

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
}
