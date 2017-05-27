package org.ekber.service.interfaces;

import org.ekber.dao.interfaces.IJavalog;
import org.ekber.domain.Comments;

public interface ICommentsService extends IJavalog {

	public void addComment(Comments c);
	public void deleteComment(Comments c);
	public Comments find(int id);
	public void updateComment(Comments c);
}
