package com.gajob.entity.crawling;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    private String title;                   // 공모전 이름

    private String ranking;                 // 공모전 순위

    private String rankingState;            // 공모전 순위상태

    private String host;                    // 공모전 주최

    private String perks;                   // 공모전 특전

    @ElementCollection
    @CollectionTable(name = "exhibit_ranking_category", joinColumns = @JoinColumn(name = "exhibit_ranking_id"))
    @Column(name = "category_name")
    private Set<String> categories = new HashSet<>();   // 공모전 카테고리

    @ElementCollection
    @CollectionTable(name = "exhibit_ranking_target", joinColumns = @JoinColumn(name = "exhibit_ranking_id"))
    @Column(name = "target_name")
    private Set<String> targets = new HashSet<>();      // 공모전 참가대상

    private String dDay;                    // 공모전 D-day

    private String state;                   // 공모전 진행상태

    private String url;                     // 공모전 URL

    private String imgUrl;                  // 공모전 이미지 URL

    public ExhibitRanking(String title, String ranking, String rankingState, String host, String perks,
                          Set categories, Set targets, String dDay, String state, String url, String imgUrl) {
        this.title = title;
        this.ranking = ranking;
        this.rankingState = rankingState;
        this.host = host;
        this.perks = perks;
        this.categories = categories;
        this.targets = targets;
        this.dDay = dDay;
        this.state = state;
        this.url = url;
        this.imgUrl = imgUrl;
    }
}
