package com.gajob.service.posts;

import com.gajob.dto.posts.PostsDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.posts.PostsResponseDto;
import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

  private final PostsRepository postsRepository;
  private final UserRepository userRepository;

  // 게시물을 DB에 저장
  @Transactional
  public PostsResponseDto save(PostsDto postsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    return new PostsResponseDto(postsRepository.save(postsDto.toEntity(user)));
  }

  // 게시물 낱개 조회 및 조회수 증가
  @Transactional
  public PostsReadDto updateView(Long id) {
    postsRepository.updateView(id);

    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다." + id));
    PostsReadDto postsReadDto = new PostsReadDto(posts);

    return postsReadDto;
  }

}
