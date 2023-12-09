package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.CuestionarioPregunta;
import ar.edu.unju.edm.model.Cuestionario;

@Repository
public interface CuestionarioPreguntaRepository extends CrudRepository<CuestionarioPregunta, Integer>{
	 List<CuestionarioPregunta> findAllByCuestionario(Cuestionario cuestionario);
	 
}