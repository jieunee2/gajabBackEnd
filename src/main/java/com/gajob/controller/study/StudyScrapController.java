package com.gajob.controller.study;

import com.gajob.service.study.StudyScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study")
@CrossOrigin(origins = "http://localhost:3000/")
public class StudyScrapController {

  private final StudyScrapService studyScrapService;

  @PostMapping("/scrap/{postId}") // 스크랩 기능
  public String scrap(@PathVariable Long postId) {
    return studyScrapService.scrap(postId);
  }

  @GetMapping("/scrap") // 게시물 전체 조회
  public ResponseEntity getScrap() {
    return ResponseEntity.ok(studyScrapService.getScrap());
  }

}
