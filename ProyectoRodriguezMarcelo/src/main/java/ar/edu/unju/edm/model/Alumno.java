package ar.edu.unju.edm.model;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Component
@Entity
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Alumno;
	@NotBlank(message="apellido is required")
	@Size(max=20)
	private String apellido;
	@NotBlank(message="nombre is required")
	@Size(max=30)
	private String nombre;
	@NotNull(message="dni is required")
	@Min(value=8)
	private Integer dni;
	private String telefono;
	@Email
	private String correo;
	@NotNull(message="curso is required")
	private short curso;
	private char division;
	private String direccion;
	private Boolean estado;
	
	
	
	public Alumno() {
		super();
	}

	public Alumno(Integer id_Alumno,
			@NotBlank(message = "apellido is required") @Size(max = 20) String apellido,
			@NotBlank(message = "nombre is required") @Size(max = 30) String nombre,
			@NotNull(message = "dni is required") @Min(8) Integer dni, String telefono, @Email String correo,
			@NotNull(message = "curso is required") short curso, char division, String direccion, Boolean estado) {
		super();
		this.id_Alumno = id_Alumno;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.curso = curso;
		this.division = division;
		this.direccion = direccion;
		this.estado = estado;
	}

	public Integer getId_Alumno() {
		return id_Alumno;
	}


	public void setId_Alumno(Integer id_Alumno) {
		this.id_Alumno = id_Alumno;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getDni() {
		return dni;
	}


	public void setDni(Integer dni) {
		this.dni = dni;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public short getCurso() {
		return curso;
	}


	public void setCurso(short curso) {
		this.curso = curso;
	}


	public char getDivision() {
		return division;
	}


	public void setDivision(char division) {
		this.division = division;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	
	
}
