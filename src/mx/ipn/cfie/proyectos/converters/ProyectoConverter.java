package mx.ipn.cfie.proyectos.converters;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Proyecto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

/**
 * User: sg
 * Date: 27/10/12
 * Time: 16:58
 */
@FacesConverter("mx.ipn.cfie.proyectos.converters.proyectoConverter")
public class ProyectoConverter implements Converter, Serializable {
	private static final long serialVersionUID = -5137676309479323480L;

	protected HibernateDao dao = new HibernateDao();

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext facesContext, UIComponent component,
	                          String value) throws ConverterException {
		Proyecto entity;
		if (value == null) {
			entity = null;
		} else {
			entity = dao.get(Proyecto.class, value);
			if (entity == null) {
				System.out.println("There is no proyecto with folio:  " + value);
			}
		}
		return entity;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
	                          Object value) throws ConverterException {
		if (value != null && !(value instanceof Proyecto)) {
			return value.toString();
		}
		if (value == null) {
			return "";
		}

		Proyecto entity = (Proyecto) value;
		return entity.getFolioSIP() == null ? "" : entity.getFolioSIP();
	}


}
