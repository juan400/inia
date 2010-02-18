package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.gem.entidades.Archivo;

public interface ServicioArchivo {

	public List<Archivo> ObtenerArchivos(Archivo pArchivo);
	public Archivo ObtenerArchivo(Archivo pArchivo);
	public Archivo RegistrarArchivo(Archivo pArchivo) throws Exception;
	public void ActualizarArchivo(Archivo pArchivo);
	
}
