package com.inia_mscc.modulos.pyhton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class EjecutarPyhton {

	private String comandoPyhton;
	private ArrayList<String> parametros;

	public EjecutarPyhton(String comandoPyhton, ArrayList<String> parametros) {
		super();
		this.comandoPyhton = comandoPyhton;
		this.parametros = parametros;
	}

	public String getComandoPyhton() {
		return comandoPyhton;
	}

	public void setComandoPyhton(String comandoPyhton) {
		this.comandoPyhton = comandoPyhton;
	}

	public ArrayList<String> getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList<String> parametros) {
		this.parametros = parametros;
	}

	public void ejecutarPython() throws Exception {
		try {
			StringBuilder comando = new StringBuilder();
			comando.append(this.comandoPyhton + " ");
			if (parametros != null) {

				for (int i = 0; i < parametros.size(); i++) {

					comando.append(parametros.get(i) + " ");
				}
			}
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine jythonEngine = manager.getEngineByName("python");
			jythonEngine.eval(comando.toString());
			// Thread.sleep(arg0, arg1)
			Thread.sleep(36000);
		} catch (Exception ex) {
			throw new Exception("No pudo realizar la ejecución.");
		}
	}

	/**
	 * 
	 */
	public void ejecutarComando() {
		String s = null;

		try {
			// Determinar en qué SO estamos
			String so = System.getProperty("os.name");

			String comando = "cd ArchivosSubidos/Ejecucion/Ejecucion_admin_2010-3-25_20496724";

			StringBuilder lineaComando = new StringBuilder();
			lineaComando.append(this.comandoPyhton + " ");
			if (parametros != null) {

				for (int i = 0; i < parametros.size(); i++) {

					lineaComando.append(parametros.get(i) + " ");
				}
			}
			/**
			 * 
			 // Comando para Linux if (so.equals("Linux")) comando =
			 * "ifconfig";
			 * 
			 * // Comando para Windows else comando = "cmd /c ipconfig";
			 */
			// Ejcutamos el comando
			Process p = Runtime.getRuntime().exec(comando);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));

			// Leemos la salida del comando
			System.out.println("Ésta es la salida standard del comando:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// Leemos los errores si los hubiera
			System.out
					.println("Ésta es la salida standard de error del comando (si la hay):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}

			System.exit(0);
		} catch (IOException e) {
			System.out.println("Excepción: ");
			e.printStackTrace();
			System.exit(-1);
		}

	}

}