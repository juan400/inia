package com.inia_mscc.modulos.pyhton;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EjecutarPyhton {

	private String comandoPyhton;
	private ArrayList<String> parametros;
	
	
	public EjecutarPyhton(String comandoPyhton,ArrayList<String> parametros){
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


	public void ejecutarPython() throws ScriptException{
		
		StringBuilder comando = new StringBuilder();
		comando.append(this.comandoPyhton);
		if (parametros != null) {
			
			for (int i = 0; i < parametros.size(); i++) {
				
				comando.append(parametros.get(i) + " ");
			}
		}
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine jythonEngine = manager.getEngineByName("python");
		jythonEngine.eval(comando.toString());
		
	   }

	}
	
