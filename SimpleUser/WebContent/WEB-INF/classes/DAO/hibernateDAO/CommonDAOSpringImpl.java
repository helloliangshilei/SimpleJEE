package DAO.hibernateDAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/*
 * A simple Generic DAO Implementation using hibernate and spring together conventions.  
 * The object is to test the generic DAO strategy, hibernate, and the hibernate XML and Annotation mappings.
 * Also to test the spring wiring.  
 */
public abstract class CommonDAOSpringImpl<T extends Serializable> implements CommonDAO<T> {
	
	@PersistenceContext(unitName="userPU")
	protected EntityManager entityManager;

	@Override
	public void save(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Class<?> classy, String id) {
		return (T) entityManager.find(classy, id);

	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<?> classy) {
		Query query = entityManager.createQuery("from " + classy.getName());
		return query.getResultList();
	}
}
