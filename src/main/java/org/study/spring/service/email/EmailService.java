package org.study.spring.service.email;

import org.study.spring.model.email.EmailDTO;

public interface EmailService {
	public void sendMail(EmailDTO dto);
}
