package org.ekber.service.interfaces;

import org.ekber.dao.interfaces.IJavalog;
import org.ekber.domain.SubCategory;

public interface ISubCategoryService extends IJavalog {

	public SubCategory findBySubCategory(String subCategory);
	public SubCategory findBySubCategoryId(int id);
	public void addNewSubCategory(SubCategory c);
	public void updateSubCategory(SubCategory c);
	public void deleteSubCategory(SubCategory c);
}
