package com.gajob.dto.study;

import com.gajob.entity.study.StudyLikes;
import lombok.Data;

@Data
public class StudyLikesResponseDto {

  private Long id;
  private String nickname;

  public StudyLikesResponseDto(StudyLikes studyLikes) {
    this.id = studyLikes.getId();
    this.nickname = studyLikes.getUser().getNickname();
  }

}
