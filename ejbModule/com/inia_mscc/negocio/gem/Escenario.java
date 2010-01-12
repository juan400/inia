package com.inia_mscc.negocio.gem;

import java.util.Date;

import com.inia_mscc.negocio.adm.entidades.Region;
import com.inia_mscc.negocio.comun.entidades.Objeto;
import com.inia_mscc.negocio.seg.Usuario;

public class Escenario extends Objeto {
	
	private Date _fechaHora;
	private Usuario _usuarioInvestigador;
	private Region _region;
	private Cultivo _cultivo;
	private Archivo _archivoEscenario;
	private Archivo _archivoMSCC;
	
	public Escenario() {
		super();
		_fechaHora = new Date();
		_usuarioInvestigador = null;
		_region = null;
		_cultivo = null;
		_archivoEscenario = null;
		_archivoMSCC = null;
	}

	public Date get_fechaHora() {
		return _fechaHora;
	}

	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}

	public Usuario get_usuarioInvestigador() {
		return _usuarioInvestigador;
	}

	public void set_usuarioInvestigador(Usuario usuarioInvestigador) {
		_usuarioInvestigador = usuarioInvestigador;
	}

	public Region get_region() {
		return _region;
	}

	public void set_region(Region region) {
		_region = region;
	}

	public Cultivo get_cultivo() {
		return _cultivo;
	}

	public void set_cultivo(Cultivo cultivo) {
		_cultivo = cultivo;
	}

	public Archivo get_archivoEscenario() {
		return _archivoEscenario;
	}

	public void set_archivoEscenario(Archivo archivoEscenario) {
		_archivoEscenario = archivoEscenario;
	}

	public Archivo get_archivoMSCC() {
		return _archivoMSCC;
	}

	public void set_archivoMSCC(Archivo archivoMSCC) {
		_archivoMSCC = archivoMSCC;
	}
	
}
