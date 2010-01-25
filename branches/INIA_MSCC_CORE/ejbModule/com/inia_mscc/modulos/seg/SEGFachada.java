package com.inia_mscc.modulos.seg;

import java.io.IOException;

import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
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

	public void ActualizarDatos(DatoUsuario pDatosUsuario) {
		srvUsuario.ActualizarDatos(pDatosUsuario);
	}

	public void CambiarPassword(Usuario pUsuario) {
		srvUsuario.CambiarPassword(pUsuario);
	}

	public Boolean ComprobarClaveReigstro(String pClave) {
		return srvUsuario.ComprobarClaveReigstro(pClave);
	}

	public Usuario RegistrarUsuario(Usuario pUsuario) {
		return srvUsuario.RegistrarUsuario(pUsuario);
	}

	public Usuario Login(String pLogin, String pPassword) {
		return srvUsuario.Login(pLogin, pPassword);
	}

}
