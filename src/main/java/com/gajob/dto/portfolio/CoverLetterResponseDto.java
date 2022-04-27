package com.gajob.dto.portfolio;

import com.gajob.entity.portfolio.CoverLetter;
import lombok.Data;

@Data
public class CoverLetterResponseDto {

    private Long id;
    private String title;
    private String createdDate, modifiedDate;

    public CoverLetterResponseDto(CoverLetter coverLetter) {
        this.id = coverLetter.getId();
        this.title = coverLetter.getTitle();
        this.createdDate = coverLetter.getCreatedDate();
        this.modifiedDate = coverLetter.getModifiedDate();
    }

}
