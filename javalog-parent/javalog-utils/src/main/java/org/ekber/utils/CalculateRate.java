package org.ekber.utils;

import java.text.NumberFormat;
import java.util.List;

import org.ekber.domain.UserRate;

public class CalculateRate {

	public static float calculateStarRate(List<UserRate> rateList) {

		NumberFormat nf = NumberFormat.getInstance();
		int sum = 0, count = 0;
		
		try {
			
			nf.setMaximumFractionDigits(2);

			
			count = rateList.size();

			for (UserRate u : rateList) {
				sum += u.getRate();
			}
			
			if (count != 0) {
				return new Float(nf.format(((float) sum / count)));
			} 
			
		} catch (NumberFormatException e) {
			return new Float(nf.format(((float) sum/count)).replace(',', '.'));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0f;

	}
}
