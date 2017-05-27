package org.ekber.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ekber.dao.interfaces.ICategoryDao;
import org.ekber.domain.Article;
import org.ekber.domain.Category;
import org.ekber.domain.SubCategory;
import org.ekber.service.interfaces.ICategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

public class CategoryServiceImpl implements ICategoryService,InitializingBean {

	private ICategoryDao categoryDao;

	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(categoryDao == null)
			throw new IllegalStateException("categoryDao nesnesi set edilmelidir.");
		
	}

	@Override
	@Transactional
	public List<Category> getAllCategory() {
		return categoryDao.getAllCategory();	
	}


	@Override
	@Transactional
	public Category findByCategory(String category) {
		return categoryDao.findByCategory(category);
	}


	@Override
	public List<Article> getRecentArticlesInPerCategory(List<Category> catList) {
		
		List<Article> list = new ArrayList<Article>();
		
		for(Category c : catList){
			List<SubCategory> scList = c.getSubCat();
				for(SubCategory s : scList){
					if(s.getArticles().size() != 0){
						List<Article> artList = s.getArticles();
						Collections.sort(artList, new Article());
						list.add(artList.get(0));
					}
				}
		}
		Collections.sort(list, new Article());
		//TODO: Anasayfada 10 tane baslik cok olur diye 5 yaptim
		return list.subList(0, 5);
	}


	@Override
	@Transactional
	public void addNewCategory(Category c) {
		categoryDao.persist(c);
	}


	@Override
	@Transactional
	public void updateCategory(Category c) {
		categoryDao.update(c);
	}
	
	@Override
	@Transactional
	public void deleteCategory(Category c) {
		categoryDao.delete(c);
	}


	@Override
	@Transactional
	public Category findByCategoryId(int id) {
		return categoryDao.findById(id);
	}
	
	
}
