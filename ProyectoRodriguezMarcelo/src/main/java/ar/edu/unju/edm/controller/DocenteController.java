package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import ar.edu.unju.edm.model.Docente;
import ar.edu.unju.edm.service.IDocenteService;
import javax.validation.Valid;

@Controller
public class DocenteController {
	
	private static final Log GRUPO6 = LogFactory.getLog(DocenteController.class);
	
	@Autowired
	Docente unDocente;
	
	@Autowired
	IDocenteService unServicio;
	
	@GetMapping("/docente")
	public ModelAndView cargarDocente() {
		ModelAndView cargaDocente = new ModelAndView("formularioDocente");
		cargaDocente.addObject("nuevoDocente", unDocente);
		
		cargaDocente.addObject("band", false);
		GRUPO6.warn("Cargando nuevo Docente");
		return cargaDocente;
	}
	
	@GetMapping("/listadoDocente")
	public ModelAndView mostrarDocente() {
		ModelAndView listadoDocentes = new ModelAndView("mostrarDocentes");
		listadoDocentes.addObject("docenteListado", unDocente);
		
		listadoDocentes.addObject("docenteListado",unServicio.listarDocentes());
		
		return listadoDocentes;
	}
	
	@GetMapping("/listaDeDocentes")
	public ModelAndView mostrarDocentes() {
		ModelAndView listadoDocentes = new ModelAndView("listaDeDocentes");
		listadoDocentes.addObject("docenteListado", unDocente);
		
		listadoDocentes.addObject("docenteListado",unServicio.listarDocentes());
		
		return listadoDocentes;
	}
	
	@PostMapping(value ="/guardarDocente", consumes="multipart/form-data")
	public ModelAndView guardarDocente(@Valid @ModelAttribute("nuevoDocente") Docente docenteNuevo,@RequestParam("file") MultipartFile[] archivo, BindingResult resultado ){
		
		if(resultado.hasErrors()) {
			ModelAndView cargaDocente = new ModelAndView("formularioDocente");
			cargaDocente.addObject("nuevoDocente", docenteNuevo);
			return cargaDocente;
		}
		
		ModelAndView listadoDocentes = new ModelAndView("mostrarDocentes");
		
		//carga de la foto
		try {
			GRUPO6.warn("Cargando la FOTO");
			byte[] contenido= archivo[0].getBytes();
			String base64= Base64.getEncoder().encodeToString(contenido);
			docenteNuevo.setFoto(base64);
		}catch(Exception e) {
			listadoDocentes.addObject("cargarFotoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		
		GRUPO6.warn("Mostrando el nuevo docente " + docenteNuevo.getNombre());
		try {
			unServicio.cargarDocente(docenteNuevo);
		}catch(Exception e) {
			listadoDocentes.addObject("cargaDocenteErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		listadoDocentes.addObject("docenteListado",unServicio.listarDocentes());
		
		return listadoDocentes;
	}
	
	//MODIFICAR 
	
	@GetMapping("/modificarDocente/{id_Docente}")
	public ModelAndView modificarDocente(@PathVariable(name="id_Docente") Integer id) {
		ModelAndView editarDocente = new ModelAndView("formularioDocente");
		
		try {
			editarDocente.addObject("nuevoDocente",unServicio.mostrarUnDocente(id));
		}catch(Exception e) {
			editarDocente.addObject("modificarDocenteErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		GRUPO6.warn("Docente a modificar: " + id);
		editarDocente.addObject("band", true);
		
		return editarDocente;
	}
	
	@PostMapping(value ="/modificarDocente", consumes="multipart/form-data")
	public ModelAndView modificarDocente(@ModelAttribute("nuevoDocente") Docente docenteNuevo, @RequestParam("file") MultipartFile[] archivo){
		ModelAndView listadoDocentes = new ModelAndView("mostrarDocentes");
		
		
		//carga de la foto
		try {
			GRUPO6.warn("Cargando una imagen para el docente");
			byte[] contenido= archivo[0].getBytes();
			String base64= Base64.getEncoder().encodeToString(contenido);
			docenteNuevo.setFoto(base64);
		}catch(Exception e) {
			listadoDocentes.addObject("cargarFotoErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		
		try {
			GRUPO6.warn("Docente modificado: " + docenteNuevo.getId_Docente());
			unServicio.cargarDocente(docenteNuevo);
		}catch(Exception e) {
			listadoDocentes.addObject("cargaDocenteErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		listadoDocentes.addObject("docenteListado",unServicio.listarDocentes());
		
		return listadoDocentes;
	}
	
	//ELIMINAR
	
	@GetMapping("/eliminarDocente/{id_Docente}")
	public ModelAndView eliminarDocente(@PathVariable(name="id_Docente") Integer id) {
		ModelAndView eliminarDocente = new ModelAndView("mostrarDocentes");
		
		try {
			GRUPO6.warn("Eliminando Docente");
			unServicio.eliminarDocente(id);
		}catch(Exception e) {
			eliminarDocente.addObject("eliminarDocenteErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		try {
			GRUPO6.warn("Listando Docentes");
			eliminarDocente.addObject("docenteListado", unServicio.listarDocentes());
		}catch(Exception e) {
			eliminarDocente.addObject("listarDocenteErrorMessage", e.getMessage());
			GRUPO6.error(e);
		}
		
		return eliminarDocente;
	}

}
