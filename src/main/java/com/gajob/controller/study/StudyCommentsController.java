package com.gajob.controller.study;

import com.gajob.dto.study.StudyCommentsDto;
import com.gajob.dto.study.StudyCommentsResponseDto;
import com.gajob.service.study.StudyCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study")
@CrossOrigin(origins = "http://localhost:3000/")
public class StudyCommentsController {

  private final StudyCommentsService studyCommentsService;

  @PostMapping("/comments/{postId}") // 댓글 저장
  public ResponseEntity<StudyCommentsResponseDto> save(@PathVariable Long postId,
      @RequestBody StudyCommentsDto studyCommentsDto) {
    return ResponseEntity.ok(studyCommentsService.save(postId, studyCommentsDto));
  }

  @GetMapping("/comments/{postId}") // 게시물 별로 댓글 조회
  public ResponseEntity getStudyComments(@PathVariable Long postId) {
    return ResponseEntity.ok(studyCommentsService.getStudyComments(postId));
  }

  @PutMapping({"/posts/{postId}/comments/{commentId}"}) //댓글 수정
  public ResponseEntity update(@PathVariable("postId") Long postId,
      @PathVariable("commentId") Long commentId,
      @RequestBody StudyCommentsDto studyCommentsDto) {
    return ResponseEntity.ok(studyCommentsService.update(postId, commentId, studyCommentsDto));
  }

  @DeleteMapping("/comments/{commentId}") //댓글 삭제
  public ResponseEntity delete(@PathVariable Long commentId) {
    return ResponseEntity.ok(studyCommentsService.delete(commentId));
  }


}
