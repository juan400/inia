package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.Ubicacion;

public interface ServicioUbicacion {

	public List<Ubicacion> ObtenerUbicacions(Ubicacion pUbicacion);
	public Ubicacion ObtenerUbicacion(Ubicacion pUbicacion);
	public Ubicacion RegistrarUbicacion(Ubicacion pUbicacion);
	public void ActualizarUbicacion(Ubicacion pUbicacion);
	
}
