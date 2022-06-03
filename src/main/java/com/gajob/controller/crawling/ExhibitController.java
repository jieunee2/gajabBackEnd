package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitDto;
import com.gajob.entity.crawling.Exhibit;
import com.gajob.service.crawling.ExhibitCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/issue")
public class ExhibitController {

    private final ExhibitCrawling exhibitCrawling;


    @GetMapping("/exhibits") // 크롤링한 공모전 정보 전체 조회
    public ResponseEntity<ExhibitDto> getAllExhibit() {
        return new ResponseEntity(exhibitCrawling.getAllExhibit(),
                HttpStatus.OK);
    }

    @GetMapping("/exhibits/{exhibitId}")  // 저장한 공모전 낱개 조회
    public ResponseEntity getExhibit(@PathVariable Long exhibitId) {
        return ResponseEntity.ok(exhibitCrawling.getExhibit(exhibitId));
    }

    @PostMapping("/exhibit") // 크롤링한 뉴스 데이터 DB 저장
    public ResponseEntity<ExhibitDto> saveExhibit() throws Exception {
        return ResponseEntity.ok(exhibitCrawling.saveExhibit(new ExhibitDto(Exhibit.builder().build())));
    }

}
