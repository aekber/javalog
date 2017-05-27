package org.ekber.service.interfaces;

import java.util.List;

import org.ekber.dao.interfaces.IJavalog;
import org.ekber.domain.Article;
import org.ekber.domain.TagDTO;


public interface ITagService extends IJavalog {

	public List<TagDTO> getTags();
	public List<Article> getArticlesByTag(String tagName);
	
}
