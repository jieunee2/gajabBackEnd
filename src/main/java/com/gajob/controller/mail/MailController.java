package com.gajob.controller.mail;

import com.gajob.dto.mail.MailDto;
import com.gajob.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class MailController {

  private final MailService mailService;

  // 이메일 보내기
  @Transactional
  @PostMapping("/find-password")
  public String sendEmail(@RequestBody MailDto mailDto) {
    mailService.createMailAndUpdatePassword(mailDto.getEmail());
    mailService.mailSend(mailDto);

    return "send-mail-successful";
  }

}
