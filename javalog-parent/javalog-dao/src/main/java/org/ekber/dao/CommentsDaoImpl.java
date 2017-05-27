package org.ekber.dao;

import java.io.Serializable;

import org.ekber.dao.interfaces.ICommentsDao;
import org.ekber.domain.Comments;

public class CommentsDaoImpl extends GenericDaoHibernate<Comments, Serializable> implements ICommentsDao {

	public CommentsDaoImpl() {
		super(Comments.class);
	}

	@Override
	public void addComment(Comments c) {
		persist(c);
	}

	@Override
	public void deleteComment(Comments c) {
		delete(c);		
	}

	@Override
	public void updateComment(Comments c) {
		update(c);
	}

}
