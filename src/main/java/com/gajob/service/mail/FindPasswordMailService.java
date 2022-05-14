package com.gajob.service.mail;

import com.gajob.dto.mail.MailDto;

public interface FindPasswordMailService {

  MailDto createMailAndUpdatePassword(String email);

  void mailSend(MailDto mailDto);

  String updateToTempPassword(String email);

  String createTempPassword();
}
