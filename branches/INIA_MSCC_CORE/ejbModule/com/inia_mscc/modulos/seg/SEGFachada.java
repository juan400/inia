package com.inia_mscc.modulos.seg;


import java.io.IOException;

import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.proveedores.ProveedorUsuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

public class SEGFachada {
	private ServicioUsuario srvUsuario;

	public SEGFachada() {
		try {
			srvUsuario = new ProveedorUsuario();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Usuario login(String login, String password){		
		Usuario usuarioLogeado = srvUsuario.login(login, password);
		return usuarioLogeado;
	}
}
