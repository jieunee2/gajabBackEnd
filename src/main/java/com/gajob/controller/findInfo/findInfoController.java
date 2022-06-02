package com.gajob.controller.findInfo;

import com.gajob.dto.mail.MailDto;
import com.gajob.dto.user.UserDto;
import com.gajob.service.findInfo.FindIdService;
import com.gajob.service.findInfo.FindPasswordMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class findInfoController {

  private final FindPasswordMailService findPasswordMailService;

  private final FindIdService findIdService;

  // 임시 비밀번호를 담은 메일 전송
  @PostMapping("/find-password")
  public String sendEmail(@RequestBody MailDto mailDto) throws Exception {
    findPasswordMailService.mailSend(mailDto);
    findPasswordMailService.sendSimpleMessage(mailDto);

    return "send-mail-successful";
  }

  @GetMapping("/find-id")
  public ResponseEntity findId(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(findIdService.findId(userDto));
  }

}
