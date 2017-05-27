package org.ekber.dao.interfaces;

import java.io.Serializable;

import org.ekber.domain.SubCategory;

public interface ISubCategoryDao extends IGenericDao<SubCategory, Serializable> {

	public SubCategory findBySubCategory(String subCategory);
}
