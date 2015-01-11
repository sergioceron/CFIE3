package mx.ipn.cfie.proyectos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: sg
 * Date: 2/11/12
 * Time: 18:11
 */
@Entity
public class Alumno {
	private Long id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String categoria;
	private String nivelEstudios;

	public Alumno() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(String nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public boolean empty(){
		return nombre.trim().length() == 0 || apellidoMaterno.trim().length() == 0 || apellidoPaterno.trim().length() == 0
				|| categoria.trim().length() == 0 || nivelEstudios.trim().length() == 0;
	}
}
