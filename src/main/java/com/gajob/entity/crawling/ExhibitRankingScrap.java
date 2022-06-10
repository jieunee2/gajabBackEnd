package com.gajob.entity.crawling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
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
public class ExhibitRankingScrap extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibit_ranking_id")
    private Long id;

    @Column
    private String title;   // 공모전 이름

    @Column
    private String ranking; // 공모전 순위

    @Column
    private String rankingState;    // 공모전 순위상태

    @Column
    private String host;    // 공모전 주최

    @Column
    private String perks;   // 공모전 특전

    @ElementCollection
    @CollectionTable(name = "exhibit_ranking_frame_category", joinColumns = @JoinColumn(name = "exhibit_ranking_frame_id"))
    @Column(name = "category_name")
    private Set<String> categories = new HashSet<>();   // 공모전 카테고리

    @ElementCollection
    @CollectionTable(name = "exhibit_ranking_frame_target", joinColumns = @JoinColumn(name = "exhibit_ranking_frame_id"))
    @Column(name = "target_name")
    private Set<String> targets = new HashSet<>();  // 공모전 참가대상

    @Column
    private String dDay;    // 공모전 D-day

    @Column
    private String state;   // 공모전 진행상태

    @Column
    private String url; // 공모전 URL

    @Column
    private String imgUrl;  // 공모전 이미지 URL

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

/*
    -공모전랭킹 스크랩 기능(크롤링한 공모전랭킹 정보를 바로 스크랩)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibit_ranking_id")
    private ExhibitRankingFrame exhibitRankingFrame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"exhibit_ranking"})
    private User user;

    public ExhibitRankingScrap(ExhibitRankingFrame exhibitRankingFrame, User user) {
        this.exhibitRankingFrame = exhibitRankingFrame;
        this.user = user;
    }
*/

}
