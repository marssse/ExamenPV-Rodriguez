package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Docente;
import ar.edu.unju.edm.repository.DocenteRepository;
import ar.edu.unju.edm.service.IDocenteService;

@Service
@Qualifier("servicioEnMySQL")
public class ImpDocenteService implements IDocenteService {
	
	@Autowired
	DocenteRepository docenteRepository;

	@Override
	public void cargarDocente(Docente unDocente) {
		unDocente.setEstado(true);
		unDocente.setTipo("ADMIN");
		String pw = unDocente.getContrasenia();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		unDocente.setContrasenia(encoder.encode(pw));
		docenteRepository.save(unDocente);
	}

	@Override
	public void eliminarDocente(Integer codigoDocente) {
		Optional<Docente> auxiliar = Optional.of(new Docente());
		auxiliar=docenteRepository.findById(codigoDocente);
		auxiliar.get().setEstado(false);
		docenteRepository.save(auxiliar.get());
	}

	@Override
	public Docente mostrarUnDocente(Integer codigoDocente) {
		Optional<Docente> auxiliar = Optional.of(new Docente());
		auxiliar= docenteRepository.findById(codigoDocente);
		return auxiliar.get();
	}

	@Override
	public ArrayList<Docente> listarDocentes() {
		return (ArrayList<Docente>) docenteRepository.findByEstado(true);
	}

	@Override
	public void eliminarTodosLosDocentes() {
		
	}

	@Override
	public Docente modificarUnDocente(Integer codigoDocente) {
		return null;
	}

}
