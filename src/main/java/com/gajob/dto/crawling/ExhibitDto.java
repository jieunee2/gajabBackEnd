package com.gajob.dto.crawling;

import com.gajob.entity.crawling.Exhibit;
import lombok.Data;

@Data
public class ExhibitDto {

    private String title;
    private String organization;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;

    public ExhibitDto(Exhibit exhibit) {
        this.title = exhibit.getTitle();
        this.organization = exhibit.getOrganization();
        this.state = exhibit.getState();
        this.todayState = exhibit.getTodayState();
        this.url = exhibit.getUrl();
        this.imgUrl = exhibit.getImgUrl();
    }
}
