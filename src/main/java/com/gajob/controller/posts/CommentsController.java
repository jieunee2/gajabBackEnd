package com.gajob.controller.posts;

import com.gajob.dto.posts.CommentsDto;
import com.gajob.dto.posts.CommentsResponseDto;
import com.gajob.service.posts.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class CommentsController {

  private final CommentsService commentsService;

  @PostMapping("/comments/{id}") //댓글 저장
  public ResponseEntity<CommentsResponseDto> save(@PathVariable Long id,
      @RequestBody CommentsDto commentsDto) {
    return ResponseEntity.ok(commentsService.save(id, commentsDto));
  }

  @PutMapping({"/posts/{postId}/comments/{commentsId}"}) //댓글 수정
  public ResponseEntity update(@PathVariable("postId") Long postId,
      @PathVariable("commentsId") Long commentsId,
      @RequestBody CommentsDto commentsDto) {
    return ResponseEntity.ok(commentsService.update(postId, commentsId, commentsDto));
  }

  @DeleteMapping("/comments/{id}") //댓글 삭제
  public ResponseEntity delete(@PathVariable Long id) {
    return ResponseEntity.ok(commentsService.delete(id));
  }

}
