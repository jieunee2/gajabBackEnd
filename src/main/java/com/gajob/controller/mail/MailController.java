package com.gajob.controller.mail;

import com.gajob.dto.mail.MailDto;
import com.gajob.service.mail.EmailVerificationMailService;
import com.gajob.service.mail.EmailVerificationMailServiceImpl;
import com.gajob.service.mail.FindPasswordMailService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class MailController {

  private final FindPasswordMailService findPasswordMailService;
  private final EmailVerificationMailService emailVerificationMailService;

  // 임시 비밀번호를 담은 메일 전송
  @PostMapping("/find-password")
  public String sendEmail(@RequestBody MailDto mailDto) {
    findPasswordMailService.createMailAndUpdatePassword(mailDto.getEmail());
    findPasswordMailService.mailSend(mailDto);

    return "send-mail-successful";
  }

  // 교내 이메일 인증 코드 전송
  @PostMapping("/email")
  public String emailAuth(@RequestBody MailDto mailDto) throws Exception {
    emailVerificationMailService.createMessage(mailDto);
    emailVerificationMailService.sendSimpleMessage(mailDto);

    return "send-mail-successful";
  }

  //  // 교내 이메일 인증 코드 검증
  @PostMapping("/verifyCode")
  public String verifyCode(@RequestBody Map<String, String> code) {
    if (EmailVerificationMailServiceImpl.num.equals(code.get("code"))) {
      return "authentication-success";
    } else {
      return "authentication-failed";
    }
  }


}
