package com.gajob.controller;

import com.gajob.dto.NewsDto;
import com.gajob.entity.News;
import com.gajob.service.crawling.NewsCrawling;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/crawling")
public class NewsController {

  private final NewsCrawling newsCrawling;

  @GetMapping("/news") // 크롤링한 데이터 가져오기
  public List<News> getNewsData(String url) throws IOException {
    return newsCrawling.getNewsData(newsCrawling.getNewsUrl(1));
  }

  @PostMapping("/save") // 크롤링한 데이터 DB 저장
  public ResponseEntity<NewsDto> newsSave() throws Exception {
    return ResponseEntity.ok(newsCrawling.newsSave(new NewsDto(News.builder().build())));
  }

}
