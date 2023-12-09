package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Alumno;


@Repository

public interface AlumnoRepository extends CrudRepository <Alumno,Integer>{
	public List<Alumno> findByEstado (Boolean estado);
	public Optional<Alumno> findByDni (Integer dni);
}
