package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Docente;

@Controller
public class IndexController implements ErrorController  {
	
	@Autowired
	Docente unDocente;
	
	private static final String PATH = "/error";
	
	@GetMapping({"/","/index","/home","/login"})
	public ModelAndView cargarPrincipal(){
		ModelAndView principal = new ModelAndView("index");
		principal.addObject("docenteParaLoguear", unDocente);
		
		return principal;
	}
	
	//en caso de ingresar una direccion no válida
	@RequestMapping(value = PATH)
	public String error() {
		return "error";
		
	}
	
	public static String getPath() {
		return PATH;
	}
}
