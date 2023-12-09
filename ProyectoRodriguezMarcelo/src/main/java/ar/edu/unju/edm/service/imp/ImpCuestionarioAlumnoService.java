package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CuestionarioAlumno;
import ar.edu.unju.edm.model.Pregunta;
import ar.edu.unju.edm.repository.CuestionarioAlumnoRepository;
import ar.edu.unju.edm.service.ICuestionarioAlumnoService;
import ar.edu.unju.edm.service.ICuestionarioPreguntaService;

@Service
public class ImpCuestionarioAlumnoService implements ICuestionarioAlumnoService {

	@Autowired
	CuestionarioAlumnoRepository cuesEstudianteRepository;
	@Autowired
	ICuestionarioPreguntaService cuesPreguntaService;
	
	
	@Override
	public void cargarCuestionarioAlumno(CuestionarioAlumno cuestionarioAlumno) {
		cuesEstudianteRepository.save(cuestionarioAlumno);
	}

	@Override
	public void eliminarCuestionarioAlumno(Integer IdCuestionarioAlumno) {
		CuestionarioAlumno aux = cuesEstudianteRepository.findById(IdCuestionarioAlumno).get();
		cuesEstudianteRepository.delete(aux);
	}

	@Override
	public ArrayList<CuestionarioAlumno> listarTodosCuestionariosAlumnos() {
		
		return (ArrayList<CuestionarioAlumno>) cuesEstudianteRepository.findAll();
	}

	@Override
	public CuestionarioAlumno listarUnCuestionarioAlumno(Integer IdCuestionarioAlumno) {
		Optional<CuestionarioAlumno> aux = Optional.of(new CuestionarioAlumno());
		aux = cuesEstudianteRepository.findById(IdCuestionarioAlumno);
		return aux.get();
	}

	@Override
	public CuestionarioAlumno modificarCuestionarioAlumno(Integer IdCuestionarioAlumno) {
		return null;
	}

	@Override
	public void eliminarTodosCuesAlumnos() {
		
	}

	@Override

	public String fechaActual() {
		LocalDate fechaActual = LocalDate.now();
		String fechaString = fechaActual.toString();

		return fechaString;
	}

	@Override
	public Integer calcularPuntajeObtenido(Integer idCuestionario, Map<String,String> opcionesElegidas) {
		List<Pregunta> preguntas = cuesPreguntaService.ListarPreguntasDeUnCuestionario(idCuestionario);
		List<Integer> opcionesCorrectas = cuesPreguntaService.ListarRespuestasDePreguntas(idCuestionario);
		List<Integer> puntajes= cuesPreguntaService.ListadoDePuntajes(idCuestionario);
		Integer puntajeObtenido=0;
		
		for(int i=0;i<preguntas.size();i++) {
			String opcion= opcionesElegidas.get("respuestasSeleccionadas[" + preguntas.get(i).getIdPregunta() + "]");
			Integer aux= Integer.parseInt(opcion);
			if(aux==opcionesCorrectas.get(i)) {
				puntajeObtenido+=puntajes.get(i);
			}
		}
		return puntajeObtenido;
	}

}
