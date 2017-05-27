package org.ekber.service.interfaces;

import java.util.List;

import org.ekber.dao.interfaces.IJavalog;
import org.ekber.domain.Article;


public interface IArticleService extends IJavalog {

	public List<Article> getAllArticles();
	public List<Article> getMostPopularArticles(List<Article> list);
	public List<Article> getLatestArticles(List<Article> list);
	public void addNewArticle(Article a);
	public void updateArticle(Article a);
	public void deleteArticle(Article a);
	public Article findByArticleTag(String article);
}
