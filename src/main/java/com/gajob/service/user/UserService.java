package com.gajob.service.user;

import com.gajob.dto.user.JwtResponseDto;
import com.gajob.dto.user.LoginDto;
import com.gajob.dto.user.PasswordUpdateDto;
import com.gajob.dto.user.UserDto;
import com.gajob.entity.user.Authority;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.jwt.JwtFilter;
import com.gajob.jwt.TokenProvider;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  private final PostsRepository postsRepository;
  private final StudyRepository studyRepository;

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

    // 중복된 학번이 있을 경우, 에러 처리
    if (userRepository.existsByStudentId(userDto.getStudentId())) {
      throw new CustomException(ErrorCode.DUPLICATE_STUDENT_ID);
    }
    // 잘못된 형식의 학번을 입력할 경우, 에러 처리(교내 기준, 학번 9자리)
    else if (!(userDto.getStudentId().length() == 9)) {
      throw new CustomException(ErrorCode.INVALID_STUDENT_ID);
    }

    Authority authority = Authority.builder()
        .authorityName("ROLE_USER")
        .build();

    User user = User.builder()
        .email(userDto.getEmail())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .name((userDto.getName()))
        .nickname(userDto.getNickname())
        .studentId(userDto.getStudentId())
        .studentEmail(userDto.getStudentEmail())
        .department(userDto.getDepartment())
        .authorities(Collections.singleton(authority))
        .activated(true)
        .build();

    return userRepository.save(user);
  }

  // 로그인
  @Transactional
  public JwtResponseDto login(LoginDto loginDto,
      HttpServletResponse httpServletResponse) {
    // 가입되지 않은 이메일일 경우 에러문 출력
    User user = userRepository.findOneWithAuthoritiesByEmail(loginDto.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

    // 비밀번호가 일치하지 않을 경우 에러문 출력
    if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.BAD_CREDENTIALS);
    }

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

    Authentication authentication = authenticationManagerBuilder.getObject()
        .authenticate(authenticationToken);
    SecurityContextHolder.getContext().

        setAuthentication(authentication);

    String jwt = tokenProvider.createToken(authentication);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

    user = userRepository.findOneWithAuthoritiesByEmail(loginDto.getEmail()).get();

    // Token과 User의 닉네임을 동시에 출력
    return new JwtResponseDto(jwt, user.getNickname(), user.getEmail());
  }

  // 회원 정보 수정
  @Transactional
  public User update(UserDto userDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    // 중복된 닉네임이 존재할 경우, 에러 처리
    if (userRepository.existsByNickname(userDto.getNickname())) {
      throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
    } else {
      user.update(userDto.getNickname(), userDto.getDepartment());
    }

    return userRepository.save(user);
  }

  // 회원 비밀번호 수정
  @Transactional
  public String updatePassword(PasswordUpdateDto passwordUpdateDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    //기존의 비밀번호와 수정하려는 비밀번호가 같을 경우, 에러 처리
    if (passwordEncoder.matches(passwordUpdateDto.getNewPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.DUPLICATE_PASSWORD);
    }
    // 입력한 기존 비밀번호가 DB에 있는 기존 비밀번호와 일치하지 않을 경우, 에러 처리
    else if (!passwordEncoder.matches(passwordUpdateDto.getOldPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
    }

    //수정된 비밀번호를 암호화 하여 DB에 저장
    user.passwordUpdate(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));

    return "password-change-successful";
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
  public String deleteUserWithAuthorities(UserDto userDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    // 탈퇴를 하는 경우 사용자에게 비밀번호를 입력받고, 입력받은 비밀번호가 틀릴 시 에러 처리
    if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
    }

    postsRepository.deleteAllByUser(user);
    studyRepository.deleteAllByUser(user);

    userRepository.delete(user);

    return "delete-user";
  }
}