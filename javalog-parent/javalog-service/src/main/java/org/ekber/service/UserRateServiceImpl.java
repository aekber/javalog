package org.ekber.service;

import java.text.NumberFormat;
import java.util.List;

import org.ekber.dao.interfaces.IUserRateDao;
import org.ekber.domain.UserRate;
import org.ekber.service.interfaces.IUserRateService;
import org.springframework.transaction.annotation.Transactional;


public class UserRateServiceImpl implements IUserRateService {

	private IUserRateDao userRateDao;

	public void setUserRateDao(IUserRateDao userRateDao) {
		this.userRateDao = userRateDao;
	}

	@Override
	@Transactional
	public void addRate(UserRate u) {
		userRateDao.addRate(u);
	}

	@Override
	@Transactional
	public List<UserRate> getAllArticleRatesByArticle(int id) {
		return userRateDao.getAllArticleRatesByArticle(id);
	}

	@Override
	@Transactional
	@Deprecated
	public boolean isUserIpExists(String ip, int articleId) {
		return userRateDao.isUserIpExists(ip, articleId);
	}
	
	@Override
	@Transactional
	public String calculateAverageRateByArticle(int id) {
		
		//List<UserRate> list = userRateDao.getRatesByProperty("articlesrate", id);
		List<UserRate> list = userRateDao.getAllArticleRatesByArticle(id);
		
		int sum = 0, count = 0;

		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);

		if (list != null) {
			for (UserRate r : list) {
				sum += r.getRate();
				count++;
			}

			if (count != 0) {
				return format.format((float) sum / count).replace(',', '.') + ";" + count;
			} else {
				return "";
			}
		}

		return "";
	}
	
}
