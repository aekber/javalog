package org.ekber.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.ekber.dao.interfaces.IArticleDao;
import org.ekber.domain.Article;
import org.ekber.service.interfaces.IArticleService;
import org.springframework.transaction.annotation.Transactional;


public class ArticleServiceImpl implements IArticleService {

	private IArticleDao articleDao;

	public void setArticleDao(IArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	

	@Override
	public List<Article> getMostPopularArticles(List<Article> list) {
		List<Article> popularArticles = new ArrayList<Article>();
		Comparator<Article> comp = new Comparator<Article>() {

			@Override
			public int compare(Article a1, Article a2) {
				return a2.getPopularity().compareTo(a1.getPopularity());
			}
		};
		
		Collections.sort(list, comp);
		for (int i = 0; i < 5; i++) {
			popularArticles.add(list.get(i));
		}

		return popularArticles;

	}
	
	@Override
	@Transactional
	public void updateArticle(Article a){
		articleDao.update(a);
	}


	@Override
	@Transactional
	public List<Article> getAllArticles() {
		return articleDao.getAllArticles();
	}


	@Override
	@Transactional
	public Article findByArticleTag(String article) {
		return articleDao.findByArticleTag(article);
	}


	@Override
	public List<Article> getLatestArticles(List<Article> list) {
		List<Article> latestArticles = new ArrayList<Article>();
		Comparator<Article> comp = new Comparator<Article>() {

			@Override
			public int compare(Article a1, Article a2) {
				return a2.getInsertdate().compareTo(a1.getInsertdate());
			}
		};
		
		Collections.sort(list, comp);
		for (int i = 0; i < 5; i++) {
			latestArticles.add(list.get(i));
		}

		return latestArticles;
	}


	@Override
	@Transactional
	public void addNewArticle(Article a) {
		articleDao.persist(a);		
	}


	@Override
	@Transactional
	public void deleteArticle(Article a) {
		articleDao.delete(a);
	}
	
}
