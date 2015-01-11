package mx.ipn.cfie.proyectos.converters;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Usuario;

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
@FacesConverter("mx.ipn.cfie.proyectos.converters.usuarioConverter")
public class UsuarioConverter implements Converter, Serializable {
	private static final long serialVersionUID = -5137676309479323480L;

	protected HibernateDao dao = new HibernateDao();

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext facesContext, UIComponent component,
	                          String value) throws ConverterException {
		Usuario entity;
		if (value == null) {
			entity = null;
		} else {
			entity = dao.get(Usuario.class, value);
			if (entity == null) {
				System.out.println("There is no usuario with username:  " + value);
			}
		}
		return entity;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
	                          Object value) throws ConverterException {
		if (value != null && !(value instanceof Usuario)) {
			return value.toString();
		}
		if (value == null) {
			return "";
		}

		Usuario entity = (Usuario) value;
		return entity.getUsername() == null ? "" : entity.getUsername();
	}

}
