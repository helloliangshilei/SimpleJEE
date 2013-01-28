package DAO.hibernateDAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class CommonDAOImpl<T extends Serializable> implements CommonDAO<T> {

	protected Session getSession() {
		return HibernateUtility.getSession();
	}

	@Override
	public void save(T entity) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
	}

	@Override
	public void update(T entity) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T find(Class<?> classy, String id) {
		Session session = this.getSession();
		T t = (T) session.get(classy, id);
		return t;
	}

	@Override
	public void delete(T entity) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<?> classy) {
		Session session = this.getSession();
		Query queryResult = session.createQuery("from" + classy.getName());
		return queryResult.list();
	}
}
