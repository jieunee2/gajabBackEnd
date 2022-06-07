package com.gajob.dto.study;

import com.gajob.entity.study.StudyRecruitment;
import com.gajob.enumtype.Department;
import com.gajob.enumtype.Result;
import lombok.Data;

@Data
public class StudyRecruitmentResponseDto {

  private Long id;

  private String name;

  private String content;

  private String writer;

  private String studentId;

  private Department department;

  private String studentEmail;
  private String applicationDate;

  private Result result;

  public StudyRecruitmentResponseDto(StudyRecruitment studyRecruitment) {
    this.id = studyRecruitment.getId();
    this.name = studyRecruitment.getUser().getName();
    this.content = studyRecruitment.getContent();
    this.writer = studyRecruitment.getUser().getNickname();
    this.studentId = studyRecruitment.getUser().getStudentId();
    this.department = studyRecruitment.getUser().getDepartment();
    this.studentEmail = studyRecruitment.getUser().getStudentEmail();
    this.applicationDate = studyRecruitment.getCreatedDate();
    this.result = studyRecruitment.getResult();
  }
}
