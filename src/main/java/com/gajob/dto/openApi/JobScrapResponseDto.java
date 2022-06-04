package com.gajob.dto.openApi;

import com.gajob.entity.openApi.JobScrap;
import lombok.Data;

@Data
public class JobScrapResponseDto {

    private Long id;
    private String title;
    private String scrapDate;
    private String wantedInfoUrl;

    public JobScrapResponseDto(JobScrap jobScrap) {
        this.id = jobScrap.getId();
        this.title = jobScrap.getJobFrame().getTitle();
        this.scrapDate = jobScrap.getCreatedDate();
        this.wantedInfoUrl = jobScrap.getJobFrame().getWantedInfoUrl();
    }

}
