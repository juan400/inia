package com.inia_mscc.modulos.eje.entidades;

import java.util.Date;

import javax.persistence.Transient;

import org.apache.commons.collections.map.HashedMap;

import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Escenario;

public class ResultadoMSCC {
	private Date _fecha;
	private Archivo _archivo;
	private HashedMap _matrizDatos;
	private Escenario _escenario;

	public ResultadoMSCC() {
		super();
		_fecha = new Date();
		_archivo = null;
		_matrizDatos = null;
		_escenario = null;
	}

	public Date get_fecha() {
		return _fecha;
	}

	public void set_fecha(Date fecha) {
		_fecha = fecha;
	}

	public Archivo get_archivo() {
		return _archivo;
	}

	public void set_archivo(Archivo archivo) {
		_archivo = archivo;
	}
	
	@Transient
	public HashedMap get_matrizDatos() throws Exception {
		try {
//			InputStream is = _archivo.get_ubicacion().get_urlPaht()
//					.openStream(); // Abro InputStream desde URL
//			BufferedReader entrada = new BufferedReader(new InputStreamReader(
//					is));
			// File f = new File(_archivo.get_ubicacion().get_urlPaht()
			// + _archivo.get_nombre());
			// BufferedReader entrada = new BufferedReader(new FileReader(f));
			// if (f.exists()) {
			// if (f.canRead()) {
			// entrada.read();
//			String archivo = entrada.readLine();
//			if (archivo.isEmpty()) {
//				String[] lineas = archivo.split("\n");
//				for (int i = 1; i < lineas.length; i++) {
//					String[] columnas = lineas[1].split(" ");
//
//					String[] datos = lineas[i].split(" ");
//					HashedMap registro = new HashedMap();
//					for (int j = 0; j < datos.length; j++) {
//						registro.put(columnas[j].toString(), Double.parseDouble(datos[j].toString()));
//					}
//					_matrizDatos.put(i, registro);
//				}
//			}
			// }
			// }
		} catch (Exception ex) {
			throw ex;
		}
		return _matrizDatos;
	}

	public void set_matrizDatos(HashedMap matrizDatos) {
		_matrizDatos = matrizDatos;
	}
	
	public Escenario get_escenario() {
		return _escenario;
	}

	public void set_escenario(Escenario escenario) {
		_escenario = escenario;
	}

}
