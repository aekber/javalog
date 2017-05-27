package org.ekber.dao;

import java.io.Serializable;
import java.util.List;

import org.ekber.dao.interfaces.ICategoryDao;
import org.ekber.domain.Category;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class CategoryDaoImpl  extends GenericDaoHibernate<Category, Serializable> implements ICategoryDao {

	public CategoryDaoImpl() {
		super(Category.class);
	}

	public Category findByCategory(final String category){
		Category list = null;
		try {
			list = (Category) getHibernateTemplate().execute(new HibernateCallback() {
						public Object doInHibernate(Session session) {
							Criteria criteria = session.createCriteria(Category.class);
							criteria.add(Restrictions.eq("articlecategory", category));
							return criteria.uniqueResult();
						}
					});

		} catch (HibernateException e) {
			System.out.println("\nHibernate hatasi olustu :" + e.getMessage()+ "\n");
		}

		return list;
	}

	@Override
	public List<Category> getAllCategory() {
		Query q = getSession().createQuery("from Category c");
		List<Category> list = q.list();
		
		return list;
	}

}
