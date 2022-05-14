package com.gajob.service.openApi;

import com.gajob.dto.openApi.JobScrapResponseDto;

import java.util.List;

public interface JobScrapService {

    String scrap(Long jobId);   // 채용정보 스크랩 기능

    List<JobScrapResponseDto> getScrap();   // 채용정보 스크랩 목록 조회

}
