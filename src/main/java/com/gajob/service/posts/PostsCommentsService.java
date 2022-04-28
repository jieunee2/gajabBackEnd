package com.gajob.service.posts;

import com.gajob.dto.posts.PostsCommentsDto;
import com.gajob.dto.posts.PostsCommentsResponseDto;
import java.util.List;

public interface PostsCommentsService {

  PostsCommentsResponseDto save(Long postId, PostsCommentsDto postsCommentsDto); //댓글 저장

  List<PostsCommentsResponseDto> getPostsComments(Long postId); //게시물 별로 댓글 조회

  PostsCommentsResponseDto update(Long postId, Long commentId,
      PostsCommentsDto postsCommentsDto); //댓글 수정

  String delete(Long commentId); //댓글 삭제

}