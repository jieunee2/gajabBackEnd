package com.gajob.dto.crawling;

import com.gajob.entity.crawling.Exhibit;
import lombok.Data;

import java.util.Set;

@Data
public class ExhibitResponseDto {

    private Long id;
    private String title;
    private String organization;
    private Set categories;
    private String target;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;

    public ExhibitResponseDto(Exhibit exhibit) {
        this.id = exhibit.getId();
        this.title = exhibit.getTitle();
        this.organization = exhibit.getOrganization();
        this.categories = exhibit.getCategories();
        this.target = exhibit.getTarget();
        this.state = exhibit.getState();
        this.todayState = exhibit.getTodayState();
        this.url = exhibit.getUrl();
        this.imgUrl = exhibit.getImgUrl();
    }
}
