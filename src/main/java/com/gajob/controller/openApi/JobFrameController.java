package com.gajob.controller.openApi;

import com.gajob.dto.openApi.JobFrameDto;
import com.gajob.service.openApi.JobFrameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issue")
@CrossOrigin(origins = "http://localhost:3000/")
public class JobFrameController {

    private final JobFrameService jobFrameService;

    @PostMapping("/job-posting-frames") // 채용정보 저장
    public ResponseEntity save(@RequestBody JobFrameDto jobFrameDto) {
        return ResponseEntity.ok(jobFrameService.save(jobFrameDto));
    }

    @GetMapping("/job-posting-frames")  // 채용정보 전체 조회
    public ResponseEntity getAllJobFrames() {
        return ResponseEntity.ok(jobFrameService.getAllJobFrames());
    }

    @DeleteMapping("/job-posting-frames/{jobFrameId}")   // 채용정보 삭제
    public ResponseEntity delete(@PathVariable Long jobFrameId) {
        return ResponseEntity.ok(jobFrameService.delete(jobFrameId));
    }

}
