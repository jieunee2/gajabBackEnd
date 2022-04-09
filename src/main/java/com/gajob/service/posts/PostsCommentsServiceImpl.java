package com.gajob.service.posts;

import com.gajob.dto.posts.CommentsDto;
import com.gajob.dto.posts.CommentsResponseDto;
import com.gajob.entity.posts.PostsComments;
import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.repository.posts.PostsCommentsRepository;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsCommentsServiceImpl implements PostsCommentsService {

  private final UserRepository userRepository;
  private final PostsRepository postsRepository;
  private final PostsCommentsRepository postsCommentsRepository;

  // 사용자가 등록한 댓글을 DB에 저장
  @Transactional
  public CommentsResponseDto save(Long id, CommentsDto commentsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

    return new CommentsResponseDto(postsCommentsRepository.save(commentsDto.toEntity(user, posts)));
  }

  // 댓글 수정
  @Transactional
  public CommentsResponseDto update(Long postId, Long commentsId, CommentsDto commentsDto) {
    Posts posts = postsRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다." + postId));

    PostsComments postsComments = postsCommentsRepository.findById(commentsId)
        .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + commentsId));

    postsComments.update(commentsDto.getComment());

    CommentsResponseDto commentsResponseDto = new CommentsResponseDto(postsComments);

    return commentsResponseDto;
  }

  // 댓글 삭제
  @Transactional
  public String delete(Long id) {
    PostsComments postsComments = postsCommentsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
    postsCommentsRepository.delete(postsComments);

    return "comments-delete";
  }


}
