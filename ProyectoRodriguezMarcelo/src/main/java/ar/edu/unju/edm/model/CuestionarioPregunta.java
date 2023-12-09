package ar.edu.unju.edm.model;


import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Component
public class CuestionarioPregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_CuestionarioPregunta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Pregunta")
	Pregunta pregunta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Cuestionario")
	Cuestionario cuestionario;
	
	private Integer puntaje;

	public CuestionarioPregunta() {
		
	}

	public Integer getId_CuestionarioPregunta() {
		return id_CuestionarioPregunta;
	}

	public void setId_CuestionarioPregunta(Integer id_CuestionarioPregunta) {
		this.id_CuestionarioPregunta = id_CuestionarioPregunta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}
	
	
	
	
}
