package com.gajob.dto.portfolio;

import com.gajob.entity.portfolio.CoverLetter;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CoverLetterReadDto {

    private Long id;
    private String title;
    private String totalModifiedDate;
    private String createdDate, modifiedDate;
    private List<CoverLetterItemResponseDto> item;

    public CoverLetterReadDto(CoverLetter coverLetter) {
        this.id = coverLetter.getId();
        this.title = coverLetter.getTitle();
        this.totalModifiedDate = coverLetter.getModifiedDate();
        this.createdDate = coverLetter.getCreatedDate();
        this.modifiedDate = coverLetter.getModifiedDate();
        this.item = coverLetter.getCoverLetterItemList().stream().map(CoverLetterItemResponseDto::new)
                .collect(Collectors.toList());
    }

}
