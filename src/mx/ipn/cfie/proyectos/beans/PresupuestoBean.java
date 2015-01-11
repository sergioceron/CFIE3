package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.model.Presupuesto;
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
public class PresupuestoBean {
	private Presupuesto presupuesto;
	private ProyectoBean proyectoBean;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;

	public PresupuestoBean() {
		presupuesto = new Presupuesto();
	}

	public void save(){
		Proyecto proyecto = proyectoBean.getProyecto();
		proyecto.getPresupuestos().add(presupuesto);

		bitacora.addEvent(sessionBean.getUsuario(), "Nuevo Prespuesto", presupuesto.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo Creado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		presupuesto = new Presupuesto();
	}

	public void remove(Presupuesto _presupuesto){
		Proyecto proyecto = proyectoBean.getProyecto();
		proyecto.getPresupuestos().remove(_presupuesto);

		bitacora.addEvent(sessionBean.getUsuario(), "Elimino Prespuesto", _presupuesto.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Activo Eliminado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("removed");
	}

	public List<Presupuesto> getPresupuestos(){
		Proyecto proyecto = proyectoBean.getProyecto();
		List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		presupuestos.addAll(proyecto.getPresupuestos());
		return presupuestos;
	}
	
	public double getTotal(){
		double total = 0.0;
		for( Presupuesto presupuesto : proyectoBean.getProyecto().getPresupuestos() ){
			total += presupuesto.getMonto();
		}
		return total;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
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
