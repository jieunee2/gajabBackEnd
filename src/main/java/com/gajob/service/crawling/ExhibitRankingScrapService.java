package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingScrapDto;
import com.gajob.dto.crawling.ExhibitRankingScrapResponseDto;

import java.util.List;

public interface ExhibitRankingScrapService {

    ExhibitRankingScrapResponseDto save(ExhibitRankingScrapDto exhibitRankingScrapDto);  // 공모전랭킹 스크랩 저장

    List<ExhibitRankingScrapResponseDto> getAllExhibitRankingScraps();    // 공모전랭킹 스크랩 전체 조회

    String delete(Long exhibitRankingScrapId); // 공모전랭킹 스크랩 삭제

/*
    -공모전랭킹 스크랩 기능(크롤링한 공모전랭킹 정보를 바로 스크랩

    String scrap(Long exhibitRankingFrameId);    // 랭킹 공모전 스크랩 기능

    List<ExhibitRankingScrapResponseDto> getScrap();    // 랭킹 공모전 스크랩 목록 조회
*/

}
