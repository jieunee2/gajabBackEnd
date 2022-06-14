package com.gajob.controller.study;

import com.gajob.dto.study.StudyDto;
import com.gajob.service.study.StudyService;
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
public class StudyController {

  private final StudyService studyService;

  @PostMapping("/posts") // 게시물 저장
  public ResponseEntity save(@RequestBody StudyDto studyDto) {
    return ResponseEntity.ok(studyService.save(studyDto));
  }

  @GetMapping("/posts/{postId}") // 게시물 조회(조회시 조회수 증가)
  public ResponseEntity getPosts(@PathVariable Long postId) {
    return ResponseEntity.ok(studyService.getPosts(postId));
  }

  @GetMapping("/posts") // 게시물 전체 조회
  public ResponseEntity getAllPosts() {
    studyService.statusChange(); //
    return ResponseEntity.ok(studyService.getAllPosts());
  }

  @PutMapping({"/posts/{postId}"}) // 게시물 수정
  public ResponseEntity update(@PathVariable Long postId, @RequestBody StudyDto studyDto) {
    return ResponseEntity.ok(studyService.update(postId, studyDto));
  }

  @DeleteMapping("/posts/{postId}") // 게시물 삭제
  public ResponseEntity delete(@PathVariable Long postId) {
    return ResponseEntity.ok(studyService.delete(postId));
  }

}
