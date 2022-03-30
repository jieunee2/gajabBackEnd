package com.gajob.service.crawling;

import com.gajob.dto.NewsDto;
import com.gajob.entity.News;
import java.io.IOException;
import java.util.List;

public interface NewsCrawling {

  String getNewsUrl(int page); // 각 페이지 별로 url 추출

  List<News> getNewsData(String URL) throws IOException; // 사이트 내 데이터 추출

  NewsDto newsSave(NewsDto newsDto) throws Exception; // Crawling한 뉴스 정보들을 DB에 저장

  //  List<News> crawling() throws IOException; // getNewsData() 메소드를 호출하여 list에 저장

}