package com.gajob.repository.study;

import com.gajob.entity.study.StudyLikes;
import com.gajob.entity.study.Study;
import com.gajob.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyLikesRepository extends JpaRepository<StudyLikes, Long> {

//  @Modifying
//  @Query(value = "insert into likes(study_id,user_id) values(:studyId, :userId)", nativeQuery = true)
//  void likes(Long studyId, Long userId);

  Optional<StudyLikes> deleteByUserAndStudy(User user, Study study);

  Optional<StudyLikes> findByUserAndStudy(User user, Study study);
}
