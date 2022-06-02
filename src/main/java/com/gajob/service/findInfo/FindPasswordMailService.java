package com.gajob.service.findInfo;

import com.gajob.dto.mail.MailDto;
import javax.mail.internet.MimeMessage;

public interface FindPasswordMailService {

  void sendSimpleMessage(MailDto mailDto) throws Exception;

  MimeMessage mailSend(MailDto mailDto) throws Exception;

  String updateToTempPassword(String email);

  String createTempPassword();
}
