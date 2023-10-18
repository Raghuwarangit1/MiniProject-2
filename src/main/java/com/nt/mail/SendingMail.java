package com.nt.mail;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendingMail {
	@Autowired
private JavaMailSender sender;
	
	public Boolean sendMail(String body,String msg,File file) {
		Boolean flag=false;
		try {
		MimeMessage message =sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setTo("wardhan778@gmail.com");
	   helper.setSubject(body);
		helper.setText(msg,true);
		FileSystemResource resource=new FileSystemResource(file);
		helper.addAttachment(resource.getFile().getName(), file);
		flag=true;
		sender.send(message);
		System.out.println("SendingMail.sendMail()");
		return flag;
		}//
		catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
	return flag;
	}
}
