package ar.edu.unju.edm.service.imp;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Docente;
import ar.edu.unju.edm.repository.DocenteRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class LoginService implements UserDetailsService{
	
	@Autowired
	DocenteRepository docenteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id_Docente) throws UsernameNotFoundException {
		
		//busqueda del usuario
		Docente docenteEncontrado = docenteRepository.findById(Integer.parseInt(id_Docente)).orElseThrow(()-> new UsernameNotFoundException("Usuario Invalido"));
		
		//definir autorizaciones
		List<GrantedAuthority> tipos = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(docenteEncontrado.getTipo());
		tipos.add(grantedAuthority);
		
		//definir el usuario en sesion
		UserDetails docenteEnSesion = new User(id_Docente,docenteEncontrado.getContrasenia(),tipos);
		
		return docenteEnSesion;
	}

}
