package org.ekber.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ekber.dao.interfaces.ITagDao;
import org.ekber.domain.Article;
import org.ekber.domain.Tag;
import org.hibernate.Query;


public class TagDaoImpl extends GenericDaoHibernate<Tag, Serializable> implements ITagDao {
	
	public TagDaoImpl() {
		super(Tag.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tag> getTags() {
		
		Query q = getSession().createQuery("from Tag t");		
		List<Tag> list = q.list();		
		
		return list;
	}

	@Override
	public List<Article> findByTag(String tagName) {
		List<Article> artList = new ArrayList<Article>();
		List<Tag> tagList = findByProperty("tagName", tagName);
		for(Tag tag : tagList){
			artList.add(tag.getArticleTag());
		}
		
		return artList;
	}
}
