package org.ekber.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.ekber.domain.Article;

public interface IArticleDao extends IGenericDao<Article, Serializable> {

	public List<Article> getAllArticles();
	//public List<Article> getMostPopularArticles();
	public void update(Article a);
	public Article findByArticleTag(String article);
}
