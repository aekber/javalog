package org.ekber.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.ekber.dao.interfaces.IGenericDao;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericDaoHibernate<T, ID extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, ID> {

	private final Class<T> clazz;

	public GenericDaoHibernate(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T instance) {
		return getHibernateTemplate().findByExample(instance);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criterion... criteria) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)throws HibernateException, SQLException {
						Criteria c = session.createCriteria(clazz);
						for (Criterion criterion : criteria)
							c.add(criterion);
						return c.list();
					}
				});
	}

	@Override
	public void persist(T transientInstance) {
		getSession().save(transientInstance);
	}

	@Override
	public void update(T persistentInstance) {
		getSession().update(persistentInstance);
	}

	@Override
	public void saveOrUpdate(T persistentInstance) {
		getSession().saveOrUpdate(persistentInstance);
	}

	@Override
	public void delete(T persistentInstance) {
		getSession().delete(persistentInstance);
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public T findById(int id) {
        
		T result = (T) getSession().get(clazz, id);
        if(result != null){
            Hibernate.initialize(result);
            return result;
        }else{ 
            return null;
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		final String query = String.format("select t from %s t where t.%s = :parameter", clazz.getSimpleName(), propertyName);
		return getSession().createQuery(query).setParameter("parameter", value).list();
	}

}
