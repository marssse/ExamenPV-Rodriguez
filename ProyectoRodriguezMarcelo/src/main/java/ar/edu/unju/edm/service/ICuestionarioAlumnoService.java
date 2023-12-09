package ar.edu.unju.edm.service;



import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuestionarioAlumno;


@Service
public interface ICuestionarioAlumnoService {
	public void cargarCuestionarioAlumno(CuestionarioAlumno cuestionarioAlumno);
	public void eliminarCuestionarioAlumno(Integer idCuestionarioAlumno);
	public ArrayList<CuestionarioAlumno> listarTodosCuestionariosAlumnos();
	public CuestionarioAlumno listarUnCuestionarioAlumno(Integer idCuestionarioAlumno);
	public CuestionarioAlumno modificarCuestionarioAlumno(Integer idCuestionarioAlumno);
	public void eliminarTodosCuesAlumnos();
	public Integer calcularPuntajeObtenido(Integer idCuestionario, Map<String,String> opcionesElegidas);
	public String fechaActual();
}
