package org.study.spring.service.email;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.study.spring.model.email.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender; // mail 발송 객체
	
	@Override
	public void sendMail(EmailDTO dto) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			// 수신자
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			// 발신자
			msg.addFrom(new InternetAddress[] {
					new InternetAddress(dto.getSenderMail(), dto.getSenderName(), "utf-8")
			});
			// 제목
			msg.setSubject(dto.getSubject(), "utf-8");
			// 본문
			msg.setText(dto.getMessage(), "utf-8");
			mailSender.send(msg); // 전송
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
