package org.ekber.dao;

import java.io.Serializable;

import org.ekber.dao.interfaces.ISubCategoryDao;
import org.ekber.domain.SubCategory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SubCategoryDaoImpl  extends GenericDaoHibernate<SubCategory, Serializable> implements ISubCategoryDao {

	public SubCategoryDaoImpl() {
		super(SubCategory.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SubCategory findBySubCategory(final String subCategory){
		SubCategory list = null;
		try {
			list = (SubCategory) getHibernateTemplate().execute(new HibernateCallback() {
						public Object doInHibernate(Session session) {
							Criteria criteria = session.createCriteria(SubCategory.class);
							criteria.add(Restrictions.eq("subCategoryName", subCategory));
							return criteria.uniqueResult();
						}
					});

		} catch (HibernateException e) {
			System.out.println("\nHibernate hatasi olustu :" + e.getMessage()+ "\n");
		}

		return list;
	}


}
