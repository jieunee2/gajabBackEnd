package com.gajob.dto.study;

import com.gajob.entity.study.StudyComments;
import lombok.Data;

@Data
public class StudyCommentsResponseDto {

  private Long id;
  private String comment;

  private Boolean isSecret;

  private String createdDate, modifiedDate;
  private String nickname;
  private Long studyId;

  public StudyCommentsResponseDto(StudyComments studyComments) {
    this.id = studyComments.getId();
    this.comment = studyComments.getComment();
    this.isSecret = studyComments.getIsSecret();
    this.createdDate = studyComments.getCreatedDate();
    this.modifiedDate = studyComments.getModifiedDate();
    this.nickname = studyComments.getUser().getNickname();
    this.studyId = studyComments.getStudy().getId();
  }

}
