package com.gajob.dto.portfolio;

import com.gajob.entity.portfolio.CoverLetter;
import com.gajob.entity.portfolio.CoverLetterItem;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoverLetterItemDto {

    private Long id;
    private String question;
    private String answer;

    // 년, 월, 일, 시, 분까지 나오게 포맷
    private String createdDate = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private CoverLetter coverLetter;
    private User user;

    public CoverLetterItem toEntity(User user, CoverLetter coverLetter) {
        CoverLetterItem coverLetterItem = CoverLetterItem.builder()
                .id(id).question(question).answer(answer)
                .createdDate(createdDate).modifiedDate(modifiedDate)
                .coverLetter(coverLetter).user(user).build();
        return coverLetterItem;
    }

}
