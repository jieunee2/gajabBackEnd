package com.gajob.repository.crawling;

import com.gajob.entity.crawling.ExhibitScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitScrapRepository extends JpaRepository<ExhibitScrap, Long> {

/*
    -공모전 스크랩 기능(크롤링한 공모전 정보를 바로 스크랩)

    @Query("select e from ExhibitScrap e")
    List<ExhibitScrap> findAll();

    Optional<ExhibitScrap> findByUserAndExhibitFrame(User user, ExhibitFrame exhibitFrame);

    Optional<ExhibitScrap> deleteByUserAndExhibitFrame(User user, ExhibitFrame exhibitFrame);
*/

}
