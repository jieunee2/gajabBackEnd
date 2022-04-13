package com.gajob.dto.study;

import com.gajob.entity.study.Study;
import com.gajob.entity.user.User;
import com.gajob.enumtype.Area;
import com.gajob.enumtype.StudyCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyDto {

  private String title;
  private String content;
  private StudyCategory studyCategory;
  private Area area;
  private int maxPeople;
  private int minPeople;
  private LocalDate startDate;
  private LocalDate endDate;
  private User user;

  public Study toEntity(User user) {
    Study study = Study.builder().title(title).content(content).studyCategory(studyCategory)
        .area(area).maxPeople(maxPeople).minPeople(minPeople).startDate(startDate).endDate(endDate)
        .view(0).writer(user.getNickname())
        .user(user).build();

    return study;
  }

}
