package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.model.Actividad;
import mx.ipn.cfie.proyectos.model.Proyecto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * User: sg
 * Date: 7/10/12
 * Time: 15:45
 */
public class ActividadesBean {
	private Actividad actividad;
	private ProyectoBean proyectoBean;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;

	public ActividadesBean() {
		actividad = new Actividad();
	}

	public void saveActividad(){

        if( actividad.getValor() > 100 || actividad.getValor() < 1 ){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El porcentaje de la actividad debe ser entre 1 y 100", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }

		Proyecto proyecto = proyectoBean.getProyecto();
		proyecto.getActividades().add(actividad);
		bitacora.addEvent(sessionBean.getUsuario(), "Nueva Actividad", actividad.toString());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad Creada", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		actividad = new Actividad();
	}

	public void removeActividad(Actividad _actividad){
		Proyecto proyecto = proyectoBean.getProyecto();
		proyecto.getActividades().remove(_actividad);
		bitacora.addEvent(sessionBean.getUsuario(), "Elimino Actividad", _actividad.toString());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actividad Eliminada", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("removed");
	}

	public List<Actividad> getActividades(){
		Proyecto proyecto = proyectoBean.getProyecto();
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades.addAll(proyecto.getActividades());
		return actividades;
	}
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public ProyectoBean getProyectoBean() {
		return proyectoBean;
	}

	public void setProyectoBean(ProyectoBean proyectoBean) {
		this.proyectoBean = proyectoBean;
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
