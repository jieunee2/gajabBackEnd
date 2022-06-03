package com.gajob.dto.study;

import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyRecruitment;
import com.gajob.entity.user.User;
import com.gajob.enumtype.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyRecruitmentDto {

  private String content;

  private Study study;

  public StudyRecruitment toEntity(User user, Study study) {
    StudyRecruitment studyRecruitment = StudyRecruitment.builder().content(content)
        .result(Result.대기중).study(study).user(user)
        .build();

    return studyRecruitment;
  }

}
