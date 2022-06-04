package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitRankingFrameDto;
import com.gajob.service.crawling.ExhibitRankingFrameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issue")
@CrossOrigin(origins = "http://localhost:3000/")
public class ExhibitRankingFrameController {

    private final ExhibitRankingFrameService exhibitRankingFrameService;

    @PostMapping("/exhibit-ranking-frames") // 공모전랭킹 정보 저장
    public ResponseEntity save(@RequestBody ExhibitRankingFrameDto exhibitRankingFrameDto) {
        return ResponseEntity.ok(exhibitRankingFrameService.save(exhibitRankingFrameDto));
    }

    @GetMapping("/exhibit-ranking-frames")  // 공모전랭킹 정보 전체 조회
    public ResponseEntity getAllExhibitRankingFrames() {
        return ResponseEntity.ok(exhibitRankingFrameService.getAllExhibitRankingFrames());
    }

    @DeleteMapping("/exhibit-ranking-frames/{exhibitRankingFrameId}")   // 공모전랭킹 정보 삭제
    public ResponseEntity delete(@PathVariable Long exhibitRankingFrameId) {
        return ResponseEntity.ok(exhibitRankingFrameService.delete(exhibitRankingFrameId));
    }

}
