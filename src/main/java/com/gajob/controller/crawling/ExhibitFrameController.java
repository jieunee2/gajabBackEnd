package com.gajob.controller.crawling;

import com.gajob.dto.crawling.ExhibitFrameDto;
import com.gajob.service.crawling.ExhibitFrameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issue")
@CrossOrigin(origins = "http://localhost:3000/")
public class ExhibitFrameController {

    private final ExhibitFrameService exhibitFrameService;

    @PostMapping("/exhibit-frames") // 공모전 정보 저장
    public ResponseEntity save(@RequestBody ExhibitFrameDto exhibitFrameDto) {
        return ResponseEntity.ok(exhibitFrameService.save(exhibitFrameDto));
    }

    @GetMapping("/exhibit-frames")  // 공모전 정보 전체 조회
    public ResponseEntity getAllExhibitFrames() {
        return ResponseEntity.ok(exhibitFrameService.getAllExhibitFrames());
    }

    @DeleteMapping("/exhibit-frames/{exhibitFrameId}")   // 공모전 정보 삭제
    public ResponseEntity delete(@PathVariable Long exhibitFrameId) {
        return ResponseEntity.ok(exhibitFrameService.delete(exhibitFrameId));
    }

}
