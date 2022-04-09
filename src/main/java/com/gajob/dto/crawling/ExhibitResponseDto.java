package com.gajob.dto.crawling;

import com.gajob.entity.crawling.Exhibit;
import lombok.Data;

@Data
public class ExhibitResponseDto {

    private Long id;
    private String title;
    private String organization;
    private String category;
    private String target;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;

    public ExhibitResponseDto(Exhibit exhibit) {
        this.id = exhibit.getId();
        this.title = exhibit.getTitle();
        this.organization = exhibit.getOrganization();
        this.category = exhibit.getCategory();
        this.target = exhibit.getTarget();
        this.state = exhibit.getState();
        this.todayState = exhibit.getTodayState();
        this.url = exhibit.getUrl();
        this.imgUrl = exhibit.getImgUrl();
    }
}
