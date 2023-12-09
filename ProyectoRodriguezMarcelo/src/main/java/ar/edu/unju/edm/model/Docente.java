package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Entity
public class Docente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Docente;
	
	private String contrasenia;
	
	@NotBlank(message= "nombre is required")
	@Size(max=40)
	private String nombre;
	
	@NotBlank(message= "apellido is required")
	@Size(max=20)
	private String apellido;
	
	@NotNull(message= "dni is required")
	private Integer dni;
	
	@NotNull(message= "telefono is required")
	private String telefono;
	
	private String direccion;
	
	@NotNull(message= "localidad is required")
	@Size(max=90)
	private String localidad;
	
	@NotNull(message= "materia is required")
	@Size(max=100)
	private String materia;
	
	@Email
	private String correo;
	
	@NotNull(message= "legajo is required")
	private Integer legajo;
	
	@Lob
	@Column(name="foto",columnDefinition="BLOB")
	private String foto;
	
	private String tipo;
	
	private Boolean estado;
	
	public Docente() {
	}

	public Docente(String contrasenia, String nombre, String apellido, Integer dni, String telefono, String direccion,
			String localidad, String materia, String correo, Integer legajo, String foto, Boolean estado) {
		super();
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.localidad = localidad;
		this.materia = materia;
		this.correo = correo;
		this.legajo = legajo;
		this.foto = foto;
		this.estado = estado;
	}

	public Integer getId_Docente() {
		return id_Docente;
	}
	
	public void setId_Docente(Integer id_Docente) {
		this.id_Docente=id_Docente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contraseña) {
		this.contrasenia = contraseña;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
