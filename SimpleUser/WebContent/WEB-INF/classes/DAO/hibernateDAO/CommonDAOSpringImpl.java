package DAO.hibernateDAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/*
 * A simple Generic DAO Implementation using hibernate and spring together conventions.  
 * The object is to test the generic DAO strategy, hibernate, and the hibernate XML and Annotation mappings.
 * Also to test the spring wiring.  
 */
public abstract class CommonDAOSpringImpl<T extends Serializable> implements CommonDAO<T> {
	
	protected EntityManager entityManager;

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
  }
  
	@Override
	@Transactional(readOnly = false)
	public void save(T entity) {
		//sessionFactory.getCurrentSession().save(entity);
		entityManager.persist(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public T find(Class<?> classy, String id) {
		return (T) entityManager.find(classy,  id);

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<T> findAll(Class<?> classy) {
		Query query = entityManager.createQuery("select o from :class o");
		query.setParameter(":class", classy);
		return query.getResultList();
	}
}
