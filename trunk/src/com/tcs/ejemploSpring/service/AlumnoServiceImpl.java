package com.tcs.ejemploSpring.service;

import java.util.List;

import com.tcs.ejemploSpring.dao.IAlumnoDAO;
import com.tcs.ejemploSpring.modelo.Alumno;

public class AlumnoServiceImpl implements IAlumnoService {
	
	private Long id;
	private IAlumnoDAO alumnoDao;
	
	public AlumnoServiceImpl() {
		System.out.println("Se inicializa el servicio de Alumno");
	}

	public void hola(){
		System.out.println("Hola");
	}
	
	// Se utiliza para la IoC/ID
	public void setId(Long id) {
		this.id = id;
	}

	

	public void setAlumnoDao(IAlumnoDAO alumnoDao) {
		this.alumnoDao = alumnoDao;
	}

	public void agregarAlumno(Alumno alumno) {
		//alumno.setId(id);
		System.out.println(alumno.toString());
		// utilizo el DAO para persistir el Objeto
		this.alumnoDao.agregarAlumno(alumno);
	}

	public List<Alumno> obtenerAlumnos() {
		return alumnoDao.obtenerAlumnos();
	}
	
}
