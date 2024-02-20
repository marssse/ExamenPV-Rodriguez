package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Alumno;
import ar.edu.unju.edm.model.Docente;
import ar.edu.unju.edm.service.IAlumnoService;
import ar.edu.unju.edm.service.IDocenteService;

@SpringBootApplication
public class ProyectoRodriguezMarceloApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoRodriguezMarceloApplication.class, args);	
	}

	@Autowired
	Docente docente;
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	Alumno alumno;
	
	@Autowired
	IAlumnoService alumnoService;
	
	@Override
	public void run (String... args) throws Exception{

		docente.setEstado(true);
		docente.setApellido("Ramirez");
		docente.setContrasenia("nicolas");
		docente.setDni(45881380);
		docente.setCorreo("rama@gmail.com");
		docente.setMateria("programacion");
		docente.setNombre("Mateo");
		docente.setTipo("ADMIN");
		docente.setTelefono("3884633949");
		docente.setDireccion("cuyaya");
		docente.setLegajo(1234);
		docente.setLocalidad("Jujuy");
		docenteService.cargarDocente(docente);
	}
	
}
