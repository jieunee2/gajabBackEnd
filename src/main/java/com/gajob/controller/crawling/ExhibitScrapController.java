package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitScrapDto;
import com.gajob.service.crawling.ExhibitScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issue/exhibits")
@CrossOrigin(origins = "http://localhost:3000/")
public class ExhibitScrapController {

    private final ExhibitScrapService exhibitScrapService;

    @PostMapping("/scraps") // 공모전 스크랩 저장
    public ResponseEntity save(@RequestBody ExhibitScrapDto exhibitScrapDto) {
        return ResponseEntity.ok(exhibitScrapService.save(exhibitScrapDto));
    }

    @GetMapping("/scraps")  // 공모전 스크랩 전체 조회
    public ResponseEntity getAllExhibitScraps() {
        return ResponseEntity.ok(exhibitScrapService.getAllExhibitScraps());
    }

    @DeleteMapping("/scraps/{exhibitScrapId}")   // 공모전 스크랩 삭제
    public ResponseEntity delete(@PathVariable Long exhibitScrapId) {
        return ResponseEntity.ok(exhibitScrapService.delete(exhibitScrapId));
    }

/*
    -공모전 스크랩 기능(크롤링한 공모전 정보를 바로 스크랩)

    private final ExhibitScrapService exhibitScrapService;

    @PostMapping("/scraps/{exhibitFrameId}")  // 공모전 스크랩 기능
    public String scrap(@PathVariable Long exhibitFrameId) {
        return exhibitScrapService.scrap(exhibitFrameId);
    }

    @GetMapping("/scraps")   // 스크랩한 공모전 전체 조회
    public ResponseEntity getScrap() {
        return ResponseEntity.ok(exhibitScrapService.getScrap());
    }
*/

}
