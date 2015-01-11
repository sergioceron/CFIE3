package mx.ipn.cfie.proyectos.generators;

import mx.ipn.cfie.proyectos.dao.HibernateDao;
import mx.ipn.cfie.proyectos.model.Proyecto;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * User: sg
 * Date: 1/11/12
 * Time: 22:19
 */
public class FolioGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {

		HibernateDao dao = new HibernateDao();
		int i = 0;
		List<Proyecto> proyectos = dao.find(Proyecto.class);
		for( Proyecto p : proyectos ){
			Integer folio = Integer.parseInt(p.getFolioSIP().substring(6));
			if( folio >= i )
				i = folio;
		}

		Calendar calendar = Calendar.getInstance();
		return String.format("%04d%02d%04d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, i + 1);
	}
}
