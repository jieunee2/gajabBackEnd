package com.gajob.repository.crawling;

import com.gajob.entity.crawling.ExhibitFrame;
import com.gajob.entity.crawling.ExhibitScrap;
import com.gajob.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExhibitScrapRepository extends JpaRepository<ExhibitScrap, Long> {

    @Query("select e from ExhibitScrap e")
    List<ExhibitScrap> findAll();

    Optional<ExhibitScrap> findByUserAndExhibitFrame(User user, ExhibitFrame exhibitFrame);

    Optional<ExhibitScrap> deleteByUserAndExhibitFrame(User user, ExhibitFrame exhibitFrame);

}
