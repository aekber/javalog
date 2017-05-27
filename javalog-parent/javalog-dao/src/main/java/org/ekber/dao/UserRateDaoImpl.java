package org.ekber.dao;

import java.io.Serializable;
import java.util.List;

import org.ekber.dao.interfaces.IUserRateDao;
import org.ekber.domain.UserRate;
import org.hibernate.Query;


public class UserRateDaoImpl extends GenericDaoHibernate<UserRate, Serializable> implements IUserRateDao {
	
	public UserRateDaoImpl() {
		super(UserRate.class);
	}

	public void addRate(UserRate rate){
		persist(rate);
	}

	@Override
	public List<UserRate> getAllArticleRatesByArticle(int id) {
		Query q = getSession().createQuery("from UserRate u where u.articlesrate = :id");
		q.setInteger("id", id);
		return q.list();
	}

	@Override
	@Deprecated
	public boolean isUserIpExists(String session, int articleId) {
		
		Query q = getSession().createSQLQuery("select * from user_rate where ratedarticleid = :articleId and ratersession = :sess");
		q.setString("sess", session);
		q.setInteger("articleId", articleId);
		
		List<UserRate> list = q.list();
		
		return list.size() > 0 ? true : false;

	}	
	
	@Override
	public List<UserRate> getRatesByProperty(String property, int id) {
		return findByProperty(property, id);
	}
}
