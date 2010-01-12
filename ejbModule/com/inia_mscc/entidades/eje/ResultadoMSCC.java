package com.inia_mscc.entidades.eje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import org.hibernate.hql.ast.tree.IsNotNullLogicOperatorNode;

import com.inia_mscc.entidades.gem.Archivo;
import com.inia_mscc.entidades.gem.Escenario;
import com.inia_mscc.negocio.comun.Objeto;

public class ResultadoMSCC extends Objeto {
	private Date _fecha;
	private Archivo _archivo;
	private Map _matrizDatos;// : Map<dia, registro<ValorSeleccion, dato>>
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

	public Map get_matrizDatos() throws Exception {
		try {
			InputStream is = _archivo.get_ubicacion().get_urlPaht()
					.openStream(); // Abro InputStream desde URL
			BufferedReader entrada = new BufferedReader(new InputStreamReader(
					is));
			// File f = new File(_archivo.get_ubicacion().get_urlPaht()
			// + _archivo.get_nombre());
			// BufferedReader entrada = new BufferedReader(new FileReader(f));
			// if (f.exists()) {
			// if (f.canRead()) {
			// entrada.read();
			String archivo = entrada.readLine();
			//Map<dia, registro<ValorSeleccion, dato>>
			if (archivo.isEmpty()) {
				String[] lineas = archivo.split("\n");
				for (int i = 1; i < lineas.length; i++) {
					String[] columnas = lineas[1].split(" ");

					String[] datos = lineas[i].split(" ");
					Map<Integer,Map<String,Double>> registro;
					for (int j = 0; j < datos.length; j++) {
						//registro.put(columnas[j].toString(), Double.parseDouble(datos[j].toString());	
					}
					//_matrizDatos.put(i, registro);
				}
			}
			// }
			// }
		} catch (Exception ex) {
			throw ex;
		}
		return _matrizDatos;
	}

	public void set_matrizDatos(Map matrizDatos) {
		_matrizDatos = matrizDatos;
	}
	
	public Escenario get_escenario() {
		return _escenario;
	}

	public void set_escenario(Escenario escenario) {
		_escenario = escenario;
	}

}
