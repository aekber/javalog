package org.ekber.service.interfaces;

import java.util.List;

import org.ekber.dao.interfaces.IJavalog;
import org.ekber.domain.Article;
import org.ekber.domain.Category;

public interface ICategoryService extends IJavalog {

	public Category findByCategory(String category);
	public Category findByCategoryId(int id);
	public List<Article> getRecentArticlesInPerCategory(List<Category> categories);
	public List<Category> getAllCategory();
	public void addNewCategory(Category c);
	public void updateCategory(Category c);
	public void deleteCategory(Category c);
}
