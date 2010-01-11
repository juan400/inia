package com.inia_mscc.entidades.eje;

import java.io.File;
import java.util.Date;
import java.util.Map;

import com.inia_mscc.entidades.comun.Objeto;
import com.inia_mscc.entidades.gem.Archivo;
import com.inia_mscc.entidades.gem.Escenario;

public class ResultadoMSCC extends Objeto {
	private Date _fecha;
	private Archivo _archivo;
	private Map _matrizDatos;// : Map<dia, registro<ValorSeleccion, dato>>
	private Escenario _escenario;
	
	public Map MapearResultado()
	{
		//_archivo.
		//String url = new String("C:/Biblioteca/Cajón/Proyecto/INIA/Archivos Recibidos/"+_archivo.get_nombre());//output_modelo_trigo (1).txt");
		File f = new File(_archivo.get_ubicacion().get_urlPaht()+_archivo.get_nombre());
		if(f.canRead())
		{
			//system.f.this
		}
		return _matrizDatos;
	}
	
}
