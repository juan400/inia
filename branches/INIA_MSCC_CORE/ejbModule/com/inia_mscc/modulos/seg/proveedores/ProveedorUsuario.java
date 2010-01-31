package com.inia_mscc.modulos.seg.proveedores;

import java.io.IOException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;
import com.inia_mscc.excepciones.ProviderException;

public class ProveedorUsuario implements ServicioUsuario {

	private ServicioUsuario ejbUsuario;

	public ProveedorUsuario() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbUsuario = (ServicioUsuario) ctx.lookup("EJBUsuario");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void ActualizarDatos(DatoUsuario pDatosUsuario) {
		ejbUsuario.ActualizarDatos(pDatosUsuario);
		
	}

	@Override
	public void CambiarPassword(Usuario pUsuario) {
		ejbUsuario.CambiarPassword(pUsuario);
		
	}

	@Override
	public Usuario ComprobarClaveReigstro(String pClave) {
		return ejbUsuario.ComprobarClaveReigstro(pClave);
	}

	@Override
	public Usuario RegistrarUsuario(Usuario pUsuario) throws Exception {
		return ejbUsuario.RegistrarUsuario(pUsuario);
	}
	
	@Override
	public Usuario Login(String pLogin, String pPassword) {
		return ejbUsuario.Login(pLogin, pPassword);
	}

	@Override
	public boolean ComprobarEmail(String pEmail) {
		return ejbUsuario.ComprobarEmail(pEmail);
	}

	@Override
	public Usuario ObtenerUsuarioXDatos(String pLoginName, Date pUltimoAcceso,
			Perfil pPerfil, String pEmail, String pFrase) {
		return ejbUsuario.ObtenerUsuarioXDatos(pLoginName, pUltimoAcceso, pPerfil, pEmail, pFrase);
	}
}
