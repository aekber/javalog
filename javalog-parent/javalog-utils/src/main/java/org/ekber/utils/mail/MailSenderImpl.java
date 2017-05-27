package org.ekber.utils.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.ekber.domain.Comments;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailSenderImpl implements MailSender {

	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Override
	public void commentMailiGonder(Comments c){
		MimeMessagePreparator preparator = new CommentMailiGonder(c);
		this.mailSender.send(preparator);
	}
	
	@Override
	public void hataMailiGonder(String error) throws MailException {
		MimeMessagePreparator preparator = new HataMailiGonder(error);
		this.mailSender.send(preparator);
	}
	
	
	class CommentMailiGonder implements MimeMessagePreparator{

		private Comments c;
		
		public CommentMailiGonder(Comments c){
			this.c = c;
		}
		
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setTo("aekbercelik@gmail.com");
			message.setFrom("aekbercelik@gmail.com");
			message.setSubject("Yeni Yorum Yapildi");
			
			Map<String, Comments> model = new HashMap<String, Comments>();
			model.put("comment", c);
			
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "commentMailTemplate.vm", model);
			
			message.setText(text, true);
			
		}
		
	}
	
	class HataMailiGonder implements MimeMessagePreparator{

		private String errorDetails;
		
		public HataMailiGonder(String errorDetails){
			this.errorDetails = errorDetails;
		}
		
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setTo("aekbercelik@gmail.com");
			message.setFrom("aekbercelik@gmail.com");
			message.setSubject("Hata Mesaji !");
			
			Map<String, String> model = new HashMap<String, String>();
			model.put("hata", errorDetails);
			
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "hataMailTemplate.vm", model);
			
			message.setText(text, true);
			
		}
		
	}

}