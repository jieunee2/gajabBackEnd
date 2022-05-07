package com.gajob.service.mail;

import com.gajob.dto.mail.MailDto;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailService {

  private final UserRepository userRepository;
  private final JavaMailSender javaMailSender;
  private final PasswordEncoder passwordEncoder;

  // 메일 내용을 생성
  @Transactional
  public MailDto createMailAndUpdatePassword(String email) {
    MailDto mailDto = new MailDto();
    mailDto.setEmail(mailDto.getEmail());
    return mailDto;

  }

  // 메일 보내기
  @Transactional
  public void mailSend(MailDto mailDto) {
    // 임시 비밀번호 가져오기
    String tempPassword = updateToTempPassword(mailDto.getEmail());

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(mailDto.getEmail());
    simpleMailMessage.setSubject("GA-JOB에서 회원님 임시 비밀번호를 알려드립니다.");
    simpleMailMessage.setText(
        "안녕하세요. GA-JOB 입니다. 저희 사이트를 방문해 주셔서 감사합니다." + "\n" + "회원님의 임시 비밀번호를 알려드립니다." + "\n"
            + "임시 비밀번호로 로그인하신 후 원하시는 비밀번호로 수정해서 이용하시기 바랍니다." + "\n\n"
            + "임시 비밀번호 : " + tempPassword);
    simpleMailMessage.setFrom("980lje@naver.com");
    System.out.println("message" + simpleMailMessage);
    javaMailSender.send(simpleMailMessage);
  }

  //임시 비밀번호로 업데이트
  @Transactional
  public String updateToTempPassword(String email) {
    // 생성된 임시 비밀번호를 tempPassword 변수에 저장
    String tempPassword = createTempPassword();

    // 생성된 임시 비밀번호로 업데이트(가입되지 않은 유저의 이메일 또는 잘못된 이메일을 입력했을 경우 에러 발생)
    User user = userRepository.findOneWithAuthoritiesByEmail(email)
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    user.setPassword(tempPassword);

    // 임시 비밀번호 암호화
    String secure = passwordEncoder.encode(user.getPassword());
    user.setPassword(secure);

    return tempPassword;
  }

  //랜덤함수로 임시비밀번호 구문 생성
  @Transactional
  public String createTempPassword() {
    char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
        'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'};

    String str = "";

    // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
    int idx = 0;
    for (int i = 0; i < 10; i++) {
      idx = (int) (charSet.length * Math.random());
      str += charSet[idx];
    }
    return str;
  }

}
