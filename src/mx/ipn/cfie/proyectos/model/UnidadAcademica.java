package mx.ipn.cfie.proyectos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: sg
 * Date: 30/09/12
 * Time: 10:41
 */
@Entity
public class UnidadAcademica {
	private Long id;
	private String unidad;

	public UnidadAcademica() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
}
