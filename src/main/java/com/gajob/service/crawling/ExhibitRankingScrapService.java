package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingScrapResponseDto;

import java.util.List;

public interface ExhibitRankingScrapService {

    String scrap(Long exhibitRankingFrameId);    // 랭킹 공모전 스크랩 기능

    List<ExhibitRankingScrapResponseDto> getScrap();    // 랭킹 공모전 스크랩 목록 조회

}
