package DAO.hibernateDAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public abstract class CommonDAOImpl<T extends Serializable> implements
		CommonDAO<T> {

	protected Session getSession() {
		return HibernateUtility.getSession();
	}

	@Override
	public void save(T entity) {
		Session session = this.getSession();
		session.save(entity);
	}

	@Override
	public void update(T entity) {
		Session session = this.getSession();
		session.saveOrUpdate(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T find(Class<?> classy, String id) {
		T t;
		Session session = this.getSession();
		t = (T) session.get(classy, id);
		return t;
	}

	@Override
	public void delete(T entity) {
		Session session = this.getSession();
		session.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<?> classy) {
		Session session = this.getSession();
		List<T> list = null;
		Query queryResult = session.createQuery("from " + classy.getName());
		list = queryResult.list();
		return list;
	}
}