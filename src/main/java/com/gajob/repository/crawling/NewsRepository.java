package com.gajob.repository.crawling;

import com.gajob.entity.crawling.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

}
