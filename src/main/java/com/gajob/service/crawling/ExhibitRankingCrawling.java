package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingDto;
import com.gajob.dto.crawling.ExhibitRankingResponseDto;
import com.gajob.entity.crawling.ExhibitRanking;

import java.io.IOException;
import java.util.List;

public interface ExhibitRankingCrawling {

    // 각 페이지 별로 url 추출
    String getExhibitRankingUrl(int page);

    // 사이트 내 데이터 추출
    List<ExhibitRanking> getExhibitRankingData(String URL) throws IOException;

    // 크롤링한 공모전 랭킹 정보들을 DB에 저장
    ExhibitRankingDto saveExhibitRanking(ExhibitRankingDto exhibitRankingDto) throws Exception;

    // 저장한 공모전 랭킹 정보 전체 조회
    List<ExhibitRankingResponseDto> getAllExhibitRanking();

    // 저장한 공모전 랭킹 정보 낱개 조회
    ExhibitRankingResponseDto getExhibitRanking(Long exhibitRankingId);
}
