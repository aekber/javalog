package org.ekber.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;

// Generated 13.Eki.2010 10:25:23 by Hibernate Tools 3.3.0.GA

/**
 * Category generated by hbm2java
 */

public class Category extends NamedNode implements Serializable, TreeNode {

	private static final long serialVersionUID = 1L;

	private Integer catid;
	private String articlecategory;
	private List<SubCategory> subCat;

	public Category(Integer catid, String articlecategory, List<SubCategory> subCat) {
		super();
		this.setType("category");
		this.catid = catid;
		this.articlecategory = articlecategory;
		this.subCat = subCat;
	}

	public Category() {
		super();
		this.setType("category");
	}

	public Integer getCatid() {
		return this.catid;
	}

	public void setCatid(Integer catid) {
		this.catid = catid;
	}

	public String getArticlecategory() {
		return this.articlecategory;
	}

	public void setArticlecategory(String articlecategory) {
		this.articlecategory = articlecategory;
	}

	public List<SubCategory> getSubCat() {
		if (subCat == null) {
			subCat = new ArrayList<SubCategory>();
		}
		return subCat;
	}

	public void setSubCat(List<SubCategory> subCat) {
		this.subCat = subCat;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catid != null ? catid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.catid == null && other.catid != null) || (this.catid != null && !this.catid.equals(other.catid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category[id=" + catid + "]";
    }

	@Override
    public Enumeration<SubCategory> children() {
		return Iterators.asEnumeration(subCat.iterator());
    }

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return subCat.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return subCat.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return subCat.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return subCat.isEmpty();
	}

}
