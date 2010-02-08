package com.inia_mscc.modulos.seg.servicios;

import java.util.Date;

import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public interface ServicioUsuario {
	public void ActualizarUltimoAccesoUsuario(Usuario pUsuario);
	public void DarBajaBloquearUsuario(Usuario pUsuario);
	public Usuario RegistrarUsuario(Usuario pUsuario) throws Exception;
	public void ActualizarDatosUsuario(DatoUsuario pDatosUsuario);
	public Usuario ComprobarClaveReigstro(String pClave);
	public void CambiarPassword(Usuario pUsuario);
	public Usuario Login(String pLogin, String pPassword);
	public boolean ComprobarEmail(String pEmail);
	public Usuario ObtenerUsuarioXDatos(String pLoginName, Date pUltimoAcceso,
			Perfil pPerfil, String pEmail, String pFrase);
	
}
