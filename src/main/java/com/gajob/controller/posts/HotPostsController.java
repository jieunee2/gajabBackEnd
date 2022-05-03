package com.gajob.controller.posts;

import com.gajob.service.posts.HotPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "http://localhost:3000/")
public class HotPostsController {

  private final HotPostsService hotPostsService;

  @GetMapping("/hotPosts")
  public ResponseEntity getHotPosts() {
    return ResponseEntity.ok(hotPostsService.getHotPosts());
  }

}
