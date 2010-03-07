package com.inia_mscc.modulos.gem.servicios;

import java.util.List;

import com.inia_mscc.modulos.gem.entidades.Escenario;

public interface ServicioEscenario {

	public List<Escenario> ObtenerEscenarios(Escenario pEscenario);

	public Escenario ObtenerEscenario(Escenario pEscenario);

	public Escenario RegistrarEscenario(Escenario pEscenario) throws Exception;

	public void ActualizarEscenario(Escenario pEscenario) throws Exception;

}
