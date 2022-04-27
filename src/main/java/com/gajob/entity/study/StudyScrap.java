package com.gajob.entity.study;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class StudyScrap extends TimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scrap_id")
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "study_id")
  private Study study;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnoreProperties({"study"})
  private User user;

  public StudyScrap(Study study, User user) {
    this.study = study;
    this.user = user;
  }
}
