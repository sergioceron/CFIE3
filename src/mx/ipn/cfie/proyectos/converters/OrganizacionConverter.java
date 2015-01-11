package mx.ipn.cfie.proyectos.converters;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Organizacion;

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
@FacesConverter("mx.ipn.cfie.proyectos.converters.organizacionConverter")
public class OrganizacionConverter implements Converter, Serializable {
	private static final long serialVersionUID = -5137676309479323480L;

	protected HibernateDao dao = new HibernateDao();

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext facesContext, UIComponent component,
	                          String value) throws ConverterException {
		Organizacion entity;
		if (value == null) {
			entity = null;
		} else {
			entity = dao.get(Organizacion.class, Long.parseLong(value));
			if (entity == null) {
				System.out.println("There is no organizacion with name:  " + value);
			}
		}
		return entity;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
	                          Object value) throws ConverterException {
		if (value != null && !(value instanceof Organizacion)) {
			return value.toString();
		}
		if (value == null) {
			return "";
		}

		Organizacion entity = (Organizacion) value;
		return entity.getId().toString() == null ? "" : entity.getId().toString();
	}


}
