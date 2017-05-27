package org.ekber.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.ekber.domain.Category;

public interface ICategoryDao extends IGenericDao<Category, Serializable> {

	public Category findByCategory(String category);
	public List<Category> getAllCategory();
}
