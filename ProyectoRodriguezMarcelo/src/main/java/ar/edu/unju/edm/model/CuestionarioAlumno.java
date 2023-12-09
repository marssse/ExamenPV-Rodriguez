package ar.edu.unju.edm.model;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Component
@Entity
public class CuestionarioAlumno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_CuestionarioAlumno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Alumno")
	Alumno alumno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_Cuestionario")
	Cuestionario cuestionario;
	@NotNull(message="Puntaje Obtenido is required")
	private int puntajeObtenido;
	@NotNull(message="FechaRealizada is required")
	private String fechaRealizada;
	
	public CuestionarioAlumno () {
		
	}


	public Integer getId_CuestionarioAlumno() {
		return id_CuestionarioAlumno;
	}


	public void setId_CuestionarioAlumno(Integer id_CuestionarioAlumno) {
		this.id_CuestionarioAlumno = id_CuestionarioAlumno;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public Cuestionario getCuestionario() {
		return cuestionario;
	}


	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}


	public int getPuntajeObtenido() {
		return puntajeObtenido;
	}


	public void setPuntajeObtenido(int puntajeObtenido) {
		this.puntajeObtenido = puntajeObtenido;
	}


	public String getFechaRealizada() {
		return fechaRealizada;
	}


	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	
}
