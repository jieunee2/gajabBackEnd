package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitFrame;
import lombok.Data;

import java.util.Set;

@Data
public class ExhibitFrameResponseDto {

    private Long id;
    private String title;
    private String organization;
    private Set categories;
    private Set targets;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;

    public ExhibitFrameResponseDto(ExhibitFrame exhibitFrame) {
        this.id = exhibitFrame.getId();
        this.title = exhibitFrame.getTitle();
        this.organization = exhibitFrame.getOrganization();
        this.targets = exhibitFrame.getTargets();
        this.state = exhibitFrame.getState();
        this.todayState = exhibitFrame.getTodayState();
        this.url = exhibitFrame.getUrl();
        this.imgUrl = exhibitFrame.getImgUrl();
    }
}
