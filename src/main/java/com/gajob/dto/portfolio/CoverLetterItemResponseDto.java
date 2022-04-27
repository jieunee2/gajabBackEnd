package com.gajob.dto.portfolio;

import com.gajob.entity.portfolio.CoverLetterItem;
import lombok.Data;

@Data
public class CoverLetterItemResponseDto {

    private Long id;
    private String question;
    private String answer;
    private String createdDate, modifiedDate;
    private Long coverLetterId;

    public CoverLetterItemResponseDto(CoverLetterItem coverLetterItem) {
        this.id = coverLetterItem.getId();
        this.question = coverLetterItem.getQuestion();
        this.answer = coverLetterItem.getAnswer();
        this.createdDate = coverLetterItem.getCreatedDate();
        this.modifiedDate = coverLetterItem.getModifiedDate();
        this.coverLetterId = coverLetterItem.getCoverLetter().getId();
    }

}
