package com.gajob.repository.study;

import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyScrap;
import com.gajob.entity.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudyScrapRepository extends JpaRepository<StudyScrap, Long> {

  @Query("select s from StudyScrap s")
  List<StudyScrap> findAll();

  Optional<StudyScrap> findByUserAndStudy(User user, Study study);

  Optional<StudyScrap> deleteByUserAndStudy(User user, Study study);

}
