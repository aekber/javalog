package org.ekber.bean;

import java.util.List;
import java.util.Map;


/**
 * A wrapper class around several properties, to be assigned to RichFaces'
 * <code>TreeNode</code>s' <code>data</code> property.
 */
public class TreeNodeData {
	
	private String text;
	private String type;
	private Object data;
	private int parentSize;
	private int leafSize;

	/**
	 * The type of a <code>TreeNode</code>.
	 */
	public enum TreeNodeType {

		parent,

		leaf,
		
		subleaf
	}
	

	/**
	 * Convenience constructor.
	 * @param text Textual description, may be used as a tree node's label.
	 * @param type The type (parent, child) of the node.
	 * @param data Any underlying, additional, information (i.e., to be
	 * processed on selection events).
	 */
	public TreeNodeData(String text, TreeNodeType type, Object data) {
		super();
		this.text = text;
		this.setType(type);
		this.data = data;
	}
	
	
	public TreeNodeData(String text, TreeNodeType type, Object data, Map<String, List<String>> archiveMap) {
		super();
		this.text = text;
		this.setType(type);
		this.data = data;
		
		int i = 0;
		
		
		if("parent".equals(type.name()) && archiveMap != null){
				
			for(Map.Entry<String, List<String>> ent : archiveMap.entrySet()){
					
				for(String s : ent.getValue()){
					i++;
				}
			}
		}
		
		this.setParentSize(i);
	}


	/**
	 * Textual description, may be used as a tree node's label.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Textual description, may be used as a tree node's label.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * The type (parent, child) of the node.
	 */
	public String getType() {
		return type;
	}

	/**
	 * The type (parent, child) of the node.
	 */
	public void setType(TreeNodeType type) {
		this.type = type.name();
	}

	/**
	 * Any underlying, additional, information (i.e., to be processed on
	 * selection events).
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Any underlying, additional, information (i.e., to be processed on
	 * selection events).
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public int getParentSize() {
		return parentSize;
	}

	public void setParentSize(int parentSize) {
		this.parentSize = parentSize;
	}

	public int getLeafSize() {
		return leafSize;
	}

	public void setLeafSize(int leafSize) {
		this.leafSize = leafSize;
	}	
	
}
