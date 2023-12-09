package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuestionarioPregunta;
import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.repository.CuestionarioPreguntaRepository;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.repository.PreguntaRepository;
import ar.edu.unju.edm.service.ICuestionarioPreguntaService;

@Service
public class ImpCuestionarioPreguntaService implements ICuestionarioPreguntaService{

	
	@Autowired
	CuestionarioPreguntaRepository cuestionarioPreguntaRepository;
	@Autowired
	CuestionarioRepository cuestionarioRepository;
	@Autowired
	PreguntaRepository preguntaRepository;
	
	@Override
	public void cargarCuestionarioPregunta(CuestionarioPregunta cuestionarioPregunta) {
		cuestionarioPreguntaRepository.save(cuestionarioPregunta);
		
	}
	
	@Override
	public void cargarPreguntasACuestionario (List<Integer> preguntasSeleccionadas,List<Integer> puntajesSeleccionados, Integer id_Cuestionario){
		
		cuestionarioPreguntaRepository.deleteAll(cuestionarioPreguntaRepository.findAllByCuestionario(cuestionarioRepository.findById(id_Cuestionario).get()));
			
		for(int i=0;i<preguntasSeleccionadas.size();i++) {
			CuestionarioPregunta auxiliar= new CuestionarioPregunta();
			auxiliar.setPregunta(preguntaRepository.findById(preguntasSeleccionadas.get(i)).get());
			auxiliar.setPuntaje(puntajesSeleccionados.get(i));
			auxiliar.setCuestionario(cuestionarioRepository.findById(id_Cuestionario).get());
			
			cuestionarioPreguntaRepository.save(auxiliar);
		}
	}

	@Override
	public void borrarCuestionarioPregunta(Integer idCuestionarioPregunta) {
		cuestionarioPreguntaRepository.deleteById(idCuestionarioPregunta);
	}

	@Override
	public ArrayList<CuestionarioPregunta> mostrarTodosCuestionarioPregunta() {
		return (ArrayList<CuestionarioPregunta>) cuestionarioPreguntaRepository.findAll();
	}

	@Override
	public CuestionarioPregunta mostrarUnCuestionarioPregunta(Integer idCuestionarioPregunta) {
		Optional<CuestionarioPregunta> aux= Optional.of(new CuestionarioPregunta());
		aux = cuestionarioPreguntaRepository.findById(idCuestionarioPregunta);
		return aux.get();
	}

	@Override
	public CuestionarioPregunta modificarUnCuestionarioPregunta(Integer idCuestionarioPregunta) {
		return null;
	}
	
	@Override
	public List<Pregunta> ListarPreguntasDeUnCuestionario(Integer id_Cuestionario) {
		Cuestionario aux = cuestionarioRepository.findById(id_Cuestionario).get();
        List<CuestionarioPregunta> cuestionarioPreguntas = cuestionarioPreguntaRepository.findAllByCuestionario(aux);
        List<Pregunta> preguntas = new ArrayList<>();
        for(int i=0;i<cuestionarioPreguntas.size();i++) {
			preguntas.add(cuestionarioPreguntas.get(i).getPregunta());
		}
        return preguntas;
    }
	
	@Override
	public List<Integer> ListarRespuestasDePreguntas(Integer id_Cuestionario){
		List<Pregunta> preguntas = ListarPreguntasDeUnCuestionario(id_Cuestionario);
		List<Integer> respuestas = new ArrayList<>();
		for(int i=0;i<preguntas.size();i++) {
			respuestas.add(preguntas.get(i).getOpcionCorrecta());
		}
		return respuestas;
	}
	

	@Override
	public List<Integer> ListadoDePuntajes(Integer id_Cuestionario) {
		Cuestionario aux = cuestionarioRepository.findById(id_Cuestionario).get();
        List<CuestionarioPregunta> cuestionarioPreguntas = cuestionarioPreguntaRepository.findAllByCuestionario(aux);
		List<Integer> puntajes = new ArrayList<Integer>();
		for(int i=0;i<cuestionarioPreguntas.size();i++) {
			puntajes.add(cuestionarioPreguntas.get(i).getPuntaje());
		}
		return puntajes;
	}

	@Override
	public List<Pregunta> ListarPreguntasNoSeleccionadas(List<Pregunta> seleccionadas,List<Pregunta> todasLasPreguntas) {
		List<Pregunta> noSeleccionadas = new ArrayList<Pregunta>(todasLasPreguntas);
		
		noSeleccionadas.removeAll(seleccionadas);
		
		return noSeleccionadas;
		
	}

	@Override
	public void obtenerPuntajeTotalDeUnCuestionario(Integer id_cuestionario) {
		Cuestionario aux= cuestionarioRepository.findById(id_cuestionario).get();
		Integer puntajeTotal=0;
		List<Integer> puntajes=ListadoDePuntajes(id_cuestionario);
		for(int i=0; i <puntajes.size();i++) {
			puntajeTotal+=puntajes.get(i);
		}
		
		aux.setPuntajeTotal(puntajeTotal);
		cuestionarioRepository.save(aux);
	}


	@Override
	public List<Integer> depurarPuntajesNoSeleccionados(List<Integer> puntajesSeleccionados) {
		// Asignar 0 a los puntajes de las preguntas no seleccionadas
	    List<Integer> puntajesDepurados = new ArrayList<>();
	    
	    for (int i = 0; i < puntajesSeleccionados.size(); i++) {
	        if(puntajesSeleccionados.get(i) != null) {
	        	puntajesDepurados.add(puntajesSeleccionados.get(i));
	        }
	    }
		return puntajesDepurados;
	}

	
}
