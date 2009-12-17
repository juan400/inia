package com.tcs.ejemploSpring.dao;

import java.util.List;

import com.tcs.ejemploSpring.modelo.Alumno;

public interface IAlumnoDAO {
	
	void agregarAlumno(Alumno alumno);
	
	List<Alumno> obtenerAlumnos();
	
}
