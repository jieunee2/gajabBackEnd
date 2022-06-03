package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitRanking;
import lombok.Data;

import java.util.Set;

@Data
public class ExhibitRankingResponseDto {

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

    public ExhibitRankingResponseDto(ExhibitRanking exhibitRanking) {
        this.id = exhibitRanking.getId();
        this.title = exhibitRanking.getTitle();
        this.ranking = exhibitRanking.getRanking();
        this.rankingState = exhibitRanking.getRankingState();
        this.host = exhibitRanking.getHost();
        this.perks = exhibitRanking.getPerks();
        this.categories = exhibitRanking.getCategories();
        this.targets = exhibitRanking.getTargets();
        this.dDay = exhibitRanking.getDDay();
        this.state = exhibitRanking.getState();
        this.url = exhibitRanking.getUrl();
        this.imgUrl = exhibitRanking.getImgUrl();
    }
}
