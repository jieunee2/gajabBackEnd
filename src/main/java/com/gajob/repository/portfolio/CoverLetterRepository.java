package com.gajob.repository.portfolio;

import com.gajob.entity.portfolio.CoverLetter;
import com.gajob.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long> {

    @Query("select c from CoverLetter c")
    List<CoverLetter> findAll();

    List<CoverLetter> findAllByUser(User user);

}
