package com.gajob.repository.openApi;

import com.gajob.entity.openApi.JobFrame;
import com.gajob.entity.openApi.JobScrap;
import com.gajob.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobScrapRepository extends JpaRepository<JobScrap, Long> {

    @Query("select j from JobScrap j")
    List<JobScrap> findAll();

    Optional<JobScrap> findByUserAndJobFrame(User user, JobFrame jobFrame);

    Optional<JobScrap> deleteByUserAndJobFrame(User user, JobFrame jobFrame);

}
