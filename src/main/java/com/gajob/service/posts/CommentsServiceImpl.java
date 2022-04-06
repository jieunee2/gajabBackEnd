package com.gajob.service.posts;

import com.gajob.dto.posts.CommentsDto;
import com.gajob.dto.posts.CommentsResponseDto;
import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.repository.posts.CommentsRepository;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {

  private final UserRepository userRepository;
  private final PostsRepository postsRepository;
  private final CommentsRepository commentsRepository;

  // 사용자가 등록한 댓글을 DB에 저장
  @Transactional
  public CommentsResponseDto save(Long id, CommentsDto commentsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다." + id));

    return new CommentsResponseDto(commentsRepository.save(commentsDto.toEntity(user, posts)));
  }


}
