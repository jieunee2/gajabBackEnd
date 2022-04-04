package com.gajob.dto;

import com.gajob.entity.Exhibit;
import lombok.Data;

@Data
public class ExhibitDto {

    private String title;
    private String organization;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;

    public ExhibitDto(Exhibit fair) {
        this.title = fair.getTitle();
        this.organization = fair.getOrganization();
        this.state = fair.getState();
        this.todayState = fair.getTodayState();
        this.url = fair.getUrl();
        this.imgUrl = fair.getImgUrl();
    }
}
