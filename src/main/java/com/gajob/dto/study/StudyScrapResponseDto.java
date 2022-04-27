package com.gajob.dto.study;

import com.gajob.entity.study.StudyScrap;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.Status;
import com.gajob.enumtype.StudyCategory;
import lombok.Data;

@Data
public class StudyScrapResponseDto {

  private Long id;
  private String title;
  private StudyCategory studyCategory;
  private Area area;
  private Status status;
  private String writer;
  private String scrapDate;
  private String postUrl;

  public StudyScrapResponseDto(StudyScrap studyScrap) {
    this.id = studyScrap.getId();
    this.title = studyScrap.getStudy().getTitle();
    this.studyCategory = studyScrap.getStudy().getStudyCategory();
    this.area = studyScrap.getStudy().getArea();
    this.status = studyScrap.getStudy().getStatus();
    this.writer = studyScrap.getStudy().getWriter();
    this.scrapDate = studyScrap.getCreatedDate();
    this.postUrl = "http://localhost:3000/study/posts/" + studyScrap.getStudy().getId();
  }

}
