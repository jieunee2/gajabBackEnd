package com.gajob.controller.posts;

import com.gajob.dto.posts.PostsDto;
import com.gajob.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class PostsController {

  private final PostsService postsService;

  @PostMapping("/posts") // 게시물 저장
  public ResponseEntity save(@RequestBody PostsDto postsDto) {
    return ResponseEntity.ok(postsService.save(postsDto));
  }

  @GetMapping("/posts/{postId}") // 게시물 조회(조회시 조회수 증가)
  public ResponseEntity getPosts(@PathVariable Long postId) {
    return ResponseEntity.ok(postsService.getPosts(postId));
  }

  @GetMapping("/posts") // 게시물 전체 조회
  public ResponseEntity getAllPosts() {
    return ResponseEntity.ok(postsService.getAllPosts());
  }

  @PutMapping({"/posts/{postId}"}) // 게시물 수정
  public ResponseEntity update(@PathVariable Long postId, @RequestBody PostsDto postsDto) {
    return ResponseEntity.ok(postsService.update(postId, postsDto));
  }

  @DeleteMapping("/posts/{postId}")
  public ResponseEntity delete(@PathVariable Long postId) {
    return ResponseEntity.ok(postsService.delete(postId));
  }

}
