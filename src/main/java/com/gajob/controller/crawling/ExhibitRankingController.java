package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitRankingDto;
import com.gajob.entity.crawling.ExhibitRanking;
import com.gajob.service.crawling.ExhibitRankingCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/issue")
public class ExhibitRankingController {

    private final ExhibitRankingCrawling exhibitRankingCrawling;

    // 공모전 랭킹정보 저장
    @PostMapping("/exhibit-ranking")
    public ResponseEntity<ExhibitRankingDto> saveExhibitRanking() throws Exception {
        return ResponseEntity.ok(exhibitRankingCrawling.saveExhibitRanking(new ExhibitRankingDto(ExhibitRanking.builder().build())));
    }

    // 공모전 랭킹정보 조회
    @GetMapping("/exhibit-ranking")
    public ResponseEntity<ExhibitRankingDto> getExhibitRanking() {
        return new ResponseEntity(exhibitRankingCrawling.getExhibitRanking(), HttpStatus.OK);
    }
}
