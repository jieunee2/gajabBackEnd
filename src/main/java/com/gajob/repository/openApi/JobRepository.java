package com.gajob.repository.openApi;

import com.gajob.entity.openApi.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
