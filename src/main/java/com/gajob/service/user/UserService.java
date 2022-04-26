package com.gajob.service.user;

import com.gajob.dto.user.UserDto;
import com.gajob.entity.user.Authority;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // 회원가입
  @Transactional
  public User signup(UserDto userDto) {
    // 종복된 아이디(이메일)가 있을 경우, 에러 처리
    if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null)
        != null) {
      throw new CustomException(ErrorCode.DUPLICATE_USER);
    }
    // 중복된 닉네임이 있을 경우, 에러 처리
    if (userRepository.existsByNickname(userDto.getNickname())) {
      throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
    }

    Authority authority = Authority.builder()
        .authorityName("ROLE_USER")
        .build();

    User user = User.builder()
        .email(userDto.getEmail())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .name((userDto.getName()))
        .nickname(userDto.getNickname())
        .authorities(Collections.singleton(authority))
        .activated(true)
        .build();

    return userRepository.save(user);
  }

  @Transactional
  public void findByEmail(UserDto userDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

    if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.BAD_CREDENTIALS);
    }
  }

  // username을 파라미터로 받아 해당 유저의 정보 및 권한 정보를 리턴
  @Transactional(readOnly = true)
  public Optional<User> getUserWithAuthorities(String email) {
    return userRepository.findOneWithAuthoritiesByEmail(email);
  }

  // SecurityUtil의 getCurrentUsername() 메소드가 리턴하는 username의 유저 및 권한 정보를 리턴
  @Transactional(readOnly = true)
  public Optional<User> getMyUserWithAuthorities() {
    return SecurityUtil.getCurrentUsername()
        .flatMap(userRepository::findOneWithAuthoritiesByEmail);
  }

  //회원정보 삭제
  @Transactional
  public String deleteUserWithAuthorities(String email) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    userRepository.delete(user);

    return "delete-user";
  }
}