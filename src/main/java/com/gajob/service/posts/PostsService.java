package com.gajob.service.posts;

import com.gajob.dto.posts.PostsDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.posts.PostsResponseDto;
import java.util.List;

public interface PostsService {

  PostsResponseDto save(PostsDto postsDto); //게시물 저장

  PostsReadDto getPosts(Long id); //게시물 낱개 조회 및 조회수 증가

  List<PostsReadDto> getAllPosts(); //게시물 전체 조회

  PostsReadDto update(Long id, PostsDto postsDto); //게시물 수정

  String delete(Long id); //게시물 삭제

}
