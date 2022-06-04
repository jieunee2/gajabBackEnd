package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitScrap;
import lombok.Data;

@Data
public class ExhibitScrapResponseDto {

    private Long id;
    private String title;
    private String scrapDate;
    private String exhibitUrl;

    public ExhibitScrapResponseDto(ExhibitScrap exhibitScrap) {
        this.id = exhibitScrap.getId();
        this.title = exhibitScrap.getExhibitFrame().getTitle();
        this.scrapDate = exhibitScrap.getCreatedDate();
        this.exhibitUrl = "http://" + exhibitScrap.getExhibitFrame().getUrl();
    }

}
