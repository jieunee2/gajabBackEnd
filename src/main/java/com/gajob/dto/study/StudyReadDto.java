package com.gajob.dto.study;

import com.gajob.entity.study.Study;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.Status;
import com.gajob.enumtype.StudyCategory;
import java.time.LocalDate;
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
  private String createdDate, modifiedDate;
  private String openTalkUrl;
  //   List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
  private List<StudyCommentsResponseDto> comments;
  private int commentsCnt; //댓글 개수
  private int likes; //좋아요 수
  private List<StudyLikesResponseDto> likesList; //좋아요를 누른 유저들 목록

  private boolean likeStatus; // 현재 로그인 한 유저의 좋아요 여부
  private int scrap; //스크랩 수

  private boolean scrapStatus; // 현재 로그인 한 유저의 스크랩 여부

  private int supplyCnt; // 스터디 모집 지원자 수

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
    this.commentsCnt = comments.size();
    this.likes = study.getLikeList().size();
    this.likesList = study.getLikeList().stream().map(StudyLikesResponseDto::new).collect(
        Collectors.toList());
    this.scrap = study.getStudyScrapList().size();
    this.supplyCnt = study.getStudyRecruitmentList().stream()
        .map(StudyRecruitmentResponseDto::new).collect(
            Collectors.toList()).size();
  }
}
