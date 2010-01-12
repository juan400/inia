package com.inia_mscc.negocio.comun.entidades;

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
		Ninguno, Fenotipica, Genotipica, Ambiente
	}

	/**
	 * @author Juan Andres Pio
	 */
	static public enum EstadoArchivo {
		Ninguno ,Subiendo, Cargado
	}

	/**
	 * @author Juan Andres Pio
	 */
	static public enum TipoArchivo {
		Ninguno, Climatologico, Escenario, Gecros, Wgen, Climate_Parameters, K_Wgen_Prep
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
	
	/**
	 * @author Juan Andres Pio
	 */
	static public enum TipoSolicitud {
		Ninguno, Perfil, Transaccion, Habilitacion
	}
	
	
}
