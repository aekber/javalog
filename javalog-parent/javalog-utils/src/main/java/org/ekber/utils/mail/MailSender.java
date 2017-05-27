package org.ekber.utils.mail;

import org.ekber.domain.Comments;
import org.springframework.mail.MailException;

public interface MailSender {
	public void hataMailiGonder(String error) throws MailException;
	public void commentMailiGonder(Comments c) throws MailException;
}
