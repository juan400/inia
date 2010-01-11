package com.inia_mscc.entidades.comun;

public class Enumerados {

	/**
	 * @author Juan Andres Pio
	 * 
	 */
	static public enum EstadoUsuario {
		Ninguno, Activo, Inactivo, Registrado, Bloqueado
	}

	/**
	 * @author Juan Andres Pio
	 * 
	 */
	static public enum Estado {
		Activo, Inactivo
	}

	/**
	 * @author Juan Andres Pio Establece los posibles estados de un usuario
	 */
	static public enum EstadoSolicitud {
		Registrada, Pendiente, Autorizada, Rechazada
	}

	/**
	 * @author Juan Andres Pio
	 * 
	 */
	static public enum TipoPropiedadCultivo {
		Fenotipica, Genotipica, ambiente
	}

	/**
	 * @author Juan Andres Pio
	 */
	static public enum EstadoArchivo {
		Subiendo, Cargado
	}

	/**
	 * @author Juan Andres Pio
	 */
	static public enum TipoArchivo {
		Climatologico, Escenario, Gecros, Wgen, Climate_Parameters, K_Wgen_Prep
	}

	/**
	 * @author Juan Andres Pio
	 */
	static public enum TipoExtencionArchivo {
		xml, txt, py
	}
	
	/**
	 * @author Juan Andres Pio
	 */
	static public enum EstadoModificacion {
		Ninguno, Modificado, Insertado, Eliminado
	}
}
