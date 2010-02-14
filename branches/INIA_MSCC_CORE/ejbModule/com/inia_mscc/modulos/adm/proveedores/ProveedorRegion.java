package com.inia_mscc.modulos.adm.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.adm.servicios.ServicioRegion;

public class ProveedorRegion implements ServicioRegion {

	private ServicioRegion ejbRegion;

	public ProveedorRegion() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbRegion = (ServicioRegion) ctx.lookup("EJBRegion");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public Region RegistrarRegion(Region pRegion) {
		return ejbRegion.RegistrarRegion(pRegion);
	}

	@Override
	public void ActualizarRegion(Region pRegion) {
		ejbRegion.ActualizarRegion(pRegion);
	}

	@Override
	public List<Region> ObtenerRegiones() {
		return ejbRegion.ObtenerRegiones();
	}

	@Override
	public Region ComprobarRegion(Region pRegion) {
		return ejbRegion.ComprobarRegion(pRegion);
	}
	
	@Override
	public Region ComprobarRegionCodigo(Region pRegion) {
		return ejbRegion.ComprobarRegionCodigo(pRegion);
	}

	@Override
	public void EliminarRegion(Region pRegion) {
		ejbRegion.EliminarRegion(pRegion);
	}
}