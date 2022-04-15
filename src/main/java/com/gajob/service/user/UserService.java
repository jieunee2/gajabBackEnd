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

  @Transactional
  public User signup(UserDto userDto) {
    if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null)
        != null) {
      throw new CustomException(ErrorCode.DUPLICATE_USER);
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
}