package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Docente;

@Service
public interface IDocenteService {
	public void cargarDocente(Docente unDocente);
	public void eliminarDocente(Integer codigoDocente);
	public Docente mostrarUnDocente(Integer codigoDocente);
	public ArrayList<Docente> listarDocentes();
	public void eliminarTodosLosDocentes();
	public Docente modificarUnDocente(Integer codigoDocente);

}
