package org.ekber.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.ekber.domain.Article;
import org.ekber.domain.Tag;

public interface ITagDao extends IGenericDao<Tag, Serializable> {
	
	public List<Tag> getTags();
	public List<Article> findByTag(String tagName);
}
