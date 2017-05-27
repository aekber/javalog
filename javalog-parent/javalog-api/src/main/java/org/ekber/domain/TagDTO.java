package org.ekber.domain;

import java.io.Serializable;

public class TagDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tagName;
	private Integer tagFrequency;
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getTagFrequency() {
		return tagFrequency;
	}
	public void setTagFrequency(Integer tagFrequency) {
		this.tagFrequency = tagFrequency;
	}
	
	
}
