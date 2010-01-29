package com.inia_mscc.modulos.seg.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.servicios.ServicioPerfil;

public class ProveedorPerfil implements ServicioPerfil {

	private ServicioPerfil ejbPerfil;

	public ProveedorPerfil() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbPerfil = (ServicioPerfil) ctx.lookup("EJBPerfil");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public Perfil RegistrarPerfil(Perfil pPerfil) {
		return ejbPerfil.RegistrarPerfil(pPerfil);
	}
	
	@Override
	public void ActualizarPerfil(Perfil pPerfil) {
		ejbPerfil.ActualizarPerfil(pPerfil);
		
	}
	
	@Override
	public Perfil ComprobarPerfil(Perfil pPerfil){
		return ejbPerfil.ComprobarPerfil(pPerfil);
	}

	@Override
	public List<Perfil> ObtenerPerfiles() {
		return ejbPerfil.ObtenerPerfiles();
	}
	
}