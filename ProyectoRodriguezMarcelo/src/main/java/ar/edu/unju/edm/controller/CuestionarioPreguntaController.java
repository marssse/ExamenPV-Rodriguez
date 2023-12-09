package ar.edu.unju.edm.controller;

import java.util.List;

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

import ar.edu.unju.edm.model.CuestionarioPregunta;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.service.ICuestionarioPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IPreguntaService;

@Controller
public class CuestionarioPreguntaController {

	@Autowired
	ICuestionarioPreguntaService cuestionarioPreguntaService;
	@Autowired
	IPreguntaService preguntaService;
	@Autowired
	ICuestionarioService cuestionarioService;
	@Autowired
	CuestionarioPregunta unCuestionarioPregunta;
	@Autowired
	CuestionarioRepository cuestionarioRepository;
	
	private static final Log GRUPO6 = LogFactory.getLog(CuestionarioAlumnoController.class);
	
	@GetMapping("/cuestionarioPregunta/{id_Cuestionario}")
	public ModelAndView cargarCuesPregunta(@PathVariable(name="id_Cuestionario") Integer id) {
		
		ModelAndView unCuesP= new ModelAndView("formularioCuestionariopregunta");
		
		unCuesP.addObject("listadoSeleccionadas", cuestionarioPreguntaService.ListarPreguntasDeUnCuestionario(id));
		unCuesP.addObject("listadoDeNoSeleccionadas", cuestionarioPreguntaService.ListarPreguntasNoSeleccionadas( cuestionarioPreguntaService.ListarPreguntasDeUnCuestionario(id), preguntaService.listarPreguntas()));
		unCuesP.addObject("listadoPreguntas", preguntaService.listarPreguntas());
		
		GRUPO6.warn("Cargando preguntas al cuestionario");
		return unCuesP;
	}
	
	@PostMapping("/guardarCuestionarioPregunta/{id_Cuestionario}")
	public String guardarCuesPregunta(@ModelAttribute("cuesPregunta") CuestionarioPregunta CuestionarioP, @RequestParam("preguntasSeleccionada") List<Integer> preguntasSeleccionadas, @RequestParam("puntajesSeleccionados") List<Integer> puntajesSeleccionados, @PathVariable(name="id_Cuestionario") Integer id) {
		
		GRUPO6.warn("PUNTOS TOTALES");
		GRUPO6.warn(puntajesSeleccionados);
		GRUPO6.warn(preguntasSeleccionadas);
		
	  cuestionarioPreguntaService.cargarPreguntasACuestionario(preguntasSeleccionadas, cuestionarioPreguntaService.depurarPuntajesNoSeleccionados(puntajesSeleccionados), id);
		
		return "redirect:/listadoCuestionarios";
	}
	
	
}
