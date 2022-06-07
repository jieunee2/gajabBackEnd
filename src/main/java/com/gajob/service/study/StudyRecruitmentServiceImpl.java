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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudyRecruitmentServiceImpl implements StudyRecruitmentService {

  private final StudyRecruitmentRepository studyRecruitmentRepository;
  private final UserRepository userRepository;

  private final StudyRepository studyRepository;

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

//  value = value.replaceAll(System.getProperty("line.separator"), " ");

  // 사용자가 이미 지원한 스터디인지 체크
  private boolean isNotAlreadySupply(User user, Study study) {
    return studyRecruitmentRepository.findByStudyAndUser(study, user).isEmpty();
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
      Long supplyId, StudyRecruitmentUpdateDto studyRecruitmentUpdateDto) {
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

    StudyRecruitmentResponseDto studyRecruitmentResponseDto = new StudyRecruitmentResponseDto(
        studyRecruitment);

    return studyRecruitmentResponseDto;
  }
}
