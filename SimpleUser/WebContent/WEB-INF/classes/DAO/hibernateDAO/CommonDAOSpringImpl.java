package DAO.hibernateDAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * A simple Generic DAO Implementation using hibernate and spring together conventions.  
 * The object is to test the generic DAO strategy, hibernate, and the hibernate XML and Annotation mappings.
 * Also to test the spring wiring.  
 */
public abstract class CommonDAOSpringImpl<T extends Serializable> implements CommonDAO<T> {
	
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	public void save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T find(Class<?> classy, String id) {
		T t;
		Session session = sessionFactory.getCurrentSession();
		t = (T) session.get(classy, id);
		return t;
	}

	@Override
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<?> classy) {
		Session session = sessionFactory.getCurrentSession();
		List<T> list = null;
		Query queryResult = session.createQuery("from " + classy.getName());
		list = queryResult.list();
		return list;
	}
}
