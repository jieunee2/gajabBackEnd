package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitScrap;
import lombok.Data;

import java.util.Set;

@Data
public class ExhibitScrapResponseDto {

    private Long id;
    private String title;
    private String organization;
    private Set categories;
    private Set targets;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;
    private String scrapDate;

    public ExhibitScrapResponseDto(ExhibitScrap exhibitScrap) {
        this.id = exhibitScrap.getId();
        this.title = exhibitScrap.getTitle();
        this.organization = exhibitScrap.getOrganization();
        this.targets = exhibitScrap.getTargets();
        this.state = exhibitScrap.getState();
        this.todayState = exhibitScrap.getTodayState();
        this.url = exhibitScrap.getUrl();
        this.imgUrl = exhibitScrap.getImgUrl();
        this.scrapDate = exhibitScrap.getCreatedDate();
    }

/*
    -공모전 스크랩 기능(크롤링한 공모전 정보를 바로 스크랩)

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
*/

}
