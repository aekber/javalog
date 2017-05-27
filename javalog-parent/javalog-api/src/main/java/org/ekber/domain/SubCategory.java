package org.ekber.domain;

// Generated 13.Eki.2010 10:25:23 by Hibernate Tools 3.3.0.GA

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;


public class SubCategory extends NamedNode implements Serializable, TreeNode {


	private static final long serialVersionUID = 1L;


	private Integer subcatId;
	private List<Article> articles;
	private Category category;
	private String subCategoryName;

	public SubCategory(Integer subcatId, List<Article> articles,
			Category category, String subCategoryName) {
		super();
		this.setType("subcategory");
		this.subcatId = subcatId;
		this.articles = articles;
		this.category = category;
		this.subCategoryName = subCategoryName;
	}
	
	public SubCategory() {
		super();
		this.setType("subcategory");
	}
	
	public Integer getSubcatId() {
		return subcatId;
	}

	public void setSubcatId(Integer subcatId) {
		this.subcatId = subcatId;
	}

	public List<Article> getArticles() {
		if(articles == null){
			articles = new ArrayList<Article>();
		}
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (subcatId != null ? subcatId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SubCategory)) {
			return false;
		}
		SubCategory other = (SubCategory) object;
		if ((this.subcatId == null && other.subcatId != null)
				|| (this.subcatId != null && !this.subcatId
						.equals(other.subcatId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SubCategory[id=" + subcatId + "]";
	}

	@Override
    public Enumeration<Article> children() {
		return Iterators.asEnumeration(articles.iterator());
    }

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return articles.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return articles.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return articles.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return category;
	}

	@Override
	public boolean isLeaf() {
		return articles.isEmpty();
	}

}
