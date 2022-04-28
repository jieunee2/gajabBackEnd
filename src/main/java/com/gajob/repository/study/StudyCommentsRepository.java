package com.gajob.repository.study;

import com.gajob.entity.study.StudyComments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommentsRepository extends JpaRepository<StudyComments, Long> {

  List<StudyComments> findAllByStudyId(Long postId);

}
