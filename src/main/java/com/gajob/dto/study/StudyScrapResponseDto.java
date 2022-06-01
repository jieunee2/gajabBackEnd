package com.gajob.dto.study;

import com.gajob.entity.study.StudyScrap;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.Status;
import com.gajob.enumtype.StudyCategory;
import java.time.LocalDate;
import lombok.Data;

@Data
public class StudyScrapResponseDto {

  private Long id;
  private String title;

  private String content;
  private StudyCategory studyCategory;
  private Area area;

  private String nickname;

  private int maxPeople;

  private int minPeople;

  private LocalDate startDate;

  private LocalDate endDate;
  private Status status;
  private String writer;
  private String scrapDate;
  private Long studyId;

  public StudyScrapResponseDto(StudyScrap studyScrap) {
    this.id = studyScrap.getId();
    this.title = studyScrap.getStudy().getTitle();
    this.content = studyScrap.getStudy().getContent();
    this.studyCategory = studyScrap.getStudy().getStudyCategory();
    this.area = studyScrap.getStudy().getArea();
    this.nickname = studyScrap.getUser().getNickname();
    this.maxPeople = studyScrap.getStudy().getMaxPeople();
    this.minPeople = studyScrap.getStudy().getMinPeople();
    this.startDate = studyScrap.getStudy().getStartDate();
    this.endDate = studyScrap.getStudy().getEndDate();
    this.status = studyScrap.getStudy().getStatus();
    this.writer = studyScrap.getStudy().getWriter();
    this.scrapDate = studyScrap.getCreatedDate();
    this.studyId = studyScrap.getStudy().getId();
  }

}
