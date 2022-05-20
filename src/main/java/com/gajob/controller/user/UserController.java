package com.gajob.controller.user;


import com.gajob.dto.user.JwtResponseDto;
import com.gajob.dto.user.LoginDto;
import com.gajob.dto.user.PasswordUpdateDto;
import com.gajob.dto.user.UserDto;
import com.gajob.entity.user.User;
import com.gajob.jwt.TokenProvider;
import com.gajob.service.user.ProfileImageService;
import com.gajob.service.user.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")

public class UserController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final ProfileImageService profileImageService;

  @PostMapping("/signup") //회원가입
  public ResponseEntity<User> signup(
      @Valid @RequestBody UserDto userDto
  ) {
    return ResponseEntity.ok(userService.signup(userDto));
  }

  @PostMapping("/login") //로그인
  public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginDto loginDto,
      HttpServletResponse httpServletResponse) {

    return ResponseEntity.ok(userService.login(loginDto, httpServletResponse));
  }

  @PostMapping("/profile") //프로필 이미지 사진 업로드
  public String upload(
      @RequestParam(value = "profileImg", required = false) MultipartFile multipartFile) {
    profileImageService.upload(multipartFile);
    return "upload-success";
  }

  @DeleteMapping("/profile-delete") //프로필 이미지 삭제
  public String delete() {
    profileImageService.delete();
    return "delete-success";
  }

  @PutMapping({"/user"}) //회원 정보 수정
  public ResponseEntity update(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.update(userDto));
  }

  @PutMapping({"/update-password"}) //회원 비밀번호 수정
  public ResponseEntity updatePassword(@RequestBody PasswordUpdateDto passwordUpdateDto) {
    return ResponseEntity.ok(userService.updatePassword(passwordUpdateDto));
  }

  @GetMapping("/user") //현재 로그인 한 유저 정보 조회
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public ResponseEntity<User> getMyUserInfo() {
    return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
  }

  @GetMapping("/user/{email}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<User> getUserInfo(@PathVariable String email) {
    return ResponseEntity.ok(userService.getUserWithAuthorities(email).get());
  }

  @DeleteMapping("/user") //회원정보 삭제
  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  public ResponseEntity deleteUserWithAuthorities(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.deleteUserWithAuthorities(userDto));
  }
}