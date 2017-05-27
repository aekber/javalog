package org.ekber.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;


public interface IGenericDao <T, ID extends Serializable> extends IJavalog {
	
	public List<T> findByExample(T example);

    public List<T> findByCriteria(Criterion... criteria);

	public void persist(T transientInstance);

	public void update(T persistentInstance);

	public void saveOrUpdate(T persistentInstance);

	public void delete(T persistentInstance);
	
	public T findById(int id);
}

