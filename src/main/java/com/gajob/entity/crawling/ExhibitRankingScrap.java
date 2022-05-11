package com.gajob.entity.crawling;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class ExhibitRankingScrap extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibit_ranking_id")
    private ExhibitRanking exhibitRanking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"exhibit_ranking"})
    private User user;

    public ExhibitRankingScrap(ExhibitRanking exhibitRanking, User user) {
        this.exhibitRanking = exhibitRanking;
        this.user = user;
    }

}
