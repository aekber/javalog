package org.ekber.utils;

import java.util.Comparator;

import org.ekber.domain.Article;

public class SortByArticleName implements Comparator<Article> {

	@Override
	public int compare(Article a1, Article a2) {
		return a1.getArticleTag().compareTo(a2.getArticleTag());
	}

}
