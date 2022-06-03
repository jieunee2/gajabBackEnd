package com.gajob.service.verification;

import com.gajob.dto.mail.MailDto;
import javax.mail.internet.MimeMessage;

public interface EmailVerificationMailService {

  void sendSimpleMessage(MailDto mailDto) throws Exception;

  MimeMessage createMessage(MailDto mailDto) throws Exception;
}
