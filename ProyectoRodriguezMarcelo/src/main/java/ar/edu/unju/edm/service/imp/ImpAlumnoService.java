package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Alumno;
import ar.edu.unju.edm.repository.AlumnoRepository;
import ar.edu.unju.edm.service.IAlumnoService;

@Service
@Qualifier("servicioAlumnoEnMySQL")
public class ImpAlumnoService implements IAlumnoService{
	
	@Autowired
	AlumnoRepository alumnoRepository;

	@Override
	public void cargarAlumno(Alumno unAlumno) {
		unAlumno.setEstado(true);

		if(alumnoRepository.findByDni(unAlumno.getDni()).isPresent()) {
			return;
		}
		
		alumnoRepository.save(unAlumno);
	}

	@Override
	public void eliminarUnAlumno(Integer idAlumno) {
		Optional<Alumno> aux = Optional.of(new Alumno());
		aux =alumnoRepository.findById(idAlumno);
		aux.get().setEstado(false);
		alumnoRepository.save(aux.get());
	}

	@Override
	public Alumno mostrarUnAlumno(Integer idAlumno) {
		Optional<Alumno> aux = Optional.of(new Alumno());
		aux = alumnoRepository.findById(idAlumno);
		
		return aux.get();
	}

	@Override
	public ArrayList<Alumno> listarAlumnos() {
		return (ArrayList<Alumno>) alumnoRepository.findByEstado(true);
	}

	@Override
	public void eliminarTodosLosAlumnos() {
		
	}
	
	@Override
	public Alumno modificarUnAlumno(Integer idAlumno) {
		return null;
	}

}
