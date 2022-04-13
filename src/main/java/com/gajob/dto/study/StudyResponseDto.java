package com.gajob.dto.study;

import com.gajob.entity.study.Study;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.StudyCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class StudyResponseDto {

  private Long id;
  private String title;
  private String content;
  private StudyCategory studyCategory;
  private Area area;
  private int maxPeople;
  private int minPeople;
  private LocalDate startDate, endDate;
  private String writer;
  private int view;
  private LocalDateTime createdDate, modifiedDate;

  public StudyResponseDto(Study study) {
    this.id = study.getId();
    this.title = study.getTitle();
    this.content = study.getContent();
    this.studyCategory = study.getStudyCategory();
    this.area = study.getArea();
    this.maxPeople = study.getMaxPeople();
    this.minPeople = study.getMinPeople();
    this.startDate = study.getStartDate();
    this.endDate = study.getEndDate();
    this.writer = study.getUser().getNickname();
    this.view = study.getView();
    this.createdDate = study.getCreatedDate();
    this.modifiedDate = study.getModifiedDate();
  }

}
