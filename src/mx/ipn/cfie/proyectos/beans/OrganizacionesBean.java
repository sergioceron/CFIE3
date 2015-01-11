package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Municipio;
import mx.ipn.cfie.proyectos.model.Organizacion;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * User: sg
 * Date: 7/10/12
 * Time: 15:45
 */
public class OrganizacionesBean {
	private Organizacion organizacion;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;

	public OrganizacionesBean() {
		organizacion = new Organizacion();
	}

	public String create(){
		organizacion = new Organizacion();
		return "new";
	}

	public String view(Organizacion _organizacion){
		this.organizacion = _organizacion;
		return "view";
	}

	public String edit(Organizacion _organizacion){
		this.organizacion = _organizacion;
		return "edit";
	}

	public String save(){
		HibernateDao dao = new HibernateDao();
		dao.persist(organizacion);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Nueva organizacion", organizacion.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizacion Creada", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		organizacion = new Organizacion();
		return "list";
	}

	public String update(){
		HibernateDao dao = new HibernateDao();
		dao.persist(organizacion);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Actualizo organizacion", organizacion.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizacion Actualizada", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		return "list";
	}

	public void remove(Organizacion _organizacion){
		HibernateDao dao = new HibernateDao();
		dao.delete(_organizacion);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Elimino organizacion", _organizacion.toString());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizacion eliminada", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Organizacion> getOrganizaciones(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Organizacion.class);
	}

    public List<Municipio> getMunicipios(){
        HibernateDao dao = new HibernateDao();
        return dao.find("from Municipio m where m.estado='" + organizacion.getEstado() + "'");
    }

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
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

