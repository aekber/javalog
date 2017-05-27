
package org.ekber.utils.pointcuts;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.ekber.utils.mail.MailSenderImpl;



public class AuthenticationServicePointcuts
{
	private MailSenderImpl mailGonder;
	
	
	public void setMailGonder(MailSenderImpl mailGonder) {
		this.mailGonder = mailGonder;
	}

	
	public void afterUsernameOrPassword()
	{
		System.out.println("*** username or password method has exected ***");
	}

	
	public void beforeUsernameOrPassword()
	{
		System.out.println("*** user credidentals will be gotten ***");
	}
	
	
	public void afterExceptionThrows(Exception ex)
	{
		System.out.println("\n*** an exception has occured(sending by spring aop) ***\n");
		System.out.println(ExceptionUtils.getStackTrace(ex));
		
		mailGonder.hataMailiGonder(ex.toString());
	}
	
	
	public void afterLogout()
	{
		System.out.println("*** you've logout now! see you later, bye! ***");
	}
}
