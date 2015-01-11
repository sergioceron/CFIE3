package mx.ipn.cfie.proyectos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: sg
 * Date: 29/09/12
 * Time: 18:07
 */
@Entity
public class Recurso {
	private Long id;
	private String nombre;
	private String institucion;
	private String unidadAcademica;
	private String perfil;
	private String actividad;

	public Recurso() {
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

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getUnidadAcademica() {
		return unidadAcademica;
	}

	public void setUnidadAcademica(String unidadAcademica) {
		this.unidadAcademica = unidadAcademica;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
}
