package org.ekber.domain;

import java.io.Serializable;

public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer tagId;
	private String tagName;
	private Article articleTag;
	
	public Tag(Integer tagId, String tagName, Article articleTag) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.articleTag = articleTag;
	}

	public Tag() {

	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Article getArticleTag() {
		return articleTag;
	}

	public void setArticleTag(Article articleTag) {
		this.articleTag = articleTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + "]";
	}

	
}
