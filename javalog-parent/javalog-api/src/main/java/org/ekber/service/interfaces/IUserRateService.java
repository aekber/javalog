package org.ekber.service.interfaces;

import java.util.List;

import org.ekber.dao.interfaces.IJavalog;
import org.ekber.domain.UserRate;


public interface IUserRateService extends IJavalog {

	public void addRate(UserRate a);
	public boolean isUserIpExists(String ip, int articleId);
	public List<UserRate> getAllArticleRatesByArticle(int id);
	public String calculateAverageRateByArticle(int id);
	
}
