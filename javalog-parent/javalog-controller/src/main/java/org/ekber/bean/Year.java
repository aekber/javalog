package org.ekber.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.ekber.domain.NamedNode;

import com.google.common.collect.Iterators;
 
public class Year extends NamedNode implements Serializable, TreeNode {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<Month> monthList = new ArrayList<Month>();

	public Year() {
		this.setType("year");
	}

	public List<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}

	@Override
    public Enumeration<Month> children() {
        return Iterators.asEnumeration(monthList.iterator());
    }

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return monthList.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return monthList.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return monthList.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return monthList.isEmpty();
	}
}