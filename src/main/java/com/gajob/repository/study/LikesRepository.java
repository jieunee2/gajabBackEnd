package com.gajob.repository.study;

import com.gajob.dto.study.Likes;
import com.gajob.entity.study.Study;
import com.gajob.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

//  @Modifying
//  @Query(value = "insert into likes(study_id,user_id) values(:studyId, :userId)", nativeQuery = true)
//  void likes(Long studyId, Long userId);

  Optional<Likes> deleteByUserAndStudy(User user, Study study);

  Optional<Likes> findByUserAndStudy(User user, Study study);
}
