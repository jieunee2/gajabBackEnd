package com.gajob.controller.mypage;

import com.gajob.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mypage")
@CrossOrigin(origins = "http://localhost:3000/")
public class MyPageController {

  private final MyPageService myPageService;

  @GetMapping("/posts") // 사용자가 작성한 JOB담 커뮤니티 게시물 전체 조회
  public ResponseEntity getUserAllPosts() {
    return ResponseEntity.ok(myPageService.getUserAllPosts());
  }

  @GetMapping("/study") // 사용자가 작성한 스터디 매칭 시스템 커뮤니티 게시물 전체 조회
  public ResponseEntity getUserAllStudy() {
    return ResponseEntity.ok(myPageService.getUserAllStudy());
  }

  @GetMapping("/cover-letters") // 사용자가 작성한 자소서 전체 조회
  public ResponseEntity getUserAllCoverLetters() {
    return ResponseEntity.ok(myPageService.getUserAllCoverLetters());
  }

  @GetMapping("/supply-study") // 사용자가 지원한 스터디 전체 조회
  public ResponseEntity getUserAllSupplyStudy() {
    return ResponseEntity.ok(myPageService.getUserAllSupplyStudy());
  }

}
