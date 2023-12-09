package ar.edu.unju.edm.controller;

import java.util.Map;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CuestionarioAlumno;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.service.ICuestionarioPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IAlumnoService;
import ar.edu.unju.edm.service.ICuestionarioAlumnoService;


@Controller
public class CuestionarioAlumnoController {
	
	private static final Log GRUPO6 = LogFactory.getLog(CuestionarioAlumnoController.class);
	
	@Autowired
	ICuestionarioAlumnoService cuestionarioAlumnoService;
	
	@Autowired
	IAlumnoService alumnoService;

	@Autowired
	ICuestionarioService cuestionarioService;
	
	@Autowired
	ICuestionarioPreguntaService cuestionarioPreguntasService;
	
	@Autowired
	CuestionarioAlumno unCuestionarioAlumno;
	
	@Autowired
	CuestionarioRepository cuestionarioRepository;

	
	
	
	@GetMapping("/elegirCuestionario")
	public ModelAndView cargarCuesEstudiante () {
		ModelAndView cargaCuestionarioAlumno = new ModelAndView("mostrarCuestionariosAAlumnos");
		cargaCuestionarioAlumno.addObject("cuestionarios", cuestionarioService.listarCuestionariosTodos());
		GRUPO6.warn("Elegir Cuestionario");
		return cargaCuestionarioAlumno;
	}
	
	@GetMapping("/elegirCuestionarioDocente")
	public ModelAndView cargarCuestionarioAlumnoParaDocente () {
		ModelAndView cargaCuestionarioAlumno = new ModelAndView("mostrarCuestionariosParaResolverDocente");
		cargaCuestionarioAlumno.addObject("cuestionarios", cuestionarioService.listarCuestionarios());
		GRUPO6.warn("Elegir Cuestionario");
		return cargaCuestionarioAlumno;
	}
	
	
	//El Estudiante resuelve el cuestionario
	
	@GetMapping("/resolverCuestionario/{id_Cuestionario}")
	public ModelAndView resolverCuestionarioAlumno(@PathVariable(name="id_Cuestionario")  Integer idCuesElegido) {
		ModelAndView resolverCuestionario = new ModelAndView("resolverCuestionario");
			GRUPO6.warn("Alumno resuelve cuestionario");
			resolverCuestionario.addObject("nuevoCuestionarioAlumno", unCuestionarioAlumno);
			resolverCuestionario.addObject("listadoAlumnos", alumnoService.listarAlumnos());
			
			resolverCuestionario.addObject("cuestionario", cuestionarioService.mostrarUnCuestionario(idCuesElegido));
			resolverCuestionario.addObject("preguntas", cuestionarioPreguntasService.ListarPreguntasDeUnCuestionario(idCuesElegido));
			
			GRUPO6.warn("Nueva resolucion de Cuestionario");
		return resolverCuestionario;
	}
	
	
	
	//Guardar las respuestas del cuestionario
	@PostMapping("/resultadoDeCuestionario/{id_Cuestionario}")
	public ModelAndView guardarCuestionarioERealizado(@ModelAttribute("nuevoCuestionarioAlumno") CuestionarioAlumno nuevoCuestionarioAlumno,
			@RequestParam Map<String,String> respuestasSeleccionadas, @PathVariable(name="id_Cuestionario") Integer idCuestionario ) { 
		
		ModelAndView resultadoCuestionario = new ModelAndView("resultadoCuestionario");

		GRUPO6.warn("Cuestionario realizado");
		try {
		
		nuevoCuestionarioAlumno.setFechaRealizada(cuestionarioAlumnoService.fechaActual());
        nuevoCuestionarioAlumno.setCuestionario(cuestionarioRepository.findById(idCuestionario).get());
        nuevoCuestionarioAlumno.setPuntajeObtenido(cuestionarioAlumnoService.calcularPuntajeObtenido(idCuestionario, respuestasSeleccionadas));
        cuestionarioPreguntasService.obtenerPuntajeTotalDeUnCuestionario(idCuestionario);
        cuestionarioAlumnoService.cargarCuestionarioAlumno(nuevoCuestionarioAlumno);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			GRUPO6.error(e);
		}
		resultadoCuestionario.addObject("nuevoCuestionarioAlumno", nuevoCuestionarioAlumno);
    
		return resultadoCuestionario;
	}
	
	
	//mostrando todos los cuesEstudiantes
	@GetMapping("/cuestionariosRealizados")
	public ModelAndView guardarCuesEstudiante () {
		GRUPO6.warn("Listando todos los Cuestionarios");
		ModelAndView listadoCuestionarioAlumno = new ModelAndView("mostrarCuestionariosResueltos");
		
		listadoCuestionarioAlumno.addObject("cuestionarioAlumnoListado", cuestionarioAlumnoService.listarTodosCuestionariosAlumnos() );
		
		return listadoCuestionarioAlumno;
	}
	
	@GetMapping("/cuestionariosRealizadosDocentes")
	public ModelAndView guardarCuestionarioAlumnoDocentes () {
		GRUPO6.warn("Listando todos los Cuestionarios");
		ModelAndView listadoCuestionarioAlumno = new ModelAndView("mostrarCuestionariosResueltosDocente");
		
		listadoCuestionarioAlumno.addObject("cuestionarioAlumnoListado", cuestionarioAlumnoService.listarTodosCuestionariosAlumnos() );
		
		return listadoCuestionarioAlumno;
	}
	
}
