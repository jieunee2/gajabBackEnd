package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitRankingScrap;
import lombok.Data;

import java.util.Set;

@Data
public class ExhibitRankingScrapResponseDto {

    private Long id;
    private String title;
    private String ranking;
    private String rankingState;
    private String host;
    private String perks;
    private Set categories;
    private Set targets;
    private String dDay;
    private String state;
    private String url;
    private String imgUrl;
    private String scrapDate;

    public ExhibitRankingScrapResponseDto(ExhibitRankingScrap exhibitRankingScrap) {
        this.id = exhibitRankingScrap.getId();
        this.title = exhibitRankingScrap.getTitle();
        this.ranking = exhibitRankingScrap.getRanking();
        this.rankingState = exhibitRankingScrap.getRankingState();
        this.host = exhibitRankingScrap.getHost();
        this.perks = exhibitRankingScrap.getPerks();
        this.categories = exhibitRankingScrap.getCategories();
        this.targets = exhibitRankingScrap.getTargets();
        this.dDay = exhibitRankingScrap.getDDay();
        this.state = exhibitRankingScrap.getState();
        this.url = exhibitRankingScrap.getUrl();
        this.imgUrl = exhibitRankingScrap.getImgUrl();
        this.scrapDate = exhibitRankingScrap.getCreatedDate();
    }

/*
    -공모전랭킹 스크랩 기능(크롤링한 공모전랭킹 정보를 바로 스크랩)

    private Long id;
    private String title;
    private String scrapDate;
    private String exhibitRankingUrl;

    public ExhibitRankingScrapResponseDto(ExhibitRankingScrap exhibitRankingScrap) {
        this.id = exhibitRankingScrap.getId();
        this.title = exhibitRankingScrap.getExhibitRankingFrame().getTitle();
        this.scrapDate = exhibitRankingScrap.getCreatedDate();
        this.exhibitRankingUrl = exhibitRankingScrap.getExhibitRankingFrame().getUrl();
    }
*/

}
