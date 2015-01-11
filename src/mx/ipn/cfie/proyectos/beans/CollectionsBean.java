package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.*;
import org.primefaces.event.RowEditEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;

/**
 * User: sg
 * Date: 30/09/12
 * Time: 11:19
 */
public class CollectionsBean implements Serializable {

	private Departamento departamento;
	private Puesto puesto;
	private UnidadAcademica unidadAcademica;

	private BitacoraBean bitacora;
	private SessionBean sessionBean;

	public CollectionsBean() {
		departamento = new Departamento();
		unidadAcademica = new UnidadAcademica();
		puesto = new Puesto();
	}

	public void onEditDepartamentos(RowEditEvent event) {
		Departamento _departamento = (Departamento) event.getObject();

		HibernateDao dao = new HibernateDao();
		dao.persist(_departamento);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Edito Departamento",  _departamento.getDepartamento());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento Actualizado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

    public void removeDepartamento(Departamento _departamento){
        HibernateDao dao = new HibernateDao();
        dao.delete(_departamento);
        dao.flush();

        bitacora.addEvent(sessionBean.getUsuario(), "Elimino Departamento",  _departamento.getDepartamento());

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento Eliminado", "Invalid credentials");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public void onEditUnidadAcademica(RowEditEvent event) {
		UnidadAcademica _unidadAcademica = (UnidadAcademica) event.getObject();

		HibernateDao dao = new HibernateDao();
		dao.persist(_unidadAcademica);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Edito Unidad Academica",  _unidadAcademica.getUnidad());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unidad Academica Actualizada", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

    public void removeUnidadAcademica(UnidadAcademica _ua){
        HibernateDao dao = new HibernateDao();
        dao.delete(_ua);
        dao.flush();

        bitacora.addEvent(sessionBean.getUsuario(), "Elimino Unidad Academica",  _ua.getUnidad());

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unidad Academica Eliminada", "Invalid credentials");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEditPuesto(RowEditEvent event) {
		Puesto _puesto = (Puesto) event.getObject();

		HibernateDao dao = new HibernateDao();
		dao.persist(_puesto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Edito Puesto",  _puesto.getPuesto());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Puesto Actualizado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

    public void removePuesto(Puesto _puesto){
        HibernateDao dao = new HibernateDao();
        dao.delete(_puesto);
        dao.flush();

        bitacora.addEvent(sessionBean.getUsuario(), "Elimino Puesto",  _puesto.getPuesto());

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Puesto Eliminado", "Invalid credentials");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public void saveDepartamento(){
		HibernateDao dao = new HibernateDao();
		dao.persist(departamento);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Nuevo Departamento", departamento.getDepartamento());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento Creado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
	}

	public void saveUnidadAcademica(ActionEvent actionEvent){
		HibernateDao dao = new HibernateDao();
		dao.persist(unidadAcademica);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Nueva Unidad Academica",  unidadAcademica.getUnidad());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nueva Unidad Academica", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
	}

	public void savePuesto(ActionEvent actionEvent){
		HibernateDao dao = new HibernateDao();
		dao.persist(puesto);
		dao.flush();

		bitacora.addEvent(sessionBean.getUsuario(), "Nuevo Puesto",  puesto.getPuesto());

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Puesto Creado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
	}

	public List<Departamento> getDepartamentos(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Departamento.class);
	}

	public List<UnidadAcademica> getUnidadesAcademicas(){
		HibernateDao dao = new HibernateDao();
		return dao.find(UnidadAcademica.class);
	}

	public List<Actividad> getActividades(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Actividad.class);
	}

	public List<Recurso> getRecursos(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Recurso.class);
	}

	public List<Presupuesto> getPresupuestos(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Presupuesto.class);
	}

	public List<Puesto> getPuestos(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Puesto.class);
	}

	public UnidadAcademica getUnidadAcademica() {
		return unidadAcademica;
	}

	public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
		this.unidadAcademica = unidadAcademica;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
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
