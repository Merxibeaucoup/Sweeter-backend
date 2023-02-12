package com.edgar.sweeter.services;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service 
public class MailService {
	
	
	@Autowired
	private  Gmail gmail;
	
	
	public void sendEmail(String toAddress, String subject, String content) throws Exception {
		
		Properties props =  new Properties();
		
		Session session = Session.getInstance(props, null);
		
		MimeMessage email = new MimeMessage(session);
		
		try {
			email.setFrom(new InternetAddress("edgarbriandt@gmail.com"));
			email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(toAddress));
			email.setSubject(subject);
			email.setText(content);
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			email.writeTo(buffer);
			
			byte[] rawMessageBytes = buffer.toByteArray();
			
			String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
			
			Message message = new Message();
			message.setRaw(encodedEmail);
			
			message = gmail.users().messages().send("me", message).execute();
		}catch(GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			
			if(error.getCode()== 403) {
				throw e;
			}
			
		}
	}
	
}