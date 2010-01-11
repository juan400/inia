package com.inia_mscc.entidades.eje;

import java.util.Date;

import com.inia_mscc.entidades.comun.Objeto;
import com.inia_mscc.entidades.gem.Archivo;
import com.inia_mscc.entidades.seg.Usuario;

public class EjecucionMSCC extends Objeto {

	private Date _fechaHora;
	private Usuario _usuarioEjecutante;
	private Archivo _archivoEjecucion;
	private Archivo _archivoEscenario;
	private Archivo _archivoResultado;
	private ResultadoMSCC _resultaroMSCC;
}
