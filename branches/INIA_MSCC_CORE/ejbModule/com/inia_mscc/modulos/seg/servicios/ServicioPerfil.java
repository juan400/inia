package com.inia_mscc.modulos.seg.servicios;

import java.util.List;

import com.inia_mscc.modulos.seg.entidades.Perfil;

public interface ServicioPerfil {

	public Perfil RegistrarPerfil(Perfil pPerfil);

	public void ActualizarPerfil(Perfil pPerfil);

	public List<Perfil> ObtenerPerfiles();

	public Perfil ComprobarPerfil(Perfil pPerfil);

	public void EliminarPerfil(Perfil pPerfil) throws Throwable;
	
	public Perfil ObtenerPerfilConTransAsociadas(Perfil pPerfil);
	
}
