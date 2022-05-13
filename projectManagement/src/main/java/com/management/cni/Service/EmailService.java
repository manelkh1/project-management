package com.management.cni.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.management.cni.Entity.Session;
import com.management.cni.Entity.User;

@Service
public class EmailService {
	@Autowired
	private SessionService sessionService;
	@Autowired
	private JavaMailSender javaMailSender;
	

	public void sendHtmlMail(User user) throws MessagingException{
		Session session = sessionService.findUser(user);
		
		if(session != null) {
			String token = session.getToken();
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(user.getEmail());
			helper.setSubject("Email de verification");
			helper.setText("http://localhost:8090/activation?token="+token, true);
			javaMailSender.send(message);
		}
	}

}
