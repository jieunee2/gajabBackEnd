package com.gajob.controller.portfolio;

import com.gajob.dto.portfolio.PassCoverLetterDto;
import com.gajob.entity.portfolio.PassCoverLetter;
import com.gajob.service.portfolio.PassCoverLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/portfolio")
public class PassCoverLetterController {

    private final PassCoverLetterService passCoverLetterService;

    @GetMapping("/pass-cover-letters")   // 크롤링한 합격자소서 데이터 가져오기
    public ResponseEntity<PassCoverLetterDto> getPassCoverLetter() {
        return new ResponseEntity(passCoverLetterService.getPassCoverLetter(),
                HttpStatus.OK);
    }

    @PostMapping("/pass-cover-letters")  // 크롤링한 합격자소서 데이터 DB 저장
    public ResponseEntity<PassCoverLetterDto> savePassCoverLetter() throws Exception {
        return ResponseEntity.ok(passCoverLetterService.savePassCoverLetter(new PassCoverLetterDto(PassCoverLetter.builder().build())));
    }
}
