package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.gem.entidades.Cultivo;

public interface ServicioCultivo {

	public List<Cultivo> ObtenerCultivos(Cultivo pCultivo);
	public Cultivo ObtenerCultivo(Cultivo pCultivo);
	public Cultivo RegistrarCultivo(Cultivo pCultivo);
	public void ActualizarCultivo(Cultivo pCultivo);
	
}
