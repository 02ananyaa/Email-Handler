package com.email.sender.serviceImpl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.email.sender.service.EmailService;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	private JavaMailSender mail;

	public EmailServiceImpl(JavaMailSender mail) {
		this.mail = mail;
	}

	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("0213ananya@gmail.com");
		mail.send(simpleMailMessage);
		logger.info("Email has been sent to " + to);
	}

	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);// we can pass array here aslo
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("0213ananya@gmail.com");
		mail.send(simpleMailMessage);
		logger.info("Email sent");

	}

	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		MimeMessage mimeMessage = mail.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("0213ananya@gmail.com");
			helper.setText(htmlContent, true);
			mail.send(mimeMessage);
			logger.info("Email has been sent ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Email sent");

	}

	public void sendEmailWithFile(String to, String subject,String message, File file) {
		MimeMessage mimeMessage = mail.createMimeMessage();
		try {
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message);
			FileSystemResource fileSystemResource=new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mail.send(mimeMessage);
			logger.info("Email has been sent");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
