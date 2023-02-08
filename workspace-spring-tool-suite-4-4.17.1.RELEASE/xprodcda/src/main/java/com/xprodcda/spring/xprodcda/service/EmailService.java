package com.xprodcda.spring.xprodcda.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import static com.xprodcda.spring.xprodcda.constant.EmailConstant.*;

@Service
public class EmailService {
	
	private Message createEmail(String firstname, String password, String email)
	throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email,false));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(CC_EMAIL,false));
		message.setSubject(EMAIL_SUBJECT);
		message.setText("Welcome " + firstname + ", \n\n your new account password is : " + password);
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
	
	private Session getEmailSession() {
		Properties properties = System.getProperties();
		properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
		properties.put(SMTP_AUTH, true);
		properties.put(SMTP_PORT, DEFAULT_PORT);
		properties.put(SMTP_STARTTLS_ENABLE, true);
		properties.put(SMTP_STARTTLS_REQUIRED, true);
	    return Session.getInstance(properties, null);
				
	}

}
