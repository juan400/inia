package com.inia_mscc.modulos.seg.proveedores;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

public class ProveedorUsuario implements ServicioUsuario {

	private ServicioUsuario ejbUsuario;

	public ProveedorUsuario() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbUsuario = (ServicioUsuario) ctx.lookup("EJBUsuario");
		} 
		catch (NamingException e) {
			throw new com.inia_mscc.excepciones.ProviderException(e);
		}
	}
	
	@Override
	public Usuario login(String login, String password) {
		return ejbUsuario.login(login, password);
	}
}
