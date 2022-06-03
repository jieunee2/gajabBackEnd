package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitScrapResponseDto;

import java.util.List;

public interface ExhibitScrapService {

    String scrap(Long exhibitId);   // 공모전 스크랩 기능

    List<ExhibitScrapResponseDto> getScrap();   // 공모전 스크랩 목록 조회

}
