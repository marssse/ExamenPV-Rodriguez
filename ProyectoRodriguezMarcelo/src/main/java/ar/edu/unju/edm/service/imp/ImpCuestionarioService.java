package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cuestionario;
import ar.edu.unju.edm.repository.CuestionarioRepository;
import ar.edu.unju.edm.service.ICuestionarioService;

@Service
public class ImpCuestionarioService implements ICuestionarioService {
	
	@Autowired
	CuestionarioRepository cuestionarioRepository;

	@Override
	public void cargarCuestionario(Cuestionario unCuestionario) {
		unCuestionario.setEstado(true);
		
		cuestionarioRepository.save(unCuestionario);
		
	}

	@Override
	public void eliminarCuestionario(Integer idCuestionario) {
		Optional<Cuestionario> auxiliar = Optional.of(new Cuestionario());
		auxiliar=cuestionarioRepository.findById(idCuestionario);
		auxiliar.get().setEstado(false);
		cuestionarioRepository.save(auxiliar.get());
	}

	@Override
	public Cuestionario mostrarUnCuestionario(Integer idCuestionario) {
		Optional<Cuestionario> auxiliar = Optional.of(new Cuestionario());
		auxiliar= cuestionarioRepository.findById(idCuestionario);
		return auxiliar.get();
	}

	@Override
	public ArrayList<Cuestionario> listarCuestionarios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		    Object principal = authentication.getPrincipal();
		    UserDetails entidadAutenticada= null;
		    // Verificar si el objeto principal es una instancia de la entidad que deseas guardar
		    if (principal instanceof UserDetails) {
		        entidadAutenticada = (UserDetails) principal;
		    }

		    
		    ArrayList<Cuestionario> cuestionarios = (ArrayList<Cuestionario>) cuestionarioRepository.findAll();
	        ArrayList<Cuestionario> cuestionariosDeDocente= new ArrayList<>();
		    
		        for(int i=0; i<cuestionarios.size();i++) {
		        	if(cuestionarios.get(i).getDocente().getDni()==Integer.parseInt(entidadAutenticada.getUsername())){
		        		cuestionariosDeDocente.add(cuestionarios.get(i));
		        	}
		        }
		        return cuestionariosDeDocente;
}
	
	@Override
	public ArrayList<Cuestionario> listarCuestionariosTodos() {
		        return (ArrayList<Cuestionario>) cuestionarioRepository.findAll();
}

	@Override
	public void eliminarTodosLosCuestionarios() {
		
	}

}
