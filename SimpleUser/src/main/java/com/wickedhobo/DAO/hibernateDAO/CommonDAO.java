package com.wickedhobo.DAO.hibernateDAO;

import java.io.Serializable;
import java.util.List;

public interface CommonDAO<T extends Serializable> {
	
	void save(T entity);
	void update(T entity);
	T find(Class<?> classy, String id);
	void delete(T entity);
	List<T> findAll(Class<?> classy);

}
