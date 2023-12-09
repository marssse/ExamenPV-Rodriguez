package ar.edu.unju.edm.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Alumno;
import ar.edu.unju.edm.service.IAlumnoService;
import javax.validation.Valid;

@Controller
public class AlumnoController {

	private static final Log GRUPO6 = LogFactory.getLog(AlumnoController.class);
	
	
	@Autowired
	Alumno unAlumno = new Alumno();
	
	@Autowired
	@Qualifier("servicioAlumnoEnMySQL")
	IAlumnoService unServicio;
	
	@GetMapping("/alumno")
	public ModelAndView cargarAlumno() {
		ModelAndView cargaAlumno = new ModelAndView("formularioAlumno");
		cargaAlumno.addObject("nuevoAlumno", unAlumno);

		char[] divisiones = { 'A', 'B', 'C', 'D' };
		cargaAlumno.addObject("listaDivisiones", divisiones);

		cargaAlumno.addObject("band", false);
		GRUPO6.warn("Cargando nuevo Alumno");
		return cargaAlumno;
	}
	
	/*
	@GetMapping("/listadoAlumno")
	public ModelAndView mostrarAlumno(){
		
		ModelAndView listadoAlumnos = new ModelAndView("mostrarAlumnos");
		listadoAlumnos.addObject("AlumnoListado", unServicio.listarAlumnos());
		
		return listadoAlumnos;
	}
	*/

	
	@RequestMapping("/listaDeAlumnos")
	public ModelAndView mostrarAlumnos(){
		
		ModelAndView listaDeAlumnos = new ModelAndView("listaDeAlumnos");
		listaDeAlumnos.addObject("alumnoListado", unServicio.listarAlumnos());
		
		return listaDeAlumnos;
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView guardarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno nAlumno, BindingResult resultado) {
		
		if(resultado.hasErrors()) {
			ModelAndView cargaAlumno = new ModelAndView("formularioAlumno");
			cargaAlumno.addObject("nuevoAlumno", nAlumno);
			return cargaAlumno;
		}
		
		ModelAndView listadoAlumnos = new ModelAndView("redirect:/listaDeAlumnos");
		
			GRUPO6.warn("Mostrando nuevo Alumno"+nAlumno.getNombre());
		
		try {
			GRUPO6.warn("Guardando Alumno");
			unServicio.cargarAlumno(nAlumno);
		}catch(Exception e) {
			listadoAlumnos.addObject("CargadoAlumnoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
				
		listadoAlumnos.addObject("AlumnoListado", unServicio.listarAlumnos());
		
		return listadoAlumnos;
	}
	
	//MODIFICAR
	
	@GetMapping("/modificarAlumno/{id_Alumno}")
	public ModelAndView modificarAlumno(@PathVariable(name="id_Alumno") Integer id_Alu ) {
		
		ModelAndView modificaAlumno = new ModelAndView("formularioAlumno");
	
		try {
			GRUPO6.warn("Modificando Alumno");
			modificaAlumno.addObject("nuevoAlumno", unServicio.mostrarUnAlumno(id_Alu));
		}catch(Exception e) {
			modificaAlumno.addObject("modificarEstudiantAlumnoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		 char[] divisiones = {'A', 'B', 'C', 'D'};
		 modificaAlumno.addObject("listaDivisiones", divisiones);
		 
		modificaAlumno.addObject("band", true);
		return modificaAlumno;
	}
	
	@PostMapping("/modificarAlumno")
	public ModelAndView modificarEstudiante(@ModelAttribute("nuevoAlumno") Alumno unAlumno ) {
		ModelAndView listadoEditado = new ModelAndView("mostrarEstudiantes");
		
			GRUPO6.warn("Mostrando Alumno modificado: "+unAlumno.getId_Alumno());
		
		try {
			GRUPO6.warn("Guardando modificaciones de Estudiantes");
			unServicio.cargarAlumno(unAlumno);
		}catch(Exception e) {
			listadoEditado.addObject("cargaAlumnoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		listadoEditado.addObject("alumnoListado", unServicio.listarAlumnos());
		
		return listadoEditado;
	}
	
	// ELIMINAR
	@GetMapping("/eliminarAlumno/{id_Alumno}")
	public ModelAndView eliminarAlumno(@PathVariable(name="id_Alumno") Integer id) {
		ModelAndView eliminarAlumno = new ModelAndView("mostrarAlumnos");
		
		try {
			GRUPO6.warn("Eliminando Alumno");
			unServicio.eliminarUnAlumno(id);
		}catch(Exception e) {
			eliminarAlumno.addObject("eliminarAlumnoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		try {
			eliminarAlumno.addObject("alumnoListado", unServicio.listarAlumnos());
		}catch(Exception e) {
			eliminarAlumno.addObject("listarAlumnoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		return eliminarAlumno;
	}
	
	
}
