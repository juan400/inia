package com.inia_mscc.modulos.seg.servicios;

import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public interface ServicioUsuario {
	
	public Usuario RegistrarUsuario(Usuario pUsuario);
	public void ActualizarDatos(DatoUsuario pDatosUsuario);
	public Boolean ComprobarClaveReigstro(String pClave);
	public void CambiarPassword(Usuario pUsuario);
	public Usuario Login(String pLogin, String pPassword);

}
