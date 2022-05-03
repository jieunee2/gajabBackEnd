package com.gajob.service.posts;

import com.gajob.dto.posts.PostsReadDto;
import com.gajob.entity.posts.Posts;
import com.gajob.repository.posts.PostsRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HotPostsServiceImpl implements HotPostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public List<PostsReadDto> getHotPosts() {
    List<Posts> posts = postsRepository.findAll();

    // posts 데이터들을 PostsReadDto에 저장
    for (int i = 0; i < posts.size(); i++) {
      PostsReadDto postsReadDto = new PostsReadDto(posts.get(i));
    }

    // 좋아요 수가 많은 3개의 Posts를 가져오기 위해서 likes 컬럼 기준으로 내림차순하고 Stream의 개수를 3개로 제한함
    return postsRepository.findAllDesc().stream()
        .map(PostsReadDto::new).limit(3)
        .collect(Collectors.toList());
  }
}