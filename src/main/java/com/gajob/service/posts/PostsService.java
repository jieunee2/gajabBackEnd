package com.gajob.service.posts;

import com.gajob.dto.posts.PostsDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.posts.PostsResponseDto;
import com.gajob.entity.posts.Posts;
import java.util.List;

public interface PostsService {

  PostsResponseDto save(PostsDto postsDto); //게시물 저장

  PostsReadDto getPosts(Long postId); //게시물 낱개 조회 및 조회수 증가

  List<PostsReadDto> getAllPosts(); //게시물 전체 조회

  boolean isLikeStatus(Posts posts); // 게시물 좋아요 여부

  PostsReadDto update(Long postId, PostsDto postsDto); //게시물 수정

  String delete(Long postId); //게시물 삭제

}
