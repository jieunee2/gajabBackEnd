package com.gajob.service.openApi;


import com.gajob.dto.openApi.JobDto;
import com.gajob.dto.openApi.JobResponseDto;

import java.util.List;

public interface JobService {

    String getJobUrl(int page);

    String changeJson();

    JobDto saveJob(JobDto jobDto) throws Exception;

    List<JobResponseDto> getJob();

}
