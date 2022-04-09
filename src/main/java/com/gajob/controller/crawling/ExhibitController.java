package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitDto;
import com.gajob.entity.crawling.Exhibit;
import com.gajob.service.crawling.ExhibitCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/issue")
public class ExhibitController {

  private final ExhibitCrawling exhibitCrawling;


  @GetMapping("/exhibit") // 크롤링한 공모전 데이터 가져오기
  public ResponseEntity<ExhibitDto> getExhibit() {
    return new ResponseEntity(exhibitCrawling.getExhibit(),
            HttpStatus.OK);
  }

  @PostMapping("/exhibit") // 크롤링한 뉴스 데이터 DB 저장
  public ResponseEntity<ExhibitDto> saveExhibit() throws Exception {
    return ResponseEntity.ok(exhibitCrawling.saveExhibit(new ExhibitDto(Exhibit.builder().build())));
  }

}
