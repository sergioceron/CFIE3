package mx.ipn.cfie.proyectos.dao;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.Serializable;
import java.util.List;

/**
 * User: sg
 * Date: 4/08/12
 * Time: 03:22 PM
 */
public class HibernateDao implements Dao {
	//private static final Logger logger = Logger.getLogger("main");
	private static Session session;

	static {
		try {
			SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
		} catch (Throwable e) {
			e.printStackTrace();
			//logger.error("Error in creating SessionFactory object.", e);
		}
	}

	public HibernateDao() {
	}

	public void persist(Object entity) {
		try{
			session.saveOrUpdate(entity);
		} catch(Exception e) {
			e.printStackTrace();
			if( e instanceof NonUniqueObjectException) {
				//logger.trace("Duplicated entity: " + entity);
			} else {
				//logger.error("Error on persist entity: " + entity, e);
			}
		}
	}

	public void delete(Object entity) {
		session.delete(entity);
	}

	public void persist(Object[] entities) {
		for (Object entity : entities) {
			persist(entity);
		}
	}

	public <T> List<T> find(Class<T> entityClass) {
		return find("from " + entityClass.getName());
	}

	public <T> T load(Class<T> entityClass, Serializable id) {
		final T entity = (T) session.load(entityClass, id);
		return entity;
	}

	public <T> List<T> find(String hql) {
		Query query = session.createQuery(hql);
		final List<T> entities = (List<T>) query.list();
		return entities;
	}

	public void flush(){
		try{
			session.flush();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public <T> T get(Class<T> entityClass, Serializable id){
		final T entity = (T) session.get(entityClass, id);
		return entity;
	}

    public Session getSession(){
        return session;
    }
}
