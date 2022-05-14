package com.gajob.controller.openApi;

import com.gajob.service.openApi.JobScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("issue/job-posting")
@CrossOrigin(origins = "http://localhost:3000/")
public class JobScrapController {

    private final JobScrapService jobScrapService;

    @PostMapping("/scrap/{jobId}")   // 채용정보 스크랩 기능
    public String scrap(@PathVariable Long jobId) {
        return jobScrapService.scrap(jobId);
    }

    @GetMapping("/scrap")   // 스크랩한 채용정보 전체 조회
    public ResponseEntity getScrap() {
        return ResponseEntity.ok(jobScrapService.getScrap());
    }

}
