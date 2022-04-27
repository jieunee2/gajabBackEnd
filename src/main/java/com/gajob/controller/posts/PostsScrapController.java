package com.gajob.controller.posts;

import com.gajob.service.posts.PostsScrapService;
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
@RequestMapping("/community")
@CrossOrigin(origins = "http://localhost:3000/")
public class PostsScrapController {

  private final PostsScrapService postsScrapService;

  @PostMapping("/scrap/{postId}") // 스크랩 기능
  public String scrap(@PathVariable Long postId) {
    return postsScrapService.scrap(postId);
  }

  @GetMapping("/scrap") // 게시물 전체 조회
  public ResponseEntity getScrap() {
    return ResponseEntity.ok(postsScrapService.getScrap());
  }

}
