package com.inia_mscc.servicios;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.negocio.comun.Casa;

public class CommonsProvider implements CommonsServices{

	private CommonsServices ejbCommons;
	
	
	public CommonsProvider() {
		try {
			Context ctx = new InitialContext();
			ejbCommons = (CommonsServices) ctx.lookup("EJBCommons");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}


	@Override
	public <T> T obtenerEntidad(Class<T> clazz) {
		return ejbCommons.obtenerEntidad(clazz);
	}
	
	public static void main(String[] args) {
		
		CommonsServices c = new CommonsProvider();
		Casa casa = c.obtenerEntidad(Casa.class);
		System.out.println(casa.getPuerta());
		System.out.println(casa.getVentana());
		
	}

	
	
}
