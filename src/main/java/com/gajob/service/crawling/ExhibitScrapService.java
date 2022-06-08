package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitScrapDto;
import com.gajob.dto.crawling.ExhibitScrapResponseDto;

import java.util.List;

public interface ExhibitScrapService {

    ExhibitScrapResponseDto save(ExhibitScrapDto exhibitScrapDto);  // 공모전 스크랩 저장

    List<ExhibitScrapResponseDto> getAllExhibitScraps();    // 공모전 스크랩 전체 조회

    String delete(Long exhibitScrapId); // 공모전 스크랩 삭제

/*
    -공모전 스크랩 기능(크롤링한 공모전 정보를 바로 스크랩)

    String scrap(Long exhibitId);   // 공모전 스크랩 기능

    List<ExhibitScrapResponseDto> getScrap();   // 공모전 스크랩 목록 조회
*/

}
