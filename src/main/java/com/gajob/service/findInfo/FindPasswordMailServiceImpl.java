package com.gajob.service.findInfo;

import com.gajob.dto.mail.MailDto;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.user.UserRepository;
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
public class FindPasswordMailServiceImpl implements FindPasswordMailService {

  private final UserRepository userRepository;
  private final JavaMailSender javaMailSender;
  private final PasswordEncoder passwordEncoder;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  // 메일 내용을 생성
  @Transactional
  public void sendSimpleMessage(MailDto mailDto) throws Exception {
    MimeMessage message = mailSend(mailDto);
    try {//예외처리
      javaMailSender.send(message);
    } catch (MailException es) {
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
  }

  // 메일 보내기
  @Transactional
  public MimeMessage mailSend(MailDto mailDto) throws Exception {
    // 임시 비밀번호 가져오기
    String tempPassword = updateToTempPassword(mailDto.getEmail());

    logger.info("보내는 대상 : " + mailDto.getEmail());

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    mimeMessage.addRecipients(RecipientType.TO, mailDto.getEmail()); // 보내는 대상
    mimeMessage.setSubject("GA-JOB에서 임시 비밀번호를 알려드립니다.");

    // 메일에 담길 내용
    String message = "";
    message += "<div style=\"text-align: center; display: grid; place-content:c enter; width:60%; padding-bottom: 50px; margin: auto;\">";
    message += "<img width=\"300\" height=\"300\" style=\"margin: auto; padding-top: 50px; place-content:center; display: grid;\" src=\"https://postfiles.pstatic.net/MjAyMjA2MDJfMTU4/MDAxNjU0MTYwMDY3OTUw.NkJSjSny2DmWnLG_IMDsWxdCmzF2LeZX6CKY_8MgPUQg.HYcE2-rBtDHLhLBSAygCcku6Gh_aK029ouXGRG4gmXgg.PNG.980lje/mail-removebg-preview.png?type=w773\" alt=\"\" loading=\"lazy\">";
    message +=
        "<p style=\"text-align: left; padding-left: 14px; padding-top: 10px; border-top: 2px solid #eee; line-height: 15px; font-size:12px\">\n"
            + "      안녕하세요.<br>\n"
            + "      고객님의 임시 비밀번호를 알려드립니다.<br>\n"
            + "      임시 비밀번호로 로그인하신 후 원하시는 비밀번호로 수정해서 이용하시기 바랍니다.\n"
            + "    </>";
    message += "<br>";
    message += "<span style=\"color:#9fa8b9;\">임시 비밀번호 : </span>";
    message += "<span style=\"color:#435578; font-weight: bold;\">" + tempPassword + "</span>";
    message += "<br>";
    message +=
        "<div style=\"margin: 0 5px 0 14px; font-size: 11px; color: #9fa8b9; line-height: 15px;\">\n"
            + "        ※ 참고하세요!<br>\n"
            + "        임시 비밀번호로 로그인 하신 후, 반드시 비밀번호를 수정해 주세요.<br>\n"
            + "        비밀번호는 사이트 내 로그인 &gt; 마이페이지 &gt;회원정보수정 에서 수정하실 수 있습니다.<br>\n"
            + "        안전한 서비스 이용을 위해서 비밀번호는 정기적으로 변경해주는 것이 좋습니다.\n"
            + "      </div>";
    // 연결 링크는 도메인 연결 시 다시 수정할 예정
    message += "<a href=\"http://localhost:3000\" style=\"margin-top: 10px; text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">© 2022 JobMon. All rights reserved</a>";
    message += "</div>";

    mimeMessage.setText(message, "utf-8", "html"); // 내용
    mimeMessage.setFrom("980lje@naver.com");
    return mimeMessage;
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
