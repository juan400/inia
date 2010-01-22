package com.inia_mscc.modulos.seg.servicios;

import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public interface ServicioUsuario {
	
	public Usuario Login(String pLogin, String pPassword);
	
	public DatoUsuario RegistrarUsuario(DatoUsuario pUsuario);

}
