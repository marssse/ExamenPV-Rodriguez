package ar.edu.unju.edm.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.service.ICuestionarioPreguntaService;
import ar.edu.unju.edm.service.ICuestionarioService;
import ar.edu.unju.edm.service.IDocenteService;
import javax.validation.Valid;

@Controller
public class CuestionarioController {
	
 private static final Log GRUPO6 = LogFactory.getLog(CuestionarioController.class);
	
	
	@Autowired
	Cuestionario unCuestionario;
	
	@Autowired
	ICuestionarioService cuestionarioService;
	@Autowired
	IDocenteService docenteService;
	@Autowired
	ICuestionarioPreguntaService cuestionarioPreguntaService;
	
	@GetMapping("/cuestionario")
	public ModelAndView cargarCuestionario() {
		ModelAndView cargaCuestionario = new ModelAndView("formularioCuestionario");
		cargaCuestionario.addObject("nuevoCuestionario", unCuestionario);
	    GRUPO6.warn("Cargando nuevo cuestionario");
		return cargaCuestionario;
	}
	
	@PostMapping("/guardarCuestionario")
	public ModelAndView guardarCuestionario(@Valid @ModelAttribute("nuevoCuestionario") Cuestionario unCuestionario, BindingResult resultado ) {
		if(resultado.hasErrors()) {
			ModelAndView cargaCuestionario = new ModelAndView("formularioCuestionario");
			cargaCuestionario.addObject("nuevoCuestionario", unCuestionario);
			return cargaCuestionario;
		}
		
		ModelAndView listadoCuestionarios = new ModelAndView("mostrarCuestionarios");
		
			GRUPO6.warn("Mostrando nuevo Cuestionario"+unCuestionario.getId_Cuestionario());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			if (authentication != null && authentication.isAuthenticated()) {
			    // Obtener los detalles del usuario actualmente autenticado
			    Object principal = authentication.getPrincipal();
			
			    // Verificar si el objeto principal es una instancia de la entidad que deseas guardar
			    if (principal instanceof UserDetails) {
			        UserDetails entidadAutenticada = (UserDetails) principal;
			    
			        unCuestionario.setDocente(docenteService.mostrarUnDocente(Integer.parseInt(entidadAutenticada.getUsername())));
			        
			    }
			}
			
		try {
			cuestionarioService.cargarCuestionario(unCuestionario);
		}catch(Exception e) {
			listadoCuestionarios.addObject("cargarCuestionarioErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		listadoCuestionarios.addObject("cuestionarioListado", cuestionarioService.listarCuestionarios());
		
		return listadoCuestionarios;
	}
	
	@GetMapping("/listadoCuestionarios")
	public ModelAndView mostrarCuestionarios(){
		GRUPO6.warn("Lista de Cuestionarios");
		ModelAndView listadoCuestionarios = new ModelAndView("mostrarCuestionarios");
		listadoCuestionarios.addObject("cuestionarioListado", cuestionarioService.listarCuestionarios());
		
		return listadoCuestionarios;
	}
	
	//CUESTIONARIO CON PREGUNTAS
	
	@PostMapping("/cuestionarioConPreguntas/{id_Cuestionario}")
	public ModelAndView guardarCuestionarioConPreguntas(@PathVariable(name="id_Cuestionario") Integer id) {
		
		ModelAndView listadoCuestionarios = new ModelAndView("mostrarCuestionarios");
		
		GRUPO6.warn("Cuestionario con preguntas: " + unCuestionario.getTitulo());
		try {
			listadoCuestionarios.addObject("preguntas", cuestionarioPreguntaService.ListarPreguntasDeUnCuestionario(id));
		}catch(Exception e) {
			listadoCuestionarios.addObject("cargaCuestionarioConPreguntasErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		listadoCuestionarios.addObject("cuestionarioListado", cuestionarioService.listarCuestionarios());
		
		return listadoCuestionarios;
	}
	

}
