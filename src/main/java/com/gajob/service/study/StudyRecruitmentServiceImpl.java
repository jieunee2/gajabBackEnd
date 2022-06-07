package com.gajob.service.study;

import com.gajob.dto.study.StudyRecruitmentDto;
import com.gajob.dto.study.StudyRecruitmentResponseDto;
import com.gajob.dto.study.StudyRecruitmentUpdateDto;
import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyRecruitment;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.study.StudyRecruitmentRepository;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudyRecruitmentServiceImpl implements StudyRecruitmentService {

  private final StudyRecruitmentRepository studyRecruitmentRepository;
  private final UserRepository userRepository;

  private final StudyRepository studyRepository;

  private final JavaMailSender javaMailSender;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  // 스터디 모임 신청
  @Transactional
  public StudyRecruitmentResponseDto support(Long postId, StudyRecruitmentDto studyRecruitmentDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId).orElseThrow();

    if (isNotAlreadySupply(user, study)) {
      return new StudyRecruitmentResponseDto(
          studyRecruitmentRepository.save(studyRecruitmentDto.toEntity(user, study)));
    }
    // 사용자가 이미 지원한 스터디일 경우 에러문을 띄워줌
    else {
      throw new CustomException(ErrorCode.DUPLICATE_SUPPLY_STUDY);
    }
  }

  // 사용자가 이미 지원한 스터디인지 체크
  private boolean isNotAlreadySupply(User user, Study study) {
    return studyRecruitmentRepository.findByStudyAndUser(study, user).isEmpty();
  }

  // 스터디 모임 신청 취소
  @Transactional
  public String supportCancel(Long postId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    studyRecruitmentRepository.deleteByUserAndStudyId(user, postId);

    return "supply-cancel";
  }

  // 스터디 모임 신청자 전체 조회
  @Transactional(readOnly = true)
  public List<StudyRecruitmentResponseDto> getAllSupport(Long postId) {
    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    // studyRecruitmentRepository 결과로 넘어온 StudyRecruitment의 Stream을 map을 통해 StudyRecruitmentResponseDto로 변환
    return studyRecruitmentRepository.findAllByStudyId(postId).stream()
        .map(StudyRecruitmentResponseDto::new)
        .collect(Collectors.toList());
  }

  // 스터디 지원자들의 모집결과 설정
  @Transactional
  public StudyRecruitmentResponseDto setResult
  (Long postId,
      Long supplyId, StudyRecruitmentUpdateDto studyRecruitmentUpdateDto) throws Exception {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    StudyRecruitment studyRecruitment = studyRecruitmentRepository.findById(supplyId)
        .orElseThrow(() -> new CustomException(ErrorCode.SUPPLY_ID_NOT_EXIST));

    // 스터디 모집 게시물 작성자가 현재 로그인 한 유저가 아니라면 접근하지 못하도록 에러를 출력
    if (!study.getWriter().equals(user.getNickname())) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    studyRecruitment.update(studyRecruitmentUpdateDto.getResult());

    // 스터디 모집 결과 알림 메일 발송
    mailSend(studyRecruitment.getUser().getStudentEmail());
    sendSimpleMessage(studyRecruitment.getUser().getStudentEmail());

    StudyRecruitmentResponseDto studyRecruitmentResponseDto = new StudyRecruitmentResponseDto(
        studyRecruitment);

    return studyRecruitmentResponseDto;
  }

  // 메일 내용을 생성
  @Transactional
  public void sendSimpleMessage(String email) throws Exception {
    MimeMessage message = mailSend(email);
    try {//예외처리
      javaMailSender.send(message);
    } catch (MailException es) {
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
  }

  //스터디 모집 결과 알림 메일 보내기
  @Transactional
  public MimeMessage mailSend(String email) throws Exception {
    logger.info("보내는 대상 : " + email);

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    mimeMessage.addRecipients(RecipientType.TO, email); // 보내는 대상
    mimeMessage.setSubject("GA-JOB에서 회원님의 스터디 모집 결과를 알려드립니다.");

    // 메일에 담길 내용
    String message = "";
    message += "<a href=\"http://localhost:3000\">";
    message += "<img width=\"600\" height=\"800\" style=\"margin: auto; padding-top: 50px; place-content:center; display: grid;\" src=\"https://postfiles.pstatic.net/MjAyMjA2MDdfMjMx/MDAxNjU0NTg3NzY2Nzc2.JjazBHgcSzQxIim84uMlaV368nfKEeZ-tpL9K3RojwAg.Mr4Cn3zmKsxD9_SzrOWpZ2tQgKtO8QAJo6XAmThfQVgg.JPEG.980lje/%EC%8A%A4%ED%84%B0%EB%94%94_%EB%AA%A8%EC%A7%91_%EA%B2%B0%EA%B3%BC.jpg?type=w773\" alt=\"\" loading=\"lazy\">";
    message += "</a>\"";
    mimeMessage.setText(message, "utf-8", "html"); // 내용
    mimeMessage.setFrom("980lje@naver.com");
    return mimeMessage;
  }
}
