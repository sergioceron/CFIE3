package mx.ipn.cfie.proyectos.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * User: sg
 * Date: 16/09/12
 * Time: 12:20 PM
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

}