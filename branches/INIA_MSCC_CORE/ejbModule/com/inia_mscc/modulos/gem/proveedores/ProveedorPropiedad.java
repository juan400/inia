package com.inia_mscc.modulos.gem.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.servicios.ServicioPropiedad;

public class ProveedorPropiedad implements ServicioPropiedad {

	private ServicioPropiedad ejbPropiedad;

	public ProveedorPropiedad() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbPropiedad = (ServicioPropiedad) ctx.lookup("EJBPropiedad");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public void ActualizarPropiedad(Propiedad pPropiedad) {
		ejbPropiedad.ActualizarPropiedad(pPropiedad);
	}

	@Override
	public Propiedad ObtenerPropiedad(Propiedad pPropiedad) {
		return ejbPropiedad.ObtenerPropiedad(pPropiedad);
	}

	@Override
	public List<Propiedad> ObtenerPropiedades(Propiedad pPropiedad) {
		return ejbPropiedad.ObtenerPropiedades(pPropiedad);
	}

	@Override
	public void EliminarPropiedad(Propiedad pPropiedad) {
		ejbPropiedad.EliminarPropiedad(pPropiedad);
	}

	@Override
	public void EliminarPropiedades(List<Propiedad> pPropiedades) {
		ejbPropiedad.EliminarPropiedades(pPropiedades);
	}

}