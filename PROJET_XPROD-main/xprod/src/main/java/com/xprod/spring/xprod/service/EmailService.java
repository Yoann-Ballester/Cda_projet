package com.xprod.spring.xprod.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

import static com.xprod.spring.xprod.constant.EmailConstant.*;

@Service
public class EmailService {
	
	private Message createEmail(String firstname, String password, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(Message.RecipientType.TO , InternetAddress.parse(email));
		message.setRecipients(Message.RecipientType.CC , InternetAddress.parse(CC_EMAIL));
		message.setSubject(EMAIL_SUBJECT);
		message.setText("Bienvenue " + firstname + ", \n\n Votre nouveau mot de passe est : "+ password );
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
	
	public void sendNewPasswordEmail(String firstname, String password, String email) throws MessagingException {
		
		Message message = createEmail(firstname, password, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFERT_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}
	
	

}
