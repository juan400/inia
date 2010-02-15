package com.inia_mscc.modulos.gem.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.servicios.ServicioCultivo;

public class ProveedorCultivo implements ServicioCultivo {

	private ServicioCultivo ejbCultivo;

	public ProveedorCultivo() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbCultivo = (ServicioCultivo) ctx.lookup("EJBCultivo");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}
	
	@Override
	public void ActualizarCultivo(Cultivo pCultivo) {
		ejbCultivo.ActualizarCultivo(pCultivo);
		
	}

	@Override
	public Cultivo ObtenerCultivo(Cultivo pCultivo) {
		return ejbCultivo.ObtenerCultivo(pCultivo);
	}

	@Override
	public List<Cultivo> ObtenerCultivos(Cultivo pCultivo) {
		return ejbCultivo.ObtenerCultivos(pCultivo);
	}

	@Override
	public Cultivo RegistrarCultivo(Cultivo pCultivo) {
		return ejbCultivo.RegistrarCultivo(pCultivo);
	}

}
