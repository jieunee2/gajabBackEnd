package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitDto;
import com.gajob.dto.crawling.ExhibitResponseDto;
import com.gajob.dto.crawling.NewsResponseDto;
import com.gajob.entity.crawling.Exhibit;

import java.io.IOException;
import java.util.List;

public interface ExhibitCrawling {

    String getExhibitUrl(int page);    // 각 페이지 별로 url 추출

    List<Exhibit> getExhibitData(String URL) throws IOException;  // 사이트 내 데이터 추출

    ExhibitDto saveExhibit(ExhibitDto exhibitDto) throws Exception;   // 크롤링한 공모전 정보들을 DB에 저장

    List<ExhibitResponseDto> getExhibit(); // 저장한 공모전 정보들 읽어오기

}
