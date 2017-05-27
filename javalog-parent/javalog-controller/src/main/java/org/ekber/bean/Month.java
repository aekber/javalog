package org.ekber.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.ekber.domain.NamedNode;

import com.google.common.collect.Iterators;
 
public class Month extends NamedNode implements Serializable, TreeNode {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Year year;
    protected List<MonthlyArticle> artList = new ArrayList<MonthlyArticle>();

	public Month() {
		this.setType("month");
	}

	public Month(Year year, List<MonthlyArticle> artList) {
		super();
		this.setType("month");
		this.year = year;
		this.artList = artList;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public List<MonthlyArticle> getArtList() {
		return artList;
	}

	public void setArtList(List<MonthlyArticle> artList) {
		this.artList = artList;
	}

	@Override
    public Enumeration<MonthlyArticle> children() {
        return Iterators.asEnumeration(artList.iterator());
    }

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return artList.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return artList.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return artList.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return year;
	}
	
	public TreeNode setParent(Year year) {
		return this.year = year;
	}

	@Override
	public boolean isLeaf() {
		return artList.isEmpty();
	}
}