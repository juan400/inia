package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.gem.entidades.Modelo;


public interface ServicioModelo {

	public List<Modelo> ObtenerModelos(Modelo pModelo);

	public Modelo ObtenerModelo(Modelo pModelo);

	public Modelo RegistrarModelo(Modelo pModelo) throws Exception;

	public void ActualizarModelo(Modelo pModelo) throws Exception;
	
}
