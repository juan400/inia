package com.tcs.ejemploSpring.service;

import java.util.List;

import com.tcs.ejemploSpring.modelo.Alumno;

public interface IAlumnoService {
	
	/**
	 * Agrega el alumno, el Id es asignado por ID
	 * @param alumno
	 */
	void agregarAlumno(Alumno alumno);
	
	List<Alumno> obtenerAlumnos();
	
}
