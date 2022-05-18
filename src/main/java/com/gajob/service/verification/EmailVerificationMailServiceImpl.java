package com.gajob.service.verification;

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
    mimeMessage.setSubject("이메일 확인 코드 : " + code);

    // 메일에 담길 내용
    String message = "";
    message += "<div style=\"background-color:#e5e5e4; text-align: center; display: grid; place-content:c enter; width:50%; padding-bottom: 50px; margin: auto;\">";
    message += "<img width=\"130\" height=\"120\" style=\"margin: auto; padding-top: 50px; place-content:center; display: grid;\" src=\"https://postfiles.pstatic.net/MjAyMjA1MTdfNDkg/MDAxNjUyNzQ4MzQxMTY1.tsu3njYdLUSU5EDNfpBNUqunvrhSx0CG_ZojwepMC9Yg.0mcQ9jlyjg4cD5-Nxif_2gJ-QESMJ6WlukprYRnV0Qsg.PNG.980lje/%EB%A1%9C%EA%B3%A0.png?type=w773\" alt=\"\" loading=\"lazy\">";
    message += "<h1 style=\"padding-bottom: 50px; font-size: 30px; font-weight: 500; -webkit-letter-spacing: 0.01em; -moz-letter-spacing: 0.01em; -ms-letter-spacing: 0.01em; letter-spacing: 0.01em; color: #141212; text-align: center; line-height: 39px; margin: 0; font-family: inherit;\">이메일 인증 코드</h1>";
    message += "<div style=\"margin: auto; width: 400px; height: 200px; display: grid; place-content: center; text-align: center; background-color: #F9F9F9; font-size: 37px; font-family: inherit\">";
    message += "<p style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; margin: 0; padding: 0; font-weight: 500; font-size: 16px; line-height: 140%; -webkit-letter-spacing: -0.01em; -moz-letter-spacing: -0.01em; -ms-letter-spacing: -0.01em; letter-spacing: -0.01em; color: #666; font-family: inherit;\">이메일 인증 코드입니다.</p>";
    message += "<p style=\"margin-top:20px; letter-spacing: 0.7em\">";
    message += code;
    message += "</p>";
    message += "</div>";
    message += "<p style=\"margin: auto; width: 400px; height: 200px; display: grid; text-align: center; place-content: center; font-size: 16px; letter-spacing: -0.01em; color: #666; font-family: inherit;\">만약 로그인 승인 코드를 요청하지 않으셨다면 즉시 비밀번호를 변경하여 무단 로그인을 방지해 주세요. 계정 보호 문서에서 비밀번호 보안 관련 팁을 확인하실 수 있습니다.</p>";
    message += "<p style=\"-webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; margin: auto; width: 400px; height: 50px; display: grid; text-align: center; place-content: center; font-weight: 800; font-size: 12px; letter-spacing: -0.01em; color: #666; font-family: inherit;\">개인정보 ● 처리방침고객지원서비스 ● 약관</p>";
    message +=
        "<p style=\"margin: auto; width: 400px; display: grid; text-align: center; place-content: center; font-size: 9px; letter-spacing: -0.01em; color: #666; font-family: inherit;\">\n"
            + "본 메일은 이용안내 알림을 위해 발송되었습니다.</p>";
//    message += "</div></td></tr></tbody></table></div>";
    // 연결 링크는 도메인 연결 시 다시 수정할 예정
    message += "<a href=\"http://localhost:3000\" style=\"margin-top: 10px; text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">© 2022 JobMon. All rights reserved</a>";
    message += "</div>";

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
