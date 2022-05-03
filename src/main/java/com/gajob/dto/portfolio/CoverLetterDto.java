package com.gajob.dto.portfolio;

import com.gajob.entity.portfolio.CoverLetter;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoverLetterDto {

    private String title;
    private String totalModifiedDate;
    private User user;

    public CoverLetter toEntity(User user) {
        CoverLetter coverLetter = CoverLetter.builder().title(title).totalModifiedDate(totalModifiedDate).user(user).build();

        return coverLetter;
    }

}
