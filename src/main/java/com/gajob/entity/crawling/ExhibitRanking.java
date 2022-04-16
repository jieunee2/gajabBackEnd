package com.gajob.entity.crawling;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class ExhibitRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;           // 공모전 이름

    private String ranking;         // 공모전 순위

    private String rankingState;    // 공모전 순위상태

    private String host;            // 공모전 주최

    private String perks;           // 공모전 특전

    private String category;        // 공모전 카테고리

    private String target;          // 공모전 참가대상

    private String dDay;            // 공모전 D-day

    private String state;           // 공모전 진행상태

    private String url;             // 공모전 URL

    private String imgUrl;          // 공모전 이미지 URL

    public ExhibitRanking(String title, String ranking, String rankingState, String host, String perks,
                          String category, String target, String dDay, String state, String url, String imgUrl) {
        this.title = title;
        this.ranking = ranking;
        this.rankingState = rankingState;
        this.host = host;
        this.perks = perks;
        this.category = category;
        this.target = target;
        this.dDay = dDay;
        this.state = state;
        this.url = url;
        this.imgUrl = imgUrl;
    }
}
