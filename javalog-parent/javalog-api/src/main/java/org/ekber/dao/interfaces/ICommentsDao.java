package org.ekber.dao.interfaces;

import java.io.Serializable;

import org.ekber.domain.Comments;

public interface ICommentsDao extends IGenericDao<Comments, Serializable> {

	public void addComment(Comments c);
	public void deleteComment(Comments c);
	public void updateComment(Comments c);
}
