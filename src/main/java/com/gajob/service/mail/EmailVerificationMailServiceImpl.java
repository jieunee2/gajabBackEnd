package com.gajob.service.mail;

import com.gajob.dto.mail.MailDto;
import com.gajob.repository.user.UserRepository;
import java.util.Random;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmailVerificationMailServiceImpl implements EmailVerificationMailService {

  private final UserRepository userRepository;
  private final JavaMailSender javaMailSender;
  private final PasswordEncoder passwordEncoder;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  public static final String num = createKey();

  @Transactional
  public void sendSimpleMessage(MailDto mailDto) throws Exception {
    MimeMessage message = createMessage(mailDto);
    try {//예외처리
      javaMailSender.send(message);
    } catch (MailException es) {
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
  }

  // 메일 보내기
  @Transactional
  public MimeMessage createMessage(MailDto mailDto) throws Exception {
    logger.info("보내는 대상 : " + mailDto.getEmail());
    logger.info("인증 번호 : " + num);

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    String code = createCode(num);

    mimeMessage.addRecipients(RecipientType.TO, mailDto.getEmail()); // 보내는 대상
    mimeMessage.setSubject("GA-JOB에서 이메일 확인 코드를 전송했습니다.");

    // 메일에 담길 내용
    String message = "";
    message += "<img width=\"120\" height=\"100\" style=\"margin-top: 0; margin-right: 0; margin-bottom: 32px; margin-left: 0px; padding-right: 30px; padding-left: 30px;\" src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5M6fy7gTWvFtE_7XQSDFjVNG3bRBqcI9dew&usqp=CAU\" alt=\"\" loading=\"lazy\">";
    message += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
    message += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 GA-JOB 가입 창이 있는 브라우저 창에 입력하세요.</p>";
    message += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
    message += code;
    message += "</td></tr></tbody></table></div>";
    // 연결 링크는 도메인 연결 시 다시 수정할 예정
    message += "<a href=\"http://localhost:3000\" style=\"text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">copyright © 2022 by JobMon</a>";

    mimeMessage.setText(message, "utf-8", "html"); // 내용
    mimeMessage.setFrom("980lje@naver.com");
    return mimeMessage;
  }

  // 인증코드 생성
  public static String createKey() {
    StringBuffer key = new StringBuffer();
    Random random = new Random();

    for (int i = 0; i < 6; i++) { // 인증코드 6자리
      key.append((random.nextInt(10)));
    }
    return key.toString();
  }

  public String createCode(String num) {
    return num.substring(0, 3) + num.substring(3, 6);
  }

}
