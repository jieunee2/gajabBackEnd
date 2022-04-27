package com.gajob.service.study;

import com.gajob.entity.study.Likes;
import com.gajob.entity.study.Study;
import com.gajob.entity.user.User;
import com.gajob.repository.study.StudyLikesRepository;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StudyLikesServiceImpl implements StudyLikesService {

  private final StudyLikesRepository studyLikesRepository;
  private final UserRepository userRepository;
  private final StudyRepository studyRepository;

  // 좋아요 기능
  @Transactional
  public String likes(Long studyId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Study study = studyRepository.findById(studyId).orElseThrow();

    // 중복 좋아요 방지
    if (
        isNotAlreadyLike(user, study)) {
      studyLikesRepository.save(new Likes(study, user));
      return "increase-likes";
    }
    // 이미 좋아요를 눌렀던 게시물일 경우, 다시 좋아요 기능을 요청할 경우 좋아요 해제
    else if (studyLikesRepository.findByUserAndStudy(user, study).get() != null) {
      studyLikesRepository.deleteByUserAndStudy(user, study);
      return "decrease-likes";
    }
    return "increase-likes";
  }

  // 사용자가 이미 좋아요 한 게시물인지 체크
  private boolean isNotAlreadyLike(User user, Study study) {
    return studyLikesRepository.findByUserAndStudy(user, study).isEmpty();
  }

}