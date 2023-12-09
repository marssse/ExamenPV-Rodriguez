package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Pregunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPregunta;
	private String pregunta;
	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	private short dificultad;
	private Integer opcionCorrecta;
	private Boolean estado;
	
	public Pregunta() {
		super();
	}

	public Pregunta(Integer idPregunta, String pregunta, String opcion1, String opcion2, String opcion3, String opcion4,
			short dificultad, Integer opcionCorrecta, Boolean estado) {
		super();
		this.idPregunta = idPregunta;
		this.pregunta = pregunta;
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
		this.dificultad = dificultad;
		this.opcionCorrecta = opcionCorrecta;
		this.estado = estado;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getOpcion1() {
		return opcion1;
	}

	public void setOpcion1(String opcion1) {
		this.opcion1 = opcion1;
	}

	public String getOpcion2() {
		return opcion2;
	}

	public void setOpcion2(String opcion2) {
		this.opcion2 = opcion2;
	}

	public String getOpcion3() {
		return opcion3;
	}

	public void setOpcion3(String opcion3) {
		this.opcion3 = opcion3;
	}

	public String getOpcion4() {
		return opcion4;
	}

	public void setOpcion4(String opcion4) {
		this.opcion4 = opcion4;
	}

	public short getDificultad() {
		return dificultad;
	}

	public void setDificultad(short dificultad) {
		this.dificultad = dificultad;
	}

	public Integer getOpcionCorrecta() {
		return opcionCorrecta;
	}

	public void setOpcionCorrecta(Integer opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	

}