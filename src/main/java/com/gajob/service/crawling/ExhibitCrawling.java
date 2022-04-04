package com.gajob.service.crawling;

import com.gajob.dto.ExhibitDto;
import com.gajob.entity.Exhibit;

import java.io.IOException;
import java.util.List;

public interface ExhibitCrawling {

    String getExhibitUrl(int page);    // 각 페이지 별로 url 추출

    List<Exhibit> getExhibitData(String URL) throws IOException;  // 사이트 내 데이터 추출

    ExhibitDto exhibitSave(ExhibitDto exhibitDto) throws Exception;   // 크롤링한 공모전 정보들을 DB에 저장

}
