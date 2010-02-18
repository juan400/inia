package com.inia_mscc.modulos.comun.entidades;

public class Enumerados {

	static public enum TransaccionesNoContenidas {
		SEG001, SEG002, SEG003, SEG006, SEG009, SEG010, ADM002, ADM006, ADM007, ADM009
	}

	static public enum Proceso {
		ADM, SEG, EJE, GEM, HPE
	}

	static public enum NombreProceso {
		Administración, Seguridad, Ejecución, Escenarios, Historial
	}

	static public enum EstadoUsuario {
		Ninguno, Activo, Inactivo, Registrado, Bloqueado
	}

	static public enum ServicioComun {
		MailSender
	}

	static public enum ServicioADM {
		RelacionPCD, Transaccion, Region, ValorSeleccion, ListaCriterio
	}

	static public enum ServicioSEG {
		Usuario, Perfil
	}

	static public enum ServicioGEM {
		Cultivo, Propiedad, Escenario, Ubicacion, Archivo
	}

	static public enum ServicioEJE {
		Ejecucion
	}

	static public enum Estado {
		Activo, Inactivo
	}

	/**
	 * Establece los posibles estados de un usuario
	 */
	static public enum EstadoSolicitud {
		Registrada, Pendiente, Autorizada, Rechazada
	}

	static public enum TipoPropiedadCultivo {
		Ninguno, Fenotipica, Genotipica, Ambiente
	}

	static public enum ListaCriterio {
		Ninguno, Fertilizante, IndiceConeat, ParametrosSalida
	}

	static public enum EstadoArchivo {
		Ninguno, Subiendo, Cargado
	}

	static public enum TipoArchivo {
		ModeloSimulacion, Climatologico, Escenario, ParametrosClimaticos, Resultados,
	}

	static public enum NobresDeArchivos {
		mscc_, weather_data, scenario, climate_parameters_,
	}

	static public enum TipoExtencionArchivo {
		xml, txt, py
	}

	static public enum EstadoModificacion {
		Ninguno, Modificado, Insertado, Eliminado
	}

	static public enum TipoSolicitud {
		Ninguno, Perfil, Transaccion, Habilitacion
	}

}
