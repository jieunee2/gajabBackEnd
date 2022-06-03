package com.gajob.service.study;

import com.gajob.dto.study.StudyRecruitmentDto;
import com.gajob.dto.study.StudyRecruitmentResponseDto;
import com.gajob.entity.study.Study;
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

    if (studyRecruitmentRepository.findByStudyAndUser(study, user).get() != null) {
      throw new CustomException(ErrorCode.DUPLICATE_SUPPLY_STUDY);
    }

    return new StudyRecruitmentResponseDto(
        studyRecruitmentRepository.save(studyRecruitmentDto.toEntity(user, study)));
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
}
