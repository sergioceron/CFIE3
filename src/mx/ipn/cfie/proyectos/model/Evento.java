package mx.ipn.cfie.proyectos.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: sg
 * Date: 31/10/12
 * Time: 18:22
 */
@Entity
public class Evento {
	private Long id;
	private String usuario;
	private String perfil;
	private String accion;
	private String detalles;
	private Date hora;

	public Evento() {
	}

	public Evento(String usuario, String perfil, String accion, String detalles, Date hora) {
		this.usuario = usuario;
		this.perfil = perfil;
		this.accion = accion;
		this.detalles = detalles;
		this.hora = hora;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@Lob
	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}
}
