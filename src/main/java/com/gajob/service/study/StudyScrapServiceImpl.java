package com.gajob.service.study;

import com.gajob.dto.study.StudyScrapResponseDto;
import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.study.StudyScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudyScrapServiceImpl implements StudyScrapService {

  private final UserRepository userRepository;
  private final StudyRepository studyRepository;
  private final StudyScrapRepository studyScrapRepository;

  // 스크랩 기능
  @Transactional
  public String scrap(Long postId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(postId).orElseThrow();

    if (isNotAlreadyScrap(user, study)) {
      studyScrapRepository.save(new StudyScrap(study, user));
      return "scrap-success";
    }
    // 이미 스크랩 한 게시물일 경우 스크랩 취소
    else if (studyScrapRepository.findByUserAndStudy(user, study).get() != null) {
      studyScrapRepository.deleteByUserAndStudy(user, study);
      return "cancel-scrap";
    }
    return "scrap-success";
  }

  // 사용자가 이미 스크랩 한 게시물인지 체크
  private boolean isNotAlreadyScrap(User user, Study study) {
    return studyScrapRepository.findByUserAndStudy(user, study).isEmpty();
  }

  // 스크랩 목록 조회
  @Transactional(readOnly = true)
  public List<StudyScrapResponseDto> getScrap() {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    return studyScrapRepository.findAllByUser(user).stream().map(StudyScrapResponseDto::new).collect(
        Collectors.toList());
  }

}
