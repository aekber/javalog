package org.ekber.service;

import org.ekber.dao.interfaces.ISubCategoryDao;
import org.ekber.domain.SubCategory;
import org.ekber.service.interfaces.ISubCategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

public class SubCategoryServiceImpl implements ISubCategoryService,InitializingBean {

	private ISubCategoryDao subCategoryDao;

	public void setSubCategoryDao(ISubCategoryDao subCategoryDao) {
		this.subCategoryDao = subCategoryDao;
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(subCategoryDao == null)
			throw new IllegalStateException("SubCategoryDao nesnesi set edilmelidir.");
		
	}


	@Override
	@Transactional
	public SubCategory findBySubCategory(String SubCategory) {
		return subCategoryDao.findBySubCategory(SubCategory);
	}

	@Override
	@Transactional
	public void addNewSubCategory(SubCategory c) {
		subCategoryDao.persist(c);
	}


	@Override
	@Transactional
	public void updateSubCategory(SubCategory c) {
		subCategoryDao.update(c);
	}
	
	@Override
	@Transactional
	public void deleteSubCategory(SubCategory c) {
		subCategoryDao.delete(c);
	}


	@Override
	@Transactional
	public SubCategory findBySubCategoryId(int id) {
		return subCategoryDao.findById(id);
	}
	
}
