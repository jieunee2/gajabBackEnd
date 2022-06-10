package com.gajob.repository.crawling;

import com.gajob.entity.crawling.ExhibitRankingScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitRankingScrapRepository extends JpaRepository<ExhibitRankingScrap, Long> {

/*
    -공모전랭킹 스크랩 기능(크롤링한 공모전랭킹 정보를 바로 스크랩)

    @Query("select e from ExhibitRankingScrap e")
    List<ExhibitRankingScrap> findAll();

    Optional<ExhibitRankingScrap> findByUserAndExhibitRankingFrame(User user, ExhibitRankingFrame exhibitRankingFrame);

    Optional<ExhibitRankingScrap> deleteByUserAndExhibitRankingFrame(User user, ExhibitRankingFrame exhibitRankingFrame);

*/

}
