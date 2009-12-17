package com.tcs.ejemploSpring.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tcs.ejemploSpring.modelo.Alumno;
import com.tcs.ejemploSpring.service.IAlumnoService;

public class TestAlumno {
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		String context = "c:/app-context.xml";
		//"c:/application-context.xml"
		applicationContext = new FileSystemXmlApplicationContext(context);
	
		IAlumnoService service = (IAlumnoService)applicationContext.getBean("alumnoService");
		
		// Instancia de Alumno
		Alumno alumno = new Alumno();
		alumno.setNombre("Cecilia");
		
		// Llamo al servicio
		service.agregarAlumno(alumno);
		
		List<Alumno> alumnos = service.obtenerAlumnos();
		
		for (Alumno alumno2 : alumnos) {
			alumno2.toString();
		}
	}

}
