package com.gajob.service.posts;

import com.gajob.dto.posts.PostsDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.posts.PostsResponseDto;

public interface PostsService {

  PostsResponseDto save(PostsDto postsDto); //게시물 저장

  PostsReadDto updateView(Long id); //게시물 낱개 조회 및 조회수 증가
}
