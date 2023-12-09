package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.service.IPreguntaService;

@Controller
public class PreguntaController {
	@Autowired
	Pregunta unaPregunta;
	
	@Autowired
	@Qualifier("servicioEnMySQL")
	IPreguntaService preguntaService;
	
	private static final Log GRUPO6 = LogFactory.getLog(PreguntaController.class);
	
	@GetMapping("/pregunta")
	public ModelAndView cargarPregunta() {
		ModelAndView newPregunta= new ModelAndView("formularioPregunta");
		newPregunta.addObject("nuevaPregunta", unaPregunta);
    
		newPregunta.addObject("band", false);

		GRUPO6.warn("Cargando nueva pregunta");

		return newPregunta;
	}
	
	@PostMapping("/guardarPregunta")
	public ModelAndView guardarPregunta(@ModelAttribute("nuevaPregunta") Pregunta laPregunta) {
		ModelAndView listadoPreguntas = new ModelAndView("mostrarPregunta");
		try {
			GRUPO6.warn("Guardando nueva pregunta");
			preguntaService.cargarPregunta(laPregunta);
		}catch(Exception e) {
			GRUPO6.error(e);	
		}
		GRUPO6.warn("Listado de Preguntas Realizadas");
		listadoPreguntas.addObject("preguntaListado", preguntaService.listarPreguntas());
		return listadoPreguntas;
	}
	
	//ELIMINAR
	@GetMapping("/eliminarPregunta/{idPregunta}")
	public ModelAndView eliminarPregunta(@PathVariable(name="idPregunta") Integer id) {
		ModelAndView newPregunta= new ModelAndView("mostrarPregunta");
		newPregunta.addObject("nuevaPregunta", unaPregunta);
		try {
			preguntaService.eliminarPregunta(id);
		}catch(Exception e) {
			newPregunta.addObject("eliminarPreguntaErrorMessage", e.getMessage());
		}
		
		try {
			newPregunta.addObject("preguntaListado", preguntaService.listarPreguntas());
		}catch(Exception e) {
			newPregunta.addObject("listarPreguntaErrorMessage", e.getMessage());
		}
		return newPregunta;
	}
	
	//MODIFICAR
	@GetMapping("/modificarPregunta/{idPregunta}")
	public ModelAndView modificarPregunta(@PathVariable(name="idPregunta") Integer id) {
		ModelAndView newPregunta= new ModelAndView("formularioPregunta");
		try {
			newPregunta.addObject("nuevaPregunta", preguntaService.listarUnaPregunta(id));
		}catch(Exception e) {
			newPregunta.addObject("modificarPreguntaErrorMessage", e.getMessage());
		}
		newPregunta.addObject("band", true);
		return newPregunta;
	}
	
	@PostMapping("/preguntaModificada")
	public ModelAndView guardarPreguntaModificada(@ModelAttribute("nuevaPregunta") Pregunta laPregunta) {
		ModelAndView listadoPreguntas = new ModelAndView("mostrarPregunta");
			try {
				preguntaService.cargarPregunta(laPregunta);
			}catch(Exception e) {
				
			}
		listadoPreguntas.addObject("preguntaListado", preguntaService.listarPreguntas());
		return listadoPreguntas;
	}
	
	
}