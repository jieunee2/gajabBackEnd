package com.gajob.service.posts;

import com.gajob.dto.posts.PostsCommentsDto;
import com.gajob.dto.posts.PostsCommentsResponseDto;

public interface PostsCommentsService {

  PostsCommentsResponseDto save(Long postId, PostsCommentsDto postsCommentsDto); //댓글 저장

  PostsCommentsResponseDto update(Long postId, Long commentId,
      PostsCommentsDto postsCommentsDto); //댓글 수정

  String delete(Long commentId); //댓글 삭제

}