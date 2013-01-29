package DAO.hibernateDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//TODO OK, this all works basically.  No need to move all Tx logic to a lower level impl class.
public abstract class CommonDAOImpl<T extends Serializable> implements CommonDAO<T> {

	protected Session getSession() {
		return HibernateUtility.getSession();
	}

	@Override
	public void save(T entity) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
	}

	@Override
	public void update(T entity) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T find(Class<?> classy, String id) {
		Session session = null;
		Transaction tx = null;
		T t = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			t = (T) session.get(classy, id);
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
		return t;
	}

	@Override
	public void delete(T entity) {
		Session session = null;
		Transaction tx = null;
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<?> classy) {
		Session session = null;
		Transaction tx = null;
		List<T> list = new ArrayList<T>();
		try {
			session = this.getSession();
			tx = session.beginTransaction();
			Query queryResult = session.createQuery("from " + classy.getName());
			//tx.commit();
			list = queryResult.list();
		}
		catch (Exception e) {
			//tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
}
