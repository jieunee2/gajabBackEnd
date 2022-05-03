package com.gajob.service.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.entity.posts.PostsLikes;
import com.gajob.entity.user.User;
import com.gajob.repository.posts.PostsLikesRepository;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsLikesServiceImpl implements PostsLikesService {

  private final PostsLikesRepository postsLikesRepository;
  private final UserRepository userRepository;
  private final PostsRepository postsRepository;

  // 좋아요 기능
  @Transactional
  public String likes(Long postId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Posts posts = postsRepository.findById(postId).orElseThrow();

    // 중복 좋아요 방지
    if (
        isNotAlreadyLike(user, posts)) {
      postsLikesRepository.save(new PostsLikes(posts, user));
      return "increase-likes";
    }
    // 이미 좋아요를 눌렀던 게시물일 경우, 다시 좋아요 기능을 요청할 경우 좋아요 해제
    else if (postsLikesRepository.findByUserAndPosts(user, posts).get() != null) {
      postsLikesRepository.deleteByUserAndPosts(user, posts);
      return "decrease-likes";
    }
    return "increase-likes";
  }

  // 사용자가 이미 좋아요 한 게시물인지 체크
  private boolean isNotAlreadyLike(User user, Posts posts) {
    return postsLikesRepository.findByUserAndPosts(user, posts).isEmpty();
  }

}
