package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Proyecto;
import mx.ipn.cfie.proyectos.model.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * User: sg
 * Date: 28/10/12
 * Time: 11:41
 */
public class EquiposBean {

	private Proyecto proyecto;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;
	
	public EquiposBean(){
	}
	
	public List<Proyecto> getEquipos(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Proyecto.class);
	}

	public List<Usuario> getDirectores(){
		HibernateDao dao = new HibernateDao();
		List<Usuario> users = dao.find(Usuario.class);
		List<Usuario> directores = new ArrayList<Usuario>();
		for( Usuario u : users ){
			if( u.getRole().equals("Director de Proyecto") ){
				directores.add(u);
			}
		}
		return directores;
	}
	
	public String save(){

		int estudiantes = 0;
		int investigadores = 0;
		HibernateDao dao = new HibernateDao();
		if( !proyecto.getAlumno1().empty() ){
			dao.persist(proyecto.getAlumno1());
			estudiantes++;
		}
		if( !proyecto.getAlumno2().empty() ){
			dao.persist(proyecto.getAlumno2());
			estudiantes++;
		}
		if( !proyecto.getAlumno3().empty() ){
			dao.persist(proyecto.getAlumno3());
			estudiantes++;
		}
		if( !proyecto.getAlumno4().empty() ){
			dao.persist(proyecto.getAlumno4());
			estudiantes++;
		}
		if( !proyecto.getAlumno5().empty() ){
			dao.persist(proyecto.getAlumno5());
			estudiantes++;
		}
		if( !proyecto.getInvestigador1().empty() ){
			dao.persist(proyecto.getInvestigador1());
			investigadores++;
		}
		if( !proyecto.getInvestigador2().empty() ){
			dao.persist(proyecto.getInvestigador2());
			investigadores++;
		}
		if( estudiantes > 0 && investigadores > 0 ){
			dao.flush();
			bitacora.addEvent(sessionBean.getUsuario(), "Nuevo grupo de trabajo", proyecto + "");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo de trabajo creado", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "list";
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe especificar al menos un alumno y un investigador", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return null;
	}
	
	public List<Usuario> getEquipo(){
		HibernateDao dao = new HibernateDao();
		List<Usuario> usuarios = dao.find("from Usuario u where u.proyecto=" + proyecto.getFolioSIP());
		List<Usuario> equipo = new ArrayList<Usuario>();
		for( Usuario u : usuarios ){
			if( u.getRole().equals("Alumno Participante") || u.getRole().equals("Investigador Participante")){
				equipo.add(u);
			}
		}
		return equipo;
	}

	public String create(Proyecto _proyecto){
		proyecto = _proyecto;
		return "new";
	}

	public String view(Proyecto _proyecto){
		proyecto = _proyecto;
		return "view";
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
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
