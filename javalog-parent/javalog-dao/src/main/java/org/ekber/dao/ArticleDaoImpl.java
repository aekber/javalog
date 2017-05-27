package org.ekber.dao;

import java.io.Serializable;
import java.util.List;

import org.ekber.dao.interfaces.IArticleDao;
import org.ekber.domain.Article;
import org.hibernate.Query;


public class ArticleDaoImpl extends GenericDaoHibernate<Article, Serializable> implements IArticleDao {
	
	
	public ArticleDaoImpl() {
		super(Article.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> getAllArticles() {
		
		Query q = getSession().createQuery("from Article a");		
		List<Article> list = q.list();		
		
		return list;
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Article> getMostPopularArticles() {
//		
//		Query q = getSession().getNamedQuery("getPopularArticles");
//		q.setMaxResults(5);
//		List<Article> list = q.list();
//		
//		return list;
//	}


	@Override
	public Article findByArticleTag(String article) {
		Query q = getSession().createQuery("FROM Article a where a.articleTag = :article");
		q.setString("article", article);
		
		return (Article) q.uniqueResult();
	}


}
