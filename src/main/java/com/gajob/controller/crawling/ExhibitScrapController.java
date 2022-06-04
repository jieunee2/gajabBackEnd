package com.gajob.controller.crawling;

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

    @PostMapping("/scraps/{exhibitFrameId}")  // 공모전 스크랩 기능
    public String scrap(@PathVariable Long exhibitFrameId) {
        return exhibitScrapService.scrap(exhibitFrameId);
    }

    @GetMapping("/scraps")   // 스크랩한 공모전 전체 조회
    public ResponseEntity getScrap() {
        return ResponseEntity.ok(exhibitScrapService.getScrap());
    }

}
