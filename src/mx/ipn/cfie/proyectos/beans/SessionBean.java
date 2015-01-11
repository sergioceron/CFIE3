package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.model.Usuario;
import mx.ipn.cfie.proyectos.util.HibernateUtil;
import org.hibernate.Session;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * User: sg
 * Date: 29/10/12
 * Time: 17:09
 */
public class SessionBean {
	private Usuario usuario;
	private BitacoraBean bitacora;

	public SessionBean(){
		usuario = new Usuario();
		usuario.setRole("Invitado");
	}

	public String login(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Usuario u = (Usuario) session.get(Usuario.class, usuario.getUsername());
		String result = null;
		if( u != null )   {
			if( u.getPassword().equals(usuario.getPassword()) ){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido, " + u, ""));
				bitacora.addEvent(u, "Inicio sesion", u + "");
				setUsuario(u);
				result = "panel";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"La contraseña es incorrecta", "La contraseña es incorrecta"));
				result = "index";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El usuario no existe", "El usuario no existe"));
			result = "index";
		}
		return result;
	}

	public String logoff(){
		bitacora.addEvent(usuario, "Cerro sesion", usuario + "");
		usuario = new Usuario();
		usuario.setRole("Invitado");
		return "index";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BitacoraBean getBitacora() {
		return bitacora;
	}

	public void setBitacora(BitacoraBean bitacora) {
		this.bitacora = bitacora;
	}
}
