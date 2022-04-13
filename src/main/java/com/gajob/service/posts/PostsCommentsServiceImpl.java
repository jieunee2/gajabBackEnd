package com.gajob.service.posts;

import com.gajob.dto.posts.PostsCommentsDto;
import com.gajob.dto.posts.PostsCommentsResponseDto;
import com.gajob.entity.posts.PostsComments;
import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
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
  public PostsCommentsResponseDto save(Long postId, PostsCommentsDto postsCommentsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();
    Posts posts = postsRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    return new PostsCommentsResponseDto(postsCommentsRepository.save(
        postsCommentsDto.toEntity(user, posts)));
  }

  // 댓글 수정
  @Transactional
  public PostsCommentsResponseDto update(Long postId, Long commentId,
      PostsCommentsDto postsCommentsDto) {
    Posts posts = postsRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    PostsComments postsComments = postsCommentsRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_ID_NOT_EXIST));

    postsComments.update(postsCommentsDto.getComment());

    PostsCommentsResponseDto postsCommentsResponseDto = new PostsCommentsResponseDto(postsComments);

    return postsCommentsResponseDto;
  }

  // 댓글 삭제
  @Transactional
  public String delete(Long commentId) {
    PostsComments postsComments = postsCommentsRepository.findById(commentId)
        .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_ID_NOT_EXIST));
    postsCommentsRepository.delete(postsComments);

    return "comments-delete";
  }


}
