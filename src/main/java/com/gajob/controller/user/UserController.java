package com.gajob.controller.user;


import com.gajob.dto.user.LoginDto;
import com.gajob.dto.user.TokenDto;
import com.gajob.dto.user.UserDto;
import com.gajob.entity.user.User;
import com.gajob.jwt.JwtFilter;
import com.gajob.jwt.TokenProvider;
import com.gajob.service.user.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")

public class UserController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final UserService userService;

  @PostMapping("/signup") //회원가입
  public ResponseEntity<User> signup(
      @Valid @RequestBody UserDto userDto
  ) {
    return ResponseEntity.ok(userService.signup(userDto));
  }

  @PostMapping("/login") //로그인
  public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

    Authentication authentication = authenticationManagerBuilder.getObject()
        .authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.createToken(authentication);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

    return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
  }


  @GetMapping("/user") // 현재 로그인 한 유저 정보 조회
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public ResponseEntity<User> getMyUserInfo() {
    return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
  }

  @GetMapping("/user/{email}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<User> getUserInfo(@PathVariable String email) {
    return ResponseEntity.ok(userService.getUserWithAuthorities(email).get());
  }

  @DeleteMapping("/user/{email}") //회원정보 삭제
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public ResponseEntity deleteUserWithAuthorities(@PathVariable String email) {
    return ResponseEntity.ok(userService.deleteUserWithAuthorities(email));
  }
}