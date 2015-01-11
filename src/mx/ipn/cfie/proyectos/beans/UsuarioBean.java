package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * User: sg
 * Date: 16/09/12
 * Time: 12:17 PM
 */
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private String repassword;
	private BitacoraBean bitacora;
	private SessionBean sessionBean;

	public UsuarioBean() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String create(){
		usuario = new Usuario();
		return "new";
	}

	public String view(Usuario _usuario){
		this.usuario = _usuario;
		return "view";
	}

	public String edit(Usuario _usuario){
		this.usuario = _usuario;
		return "edit";
	}

	public String save(){

        if( !usuario.getPassword().equals(repassword) ){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las 2 contraseñas deben coincidir", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        HibernateDao dao = new HibernateDao();
        dao.persist(usuario);
        dao.flush();
		repassword = null;

		bitacora.addEvent(sessionBean.getUsuario(), "Nuevo usuario", usuario + "");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Creado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		return "list";
	}

	public void eliminar(Usuario _usuario){
		HibernateDao dao = new HibernateDao();
		dao.delete(_usuario);
		dao.flush();
		repassword = null;

		bitacora.addEvent(sessionBean.getUsuario(), "Elimino usuario", _usuario + "");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("deleted");
	}

	public String update(){

		if( repassword != null && repassword.trim().length() > 0 ){
			usuario.setPassword(repassword);
		}

		HibernateDao dao = new HibernateDao();
		dao.persist(usuario);
		dao.flush();
		repassword = null;

		bitacora.addEvent(sessionBean.getUsuario(), "Actualizo usuario", usuario + "");

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Actualizado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("persisted");
		return "list";
	}
	
	public String getPermisos(){
		String permisos = "";
		if( usuario.getRole() != null ){
			if( usuario.getRole().equals("Administrador") ){
				permisos = "El Aministrador, puede acceder\n" +
						"a los siguientes mòdulos:\n" +
						"\tGestión de Usuarios\n" +
						"\tGestión de Proyectos\n" +
						"\tGestión de Organizaciones\n" +
						"\tConsultar equipos de trabajo\n" +
						"\tGestión de Bitácora\n" +
						"\tProceso de Evaluaciòn";
			} else if( usuario.getRole().equals("Colaborador") )  {
				permisos = "El Colaborador, puede acceder a los siguientes módulos:\n" +
						"\tGestión de Proyectos\n" +
						"\tConsultar equipos de trabajo\n" +
						"\tGestión de Organizaciones";
			} else if( usuario.getRole().equals("Director de Proyecto") )  {
				permisos = "El Director de Proyecto, puede acceder a los siguientes módulos:\n" +
						"\tGestión de Proyectos (consulta)\n" +
						"\tConsultar equipos de trabajo\n" +
						"\tGestión de Organizaciones (consulta)";
			} else if( usuario.getRole().equals("Evaluador") )  {
				permisos = "El Evaluador, puede acceder a los siguientes módulos:\n" +
						"\tGestión de Proyectos (consulta)\n" +
						"\tProceso de Evaluación ";
			} else if( usuario.getRole().equals("Jefe de Departamento") )  {
				permisos = "El Jefe de Departamento, puede acceder a los siguientes módulos:\n" +
						"\tGestión de Usuarios\n" +
						"\tGestión de Proyectos\n" +
						"\tGestión de Organizaciones\n" +
						"\tProceso de Evaluación\n" +
						"\tConsultar equipos de trabajo ";
			} else if( usuario.getRole().equals("Representante") )  {
				permisos = "El Representante, puede acceder a los siguientes módulos:\n" +
						"\tGestión de Proyectos (consulta)\n" +
						"\tGestión de Organizaciones (consulta)\n" +
						"\tConsultar equipos de trabajo";
			}
		}
		return permisos;
	}

	public void changeRole(){
		System.out.println("change role");
	}

	public List<Usuario> getUsuarios(){
		HibernateDao dao = new HibernateDao();
		return dao.find(Usuario.class);
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
