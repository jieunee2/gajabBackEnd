package com.gajob.service.posts;

import com.gajob.dto.posts.PostsScrapResponseDto;
import com.gajob.entity.posts.Posts;
import com.gajob.entity.posts.PostsScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.posts.PostsScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsScrapServiceImpl implements PostsScrapService {

  private final UserRepository userRepository;
  private final PostsRepository postsRepository;
  private final PostsScrapRepository postsScrapRepository;

  // 스크랩 기능
  @Transactional
  public String scrap(Long postId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Posts posts = postsRepository.findById(postId).orElseThrow();

    if (isNotAlreadyScrap(user, posts)) {
      postsScrapRepository.save(new PostsScrap(posts, user));
      return "scrap-success";
    }
    // 이미 스크랩 한 게시물일 경우 스크랩 취소
    else if (postsScrapRepository.findByUserAndPosts(user, posts).get() != null) {
      postsScrapRepository.deleteByUserAndPosts(user, posts);
      return "cancel-scrap";
    }
    return "scrap-success";
  }

  // 사용자가 이미 스크랩 한 게시물인지 체크
  private boolean isNotAlreadyScrap(User user, Posts posts) {
    return postsScrapRepository.findByUserAndPosts(user, posts).isEmpty();
  }

  // 스크랩 목록 조회
  @Transactional(readOnly = true)
  public List<PostsScrapResponseDto> getScrap() {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    return postsScrapRepository.findByUser(user).stream().map(PostsScrapResponseDto::new).collect(
        Collectors.toList());
  }
}
