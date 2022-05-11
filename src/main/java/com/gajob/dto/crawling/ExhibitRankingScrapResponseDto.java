package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitRankingScrap;
import lombok.Data;

@Data
public class ExhibitRankingScrapResponseDto {

    private Long id;
    private String title;
    private String scrapDate;
    private String exhibitRankingUrl;

    public ExhibitRankingScrapResponseDto(ExhibitRankingScrap exhibitRankingScrap) {
        this.id = exhibitRankingScrap.getId();
        this.title = exhibitRankingScrap.getExhibitRanking().getTitle();
        this.scrapDate = exhibitRankingScrap.getCreatedDate();
        this.exhibitRankingUrl = exhibitRankingScrap.getExhibitRanking().getUrl();
    }

}
