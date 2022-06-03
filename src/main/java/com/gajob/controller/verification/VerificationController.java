package com.gajob.controller.verification;

import com.gajob.dto.mail.MailDto;
import com.gajob.service.verification.EmailVerificationMailService;
import com.gajob.service.verification.EmailVerificationMailServiceImpl;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class VerificationController {

  private final EmailVerificationMailService emailVerificationMailService;


  // 교내 이메일 인증 코드 전송
  @PostMapping("/student-email")
  public String emailAuth(@RequestBody MailDto mailDto) throws Exception {
    emailVerificationMailService.createMessage(mailDto);
    emailVerificationMailService.sendSimpleMessage(mailDto);

    return "send-mail-successful";
  }

  // 교내 이메일 인증 코드 검증
  @PostMapping("/studen-email-verify")
  public String verifyCode(@RequestBody Map<String, String> code) {
    if (EmailVerificationMailServiceImpl.num.equals(code.get("code"))) {
      return "authentication-success";
    } else {
      return "authentication-failed";
    }
  }

}
