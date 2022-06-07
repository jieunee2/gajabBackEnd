package com.gajob.repository.study;

import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyRecruitment;
import com.gajob.entity.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRecruitmentRepository extends JpaRepository<StudyRecruitment, Long> {

  List<StudyRecruitment> findAllByStudyId(Long postId);

  Optional<StudyRecruitment> findByStudyAndUser(Study study, User user);

  List<StudyRecruitment> findAllByUser(User user);

}
