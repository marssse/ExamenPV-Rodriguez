package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Alumno;

@Service
public interface IAlumnoService {
	public void cargarAlumno(Alumno unAlumno);
	public void eliminarUnAlumno(Integer idAlumno);
	public Alumno modificarUnAlumno(Integer idAlumno);
	public Alumno mostrarUnAlumno(Integer idAlumno);
	public ArrayList<Alumno> listarAlumnos();
	public void eliminarTodosLosAlumnos();
}
