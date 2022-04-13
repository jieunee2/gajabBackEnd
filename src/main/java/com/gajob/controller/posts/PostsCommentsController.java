package com.gajob.controller.posts;

import com.gajob.dto.posts.PostsCommentsDto;
import com.gajob.dto.posts.PostsCommentsResponseDto;
import com.gajob.service.posts.PostsCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

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
public class PostsCommentsController {

  private final PostsCommentsService postsCommentsService;


  @PostMapping("/comments/{postId}") // 댓글 저장
  public ResponseEntity<PostsCommentsResponseDto> save(@PathVariable Long postId,
      @RequestBody PostsCommentsDto postsCommentsDto) {
    return ResponseEntity.ok(postsCommentsService.save(postId, postsCommentsDto));
  }

  @PutMapping({"/posts/{postId}/comments/{commentId}"}) //댓글 수정
  public ResponseEntity update(@PathVariable("postId") Long postId,
      @PathVariable("commentId") Long commentId,
      @RequestBody PostsCommentsDto postsCommentsDto) {
    return ResponseEntity.ok(postsCommentsService.update(postId, commentId, postsCommentsDto));
  }

  @DeleteMapping("/comments/{commentId}") //댓글 삭제
  public ResponseEntity delete(@PathVariable Long commentId) {
    return ResponseEntity.ok(postsCommentsService.delete(commentId));
  }


}
