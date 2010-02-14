package com.inia_mscc.modulos.adm.servicios;

import java.util.List;

import com.inia_mscc.modulos.adm.entidades.Region;

public interface ServicioRegion {

	public Region RegistrarRegion(Region pRegion);
	public void ActualizarRegion(Region pRegion);
	public List<Region> ObtenerRegiones();
	public Region ComprobarRegion(Region pRegion);
	public Region ComprobarRegionCodigo(Region pRegion);
	public void EliminarRegion(Region pRegion);
}