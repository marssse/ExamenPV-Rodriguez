package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pregunta;

@Service
public interface IPreguntaService {
	public void cargarPregunta(Pregunta unaPregunta);
	public void eliminarPregunta(Integer idPregunta);
	public ArrayList<Pregunta> listarPreguntas ();
	public Pregunta listarUnaPregunta(Integer idPregunta);
	public Pregunta modificarPregunta (Integer idPregunta);
}