package mx.ipn.cfie.proyectos.beans;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Config;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

/**
 * User: sg
 * Date: 31/10/12
 * Time: 18:25
 */
public class ConfigBean {
	private Config config;
	
	public ConfigBean(){
		HibernateDao dao = new HibernateDao();
		List<Config> configs = dao.find(Config.class);
		if( configs.size() == 0 )
			config = new Config();
		else
			config = configs.get(0);
	}

	public void save(){
		HibernateDao dao = new HibernateDao();
		dao.persist(config);
		dao.flush();
		//bitacora.addEvent(sessionBean.getUsuario(), "Cambio de periodo de evaluación", config.getPeriodoInicio() + "-" + config.getPeriodoFinal());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Periodo de Evaluación Asignado", "Invalid credentials");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isPeriodoActivo(){
		Date todayDate = new Date();
		return (todayDate.after(config.getPeriodoInicio()) && todayDate.before(config.getPeriodoFinal()));
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
