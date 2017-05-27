package org.ekber.service;

import org.ekber.dao.interfaces.ICommentsDao;
import org.ekber.domain.Comments;
import org.ekber.utils.mail.MailSenderImpl;
import org.ekber.service.interfaces.ICommentsService;
import org.springframework.transaction.annotation.Transactional;

public class CommentsServiceImpl implements ICommentsService {

	private ICommentsDao commentsDao;
	private MailSenderImpl mailGonder;

	public void setCommentsDao(ICommentsDao commentsDao) {
		this.commentsDao = commentsDao;
	}

	public void setMailGonder(MailSenderImpl mailGonder) {
		this.mailGonder = mailGonder;
	}

	@Override
	@Transactional
	public void addComment(Comments c) {
		commentsDao.addComment(c);
		mailGonder.commentMailiGonder(c);	
	}

	@Override
	@Transactional
	public void deleteComment(Comments c) {
		commentsDao.delete(c);
	}

	@Override
	@Transactional
	public Comments find(int id) {
		return commentsDao.findById(id);
	}

	@Override
	@Transactional
	public void updateComment(Comments c) {
		commentsDao.updateComment(c);
	}
	
	
}
