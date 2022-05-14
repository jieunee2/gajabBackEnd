package com.gajob.controller.mail;

import com.gajob.dto.mail.MailDto;
import com.gajob.service.mail.FindPasswordMailService;
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

  private final FindPasswordMailService findPasswordMailService;

  // 임시 비밀번호를 담은 메일 전송
  @Transactional
  @PostMapping("/find-password")
  public String sendEmail(@RequestBody MailDto mailDto) {
    findPasswordMailService.createMailAndUpdatePassword(mailDto.getEmail());
    findPasswordMailService.mailSend(mailDto);

    return "send-mail-successful";
  }

}
