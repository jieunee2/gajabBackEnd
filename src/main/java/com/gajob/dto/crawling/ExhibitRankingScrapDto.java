package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitRankingScrap;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitRankingScrapDto {

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
    private User user;

    public ExhibitRankingScrap toEntity(User user) {
        ExhibitRankingScrap exhibitRankingScrap = ExhibitRankingScrap.builder().title(title).ranking(ranking).rankingState(rankingState)
                .host(host).perks(perks).categories(categories).targets(targets).dDay(dDay)
                .state(state).url(url).imgUrl(imgUrl).user(user).build();

        return exhibitRankingScrap;
    }
}
