package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;

public interface ServicioCultivo {

	public List<Cultivo> ObtenerCultivoes(Propiedad pPropiedad);
	public Cultivo ObtenerCultivo(Cultivo pCultivo);
	public Cultivo RegistrarCultivo(Cultivo pCultivo);
	public void ActualizarCultivo(Cultivo pCultivo);
	
}
