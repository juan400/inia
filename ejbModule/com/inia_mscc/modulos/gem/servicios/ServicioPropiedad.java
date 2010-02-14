package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.gem.entidades.Propiedad;


public interface ServicioPropiedad {

	public List<Propiedad> ObtenerPropiedades(Propiedad pPropiedad);
	public Propiedad ObtenerPropiedad(Propiedad pPropiedad);
	public void ActualizarPropiedad(Propiedad pPropiedad);
	
}