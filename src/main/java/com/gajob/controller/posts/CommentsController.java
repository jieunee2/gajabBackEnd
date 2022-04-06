package com.gajob.controller.posts;

import com.gajob.dto.posts.CommentsDto;
import com.gajob.dto.posts.CommentsResponseDto;
import com.gajob.service.posts.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentsController {

  private final CommentsService commentsService;

  @PostMapping("/comments/{id}") // 댓글 저장
  public ResponseEntity<CommentsResponseDto> save(@PathVariable Long id,
      @RequestBody CommentsDto commentsDto) {
    return ResponseEntity.ok(commentsService.save(id, commentsDto));
  }


}
