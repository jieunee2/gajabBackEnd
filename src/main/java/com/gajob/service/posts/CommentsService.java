package com.gajob.service.posts;

import com.gajob.dto.posts.CommentsDto;
import com.gajob.dto.posts.CommentsResponseDto;

public interface CommentsService {

  CommentsResponseDto save(Long id, CommentsDto commentsDto); //댓글 저장

  CommentsResponseDto update(Long postId, Long commentsId, CommentsDto commentsDto); //댓글 수정

  String delete(Long id); //댓글 삭제

}