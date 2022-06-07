package com.gajob.controller.study;

import com.gajob.dto.study.StudyRecruitmentDto;
import com.gajob.dto.study.StudyRecruitmentUpdateDto;
import com.gajob.service.study.StudyRecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study/recruitment")
@CrossOrigin(origins = "http://localhost:3000/")
public class StudyRecruitmentController {

  private final StudyRecruitmentService studyRecruitmentService;

  // 스터디 모임 신청
  @PostMapping("/{postId}")
  public ResponseEntity support(@PathVariable Long postId,
      @RequestBody StudyRecruitmentDto studyRecruitmentDto) {
    return ResponseEntity.ok(studyRecruitmentService.support
        (postId, studyRecruitmentDto));
  }

  // 스터디 모임 신청 취소
  @DeleteMapping("/{postId}")
  public ResponseEntity supportCancel(@PathVariable Long postId) {
    return ResponseEntity.ok(studyRecruitmentService.supportCancel(postId));

  }

  //스터디 모임 신청자 전체 조회
  @GetMapping("/{postId}")
  public ResponseEntity getAllSupport(@PathVariable Long postId) {
    return ResponseEntity.ok(studyRecruitmentService.getAllSupport(postId));
  }

  //스터디 지원자들의 모집결과 설정
  @PostMapping("/{postId}/result/{supplyId}")
  public ResponseEntity setResult(@PathVariable("postId") Long postId,
      @PathVariable("supplyId") Long supplyId,
      @RequestBody StudyRecruitmentUpdateDto studyRecruitmentUpdateDto) throws Exception {
    return ResponseEntity.ok(
        studyRecruitmentService.setResult(postId, supplyId, studyRecruitmentUpdateDto));
  }
}
