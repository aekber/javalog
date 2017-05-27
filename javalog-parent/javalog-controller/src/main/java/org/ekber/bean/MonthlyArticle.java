package org.ekber.bean;

import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import org.ekber.domain.NamedNode;
 
public class MonthlyArticle extends NamedNode implements Serializable, TreeNode {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Month month;
	private String articleId;

	public MonthlyArticle() {
		this.setType("art");
	}

	public MonthlyArticle(Month month) {
		super();
		this.setType("art");
		this.month = month;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
    public Enumeration<TreeNode> children() {
        return new Enumeration<TreeNode>() {
            public boolean hasMoreElements() {
                return false;
            }
 
            public TreeNode nextElement() {
                return null;
            }
        };
    }

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return month;
	}
	
	public TreeNode setParent(Month month) {
		return this.month = month;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
}