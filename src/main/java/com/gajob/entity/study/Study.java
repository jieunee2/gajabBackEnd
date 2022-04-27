package com.gajob.entity.study;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.Status;
import com.gajob.enumtype.StudyCategory;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Study extends TimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "study_id")
  private Long id;

  @Column(length = 100, nullable = false)
  private String title; //제목

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content; //내용

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("startDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate; //스터디 시작 예정일

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonProperty("endDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate; //스터디 종료 예정일

  @Column
  private int minPeople; //최소정원

  @Column
  private int maxPeople; //최대정원

  @Column
  private String writer; //글쓴이

  @Column(columnDefinition = "integer default 0") // 초기 0으로 세팅
  private int view; //조회수

  @Column
  @Enumerated(EnumType.STRING)
  private StudyCategory studyCategory; //카테고리

  @Column
  @Enumerated(EnumType.STRING)
  private Area area; //지역

  @Column
  @Enumerated(EnumType.STRING)
  private Status status; //모집상태

  @Column
  private String openTalkUrl; //오픈카톡 URL

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @JsonIgnoreProperties({"study"})
  @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
  private List<Likes> likeList;

  @JsonIgnoreProperties({"study"})
  @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
  private List<StudyScrap> studyScrapList;

  //게시글이 삭제될 경우 댓글도 삭제되어야 하기 때문에 CascadeType.REMOVE 를 사용
  @OneToMany(mappedBy = "study", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private List<StudyComments> studyCommentsList;

  //게시글 수정
  public void update(String title, String content, StudyCategory studyCategory, Area area,
      int minPeople, int maxPeople, LocalDate startDate, LocalDate endDate, String openTalkUrl) {
    this.title = title;
    this.content = content;
    this.studyCategory = studyCategory;
    this.area = area;
    this.minPeople = minPeople;
    this.maxPeople = maxPeople;
    this.startDate = startDate;
    this.endDate = endDate;
    this.openTalkUrl = openTalkUrl;
  }
}