package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Proyecto;
import mx.ipn.cfie.proyectos.model.Usuario;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * User: sg
 * Date: 28/10/12
 * Time: 11:41
 */
public class EvaluacionBean {

	private Proyecto proyecto;
	private SessionBean sessionBean;
	private ConfigBean configBean;
	private BitacoraBean bitacora;

	public EvaluacionBean(){
	}

	public String save(){
		HibernateDao dao = new HibernateDao();
		dao.persist(proyecto.getEvaluacion());
		proyecto.setEvaluado(true);
		dao.persist(proyecto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Evaluacion",  proyecto + "");

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Evaluado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("evaluado");
		return "ficha";
	}
	
	public String evaluar(Proyecto _proyecto){
		this.proyecto = _proyecto;
		return "evaluar";
	}

	public String ficha(Proyecto _proyecto){
		this.proyecto = _proyecto;
		return "ficha";
	}

	public List<Proyecto> getEvaluaciones(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Proyecto.class);
	}

	public List<Proyecto> getMisEvaluaciones(){
		HibernateDao dao = new HibernateDao();
		List<Proyecto> proyectos = dao.find(Proyecto.class);
		List<Proyecto> misproyectos = new ArrayList<Proyecto>();
		for( Proyecto p : proyectos ){
			if( p.getEvaluador().equals(sessionBean.getUsuario()) )
				misproyectos.add(p);
		}
		return misproyectos;
	}
	
	public List<Usuario> getEvaluadores(){
		HibernateDao dao = new HibernateDao();
		List<Usuario> users = dao.find(Usuario.class);
		List<Usuario> evaluadores = new ArrayList<Usuario>();
		for( Usuario u : users ){
			if( u.getRole().equals("Evaluador") ){
				evaluadores.add(u);
			}
		}
		return evaluadores;
	}

	public void onEdit(RowEditEvent event) {
		Proyecto _proyecto = (Proyecto) event.getObject();

		HibernateDao dao = new HibernateDao();
		dao.persist(_proyecto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Asignacion Evaluador",  proyecto + "\nEvaluador: " + _proyecto.getEvaluador());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluador Asignado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("asginado");
	}

	public Proyecto getProyecto(){
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public ConfigBean getConfigBean() {
		return configBean;
	}

	public void setConfigBean(ConfigBean configBean) {
		this.configBean = configBean;
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	public BitacoraBean getBitacora() {
		return bitacora;
	}

	public void setBitacora(BitacoraBean bitacora) {
		this.bitacora = bitacora;
	}
}
