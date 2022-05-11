package com.gajob.repository.crawling;

import com.gajob.entity.crawling.ExhibitRanking;
import com.gajob.entity.crawling.ExhibitRankingScrap;
import com.gajob.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExhibitRankingScrapRepository extends JpaRepository<ExhibitRankingScrap, Long> {

    @Query("select e from ExhibitRankingScrap e")
    List<ExhibitRankingScrap> findAll();

    Optional<ExhibitRankingScrap> findByUserAndExhibitRanking(User user, ExhibitRanking exhibitRanking);

    Optional<ExhibitRankingScrap> deleteByUserAndExhibitRanking(User user, ExhibitRanking exhibitRanking);

}
