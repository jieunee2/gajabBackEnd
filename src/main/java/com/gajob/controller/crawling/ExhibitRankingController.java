package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitRankingDto;
import com.gajob.entity.crawling.ExhibitRanking;
import com.gajob.service.crawling.ExhibitRankingCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/issue")
public class ExhibitRankingController {

    private final ExhibitRankingCrawling exhibitRankingCrawling;

    // 공모전 랭킹정보 저장
    @PostMapping("/exhibit-rankings")
    public ResponseEntity<ExhibitRankingDto> saveExhibitRanking() throws Exception {
        return ResponseEntity.ok(exhibitRankingCrawling.saveExhibitRanking(new ExhibitRankingDto(ExhibitRanking.builder().build())));
    }

    // 공모전 랭킹정보 조회
    @GetMapping("/exhibit-rankings")
    public ResponseEntity<ExhibitRankingDto> getAllExhibitRanking() {
        return new ResponseEntity(exhibitRankingCrawling.getAllExhibitRanking(), HttpStatus.OK);
    }

    // 공모전 랭킹정보 낱개 조회
    @GetMapping("exhibit-rankings/{exhibitRankingId}")
    public ResponseEntity getExhibitRanking(@PathVariable Long exhibitRankingId) {
        return ResponseEntity.ok(exhibitRankingCrawling.getExhibitRanking(exhibitRankingId));
    }

}
