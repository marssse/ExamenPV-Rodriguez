package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.edm.model.CuestionarioPregunta;
import ar.edu.unju.edm.model.Pregunta;

public interface ICuestionarioPreguntaService {
	public void cargarCuestionarioPregunta(CuestionarioPregunta cuestionarioPregunta);
	public void borrarCuestionarioPregunta(Integer idCuestionarioPregunta);
	public ArrayList<CuestionarioPregunta> mostrarTodosCuestionarioPregunta();
	public CuestionarioPregunta mostrarUnCuestionarioPregunta(Integer idCuestionarioPregunta);
	public CuestionarioPregunta modificarUnCuestionarioPregunta (Integer idCuestionarioPregunta);
	public void cargarPreguntasACuestionario (List<Integer> preguntasSeleccionadas,List<Integer> puntajesSeleccionados, Integer id_Cuestionario);
	public List<Pregunta> ListarPreguntasDeUnCuestionario(Integer id_Cuestionario);
	public List<Integer> ListarRespuestasDePreguntas(Integer id_Cuestionario);
	public List<Integer> ListadoDePuntajes (Integer id_Cuestionario);
	public List<Pregunta> ListarPreguntasNoSeleccionadas(List<Pregunta> seleccionadas, List<Pregunta> todasLasPreguntas);
	public void obtenerPuntajeTotalDeUnCuestionario(Integer id_cuestionario);
	
	public List<Integer> depurarPuntajesNoSeleccionados(List<Integer> puntajesSeleccionados);
}
