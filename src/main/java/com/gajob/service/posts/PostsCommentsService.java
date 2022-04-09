package com.gajob.service.posts;

import com.gajob.dto.posts.PostsCommentsDto;
import com.gajob.dto.posts.PostsCommentsResponseDto;

public interface PostsCommentsService {

  PostsCommentsResponseDto save(Long id, PostsCommentsDto postsCommentsDto); //댓글 저장

  PostsCommentsResponseDto update(Long postId, Long commentsId, PostsCommentsDto postsCommentsDto); //댓글 수정

  String delete(Long id); //댓글 삭제


}