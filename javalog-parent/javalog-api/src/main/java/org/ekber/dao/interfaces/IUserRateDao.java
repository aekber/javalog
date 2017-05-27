package org.ekber.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import org.ekber.domain.UserRate;

public interface IUserRateDao extends IGenericDao<UserRate, Serializable> {
	
	public void addRate(UserRate u);
	public boolean isUserIpExists(String ip, int articleId);
	public List<UserRate> getAllArticleRatesByArticle(int id);
	public List<UserRate> getRatesByProperty(String property, int id);
}
