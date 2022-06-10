package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitRankingScrapDto;
import com.gajob.service.crawling.ExhibitRankingScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issue/exhibit-rankings")
@CrossOrigin(origins = "http://localhost:3000/")
public class ExhibitRankingScrapController {

    private final ExhibitRankingScrapService exhibitRankingScrapService;

    @PostMapping("/scraps") // 공모전랭킹 스크랩 저장
    public ResponseEntity save(@RequestBody ExhibitRankingScrapDto exhibitRankingScrapDto) {
        return ResponseEntity.ok(exhibitRankingScrapService.save(exhibitRankingScrapDto));
    }

    @GetMapping("/scraps")  // 공모전랭킹 스크랩 전체 조회
    public ResponseEntity getAllExhibitRankingScraps() {
        return ResponseEntity.ok(exhibitRankingScrapService.getAllExhibitRankingScraps());
    }

    @DeleteMapping("/scraps/{exhibitRankingScrapId}")   // 공모전랭킹 스크랩 삭제
    public ResponseEntity delete(@PathVariable Long exhibitRankingScrapId) {
        return ResponseEntity.ok(exhibitRankingScrapService.delete(exhibitRankingScrapId));
    }

/*
    -공모전랭킹 스크랩 기능(크롤링한 공모전랭킹 정보를 바로 스크랩)

    private final ExhibitRankingScrapService exhibitRankingScrapService;

    @PostMapping("/scraps/{exhibitRankingFrameId}")   // 랭킹 공모전 스크랩 기능
    public String scrap(@PathVariable Long exhibitRankingFrameId) {
        return exhibitRankingScrapService.scrap(exhibitRankingFrameId);
    }

    @GetMapping("/scraps")   // 스크랩한 랭킹 공모전 전체 조회
    public ResponseEntity getScrap() {
        return ResponseEntity.ok(exhibitRankingScrapService.getScrap());
    }
*/

}
