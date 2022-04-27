package com.gajob.controller.portfolio;

import com.gajob.dto.portfolio.CoverLetterDto;
import com.gajob.service.portfolio.CoverLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = "http://localhost:3000/")
public class CoverLetterController {

    private final CoverLetterService coverLetterService;

    @PostMapping("/cover-letters")   // 자소서 저장
    public ResponseEntity save(@RequestBody CoverLetterDto coverLetterDto) {
        return ResponseEntity.ok(coverLetterService.save(coverLetterDto));
    }

    @GetMapping("/cover-letters/{coverLetterId}")    // 자소서 조회
    public ResponseEntity getCoverLetter(@PathVariable Long coverLetterId) {
        return ResponseEntity.ok(coverLetterService.getCoverLetter(coverLetterId));
    }

    @GetMapping("/cover-letters") // 자소서 전체 조회
    public ResponseEntity getAllCoverLetter() {
        return ResponseEntity.ok(coverLetterService.getAllCoverLetter());
    }

    @PutMapping("/cover-letters/{coverLetterId}")    // 자소서 수정
    public ResponseEntity update(@PathVariable Long coverLetterId, @RequestBody CoverLetterDto coverLetterDto) {
        return ResponseEntity.ok(coverLetterService.update(coverLetterId, coverLetterDto));
    }

    @DeleteMapping("cover-letters/{coverLetterId}")  // 자소서 삭제
    public  ResponseEntity delete(@PathVariable Long coverLetterId) {
        return ResponseEntity.ok(coverLetterService.delete(coverLetterId));
    }

}
