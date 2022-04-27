package com.gajob.controller.portfolio;

import com.gajob.dto.portfolio.CoverLetterItemDto;
import com.gajob.dto.portfolio.CoverLetterItemResponseDto;
import com.gajob.service.portfolio.CoverLetterItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = "http://localhost:3000/")
public class CoverLetterItemController {

    private final CoverLetterItemService coverLetterItemService;

    @PostMapping("items/{coverLetterId}")   // 문항 저장
    public ResponseEntity<CoverLetterItemResponseDto> save(@PathVariable Long coverLetterId,
                                                           @RequestBody CoverLetterItemDto coverLetterItemDto) {
        return ResponseEntity.ok(coverLetterItemService.save(coverLetterId, coverLetterItemDto));
    }

    @PutMapping({"/cover-letters/{coverLetterId}/items/{itemId}"})   // 문항 수정
    public ResponseEntity update(@PathVariable("coverLetterId") Long coverLetterId,
                                 @PathVariable("itemId") Long itemId,
                                 @RequestBody CoverLetterItemDto coverLetterItemDto) {
        return ResponseEntity.ok(coverLetterItemService.update(coverLetterId, itemId, coverLetterItemDto));
    }

    @DeleteMapping("/items/{itemId}")   // 문항 삭제
    public ResponseEntity delete(@PathVariable Long itemId) {
        return ResponseEntity.ok(coverLetterItemService.delete(itemId));
    }

}
