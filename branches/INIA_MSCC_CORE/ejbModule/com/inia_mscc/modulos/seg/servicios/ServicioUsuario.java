package com.inia_mscc.modulos.seg.servicios;

import com.inia_mscc.modulos.seg.entidades.Usuario;

public interface ServicioUsuario {
	
	public Usuario login(String login, String password);

}
