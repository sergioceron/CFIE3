package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Evento;
import mx.ipn.cfie.proyectos.model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: sg
 * Date: 31/10/12
 * Time: 18:25
 */
public class BitacoraBean {
	private List<Evento> filtrados;

	public BitacoraBean(){
		filtrados = new ArrayList<Evento>();
	}
	
	public void addEvent(Usuario usuario, String accion, String detalles){
		Evento e = new Evento(usuario.getUsername(), usuario.getRole(), accion, detalles, new Date());

		HibernateDao dao = new HibernateDao();
		dao.persist(e);
		dao.flush();
	}

	public List<Evento> getEvents(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Evento.class);
	}

	public List<Evento> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Evento> filtrados) {
		this.filtrados = filtrados;
	}
}
