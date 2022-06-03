package com.gajob.dto.study;

import com.gajob.entity.study.StudyRecruitment;
import com.gajob.enumtype.Result;
import lombok.Data;

@Data
public class StudyRecruitmentResponseDto {

  private Long id;
  private String content;

  private String writer;

  private String studentId;

  private String applicationDate;

  private Result result;

  public StudyRecruitmentResponseDto(StudyRecruitment studyRecruitment) {
    this.id = studyRecruitment.getId();
    this.content = studyRecruitment.getContent();
    this.writer = studyRecruitment.getUser().getNickname();
    this.studentId = studyRecruitment.getUser().getStudentId();
    this.applicationDate = studyRecruitment.getCreatedDate();
    this.result = studyRecruitment.getResult();
  }
}
