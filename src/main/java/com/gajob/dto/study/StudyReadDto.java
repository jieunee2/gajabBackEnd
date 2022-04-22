package com.gajob.dto.study;

import com.gajob.entity.study.Study;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.Status;
import com.gajob.enumtype.StudyCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class StudyReadDto {

  private Long id;
  private String title;
  private String content;
  private StudyCategory studyCategory;
  private Area area;
  private int maxPeople;
  private int minPeople;
  private LocalDate startDate, endDate;
  private Status status;
  private String writer;
  private int view;
  private LocalDateTime createdDate, modifiedDate;
  private String openTalkUrl;
  //   List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
  private List<StudyCommentsResponseDto> comments;
  private int likes;

  public StudyReadDto(Study study) {
    this.id = study.getId();
    this.title = study.getTitle();
    this.content = study.getContent();
    this.studyCategory = study.getStudyCategory();
    this.area = study.getArea();
    this.maxPeople = study.getMaxPeople();
    this.minPeople = study.getMinPeople();
    this.startDate = study.getStartDate();
    this.endDate = study.getEndDate();
    this.status = study.getStatus();
    this.writer = study.getUser().getNickname();
    this.view = study.getView();
    this.createdDate = study.getCreatedDate();
    this.modifiedDate = study.getModifiedDate();
    this.openTalkUrl = study.getOpenTalkUrl();
    this.comments = study.getStudyCommentsList().stream().map(StudyCommentsResponseDto::new)
        .collect(Collectors.toList());
    this.likes = study.getLikeList().size();
  }
}
