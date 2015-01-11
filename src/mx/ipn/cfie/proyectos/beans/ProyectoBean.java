package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Actividad;
import mx.ipn.cfie.proyectos.model.Proyecto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: sg
 * Date: 30/09/12
 * Time: 10:00
 */
public class ProyectoBean {
	private Proyecto proyecto;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;

	public ProyectoBean() {
		proyecto = new Proyecto();
	}

	public String create(){
		proyecto = new Proyecto();
		return "new";
	}

	public String view(Proyecto _proyecto){
		this.proyecto = _proyecto;
		return "view";
	}

	public String edit(Proyecto _proyecto){
		this.proyecto = _proyecto;
		return "edit";
	}

	public String save(){
		double actividades = 0.0;
		for( Actividad a : proyecto.getActividades() ){
			actividades += a.getValor();
		}

		if( actividades != 100 ){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El total de actividades debe sumar 100%", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

		HibernateDao dao = new HibernateDao();
		proyecto.setFecha(new Date());
		dao.persist(proyecto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Nuevo Proyecto", proyecto.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Guardado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		return "list";
	}

	public String update(){
		double actividades = 0.0;
		for( Actividad a : proyecto.getActividades() ){
			actividades += a.getValor();
		}

		if( actividades != 100 ){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El total de actividades debe sumar 100%", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

		HibernateDao dao = new HibernateDao();
		dao.persist(proyecto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Actualizo Proyecto", proyecto.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Actualizado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		return "list";
	}

	public void remove(Proyecto _proyecto){
		HibernateDao dao = new HibernateDao();
		dao.delete(_proyecto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Elimino Proyecto", _proyecto.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Eliminado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("removed");
	}

	public List<Proyecto> getProyectos(){
		HibernateDao dao = new HibernateDao();
		List<Proyecto> proyectos = dao.find(Proyecto.class);
		if( proyectos == null )
			proyectos = new ArrayList<Proyecto>();
		return proyectos;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public BitacoraBean getBitacora() {
		return bitacora;
	}

	public void setBitacora(BitacoraBean bitacora) {
		this.bitacora = bitacora;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
}
