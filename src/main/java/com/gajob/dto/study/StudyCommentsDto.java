package com.gajob.dto.study;

import com.gajob.entity.study.Study;
import com.gajob.entity.study.StudyComments;
import com.gajob.entity.user.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyCommentsDto {

  private Long id;
  private String comment;

  private Boolean isSecret;

  // 년, 월, 일, 시, 분까지 나오게 포맷
  private String createdDate = LocalDateTime.now().format(
      DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String modifiedDate = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

  private User user;
  private Study study;

  public StudyComments toEntity(User user, Study study) {
    StudyComments studyComments = StudyComments.builder().id(id).comment(comment).isSecret(isSecret)
        .createdDate(createdDate)
        .modifiedDate(modifiedDate).user(user).study(study).build();
    return studyComments;
  }
}
