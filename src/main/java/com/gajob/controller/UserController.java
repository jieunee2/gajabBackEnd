package com.gajob.controller;

import com.gajob.service.user.UserService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  // 회원가입
  @PostMapping("/join")
  public Long signup(@RequestBody Map<String, String> user) {
    return userService.signup(user);
  }

  // 로그인
  @PostMapping("/login")
  public String login(@RequestBody Map<String, String> user) {
    return userService.login(user);
  }

}
