package com.gajob.service.posts;

import com.gajob.dto.posts.PostsDto;
import com.gajob.dto.posts.PostsLikesResponseDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.posts.PostsResponseDto;
import com.gajob.entity.posts.Posts;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  public PostsReadDto getPosts(Long postId) {
    postsRepository.updateView(postId);

    Posts posts = postsRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    PostsReadDto postsReadDto = new PostsReadDto(posts);

    // 게시물 조회시 Posts 테이블이 likes 컬럼 업데이트 및 유저의 게시물 좋아요 상태 변경
    posts.likeUpdate(postsReadDto.getLikes());
    postsReadDto.setLikeStatus(isLikeStatus(posts));

    return postsReadDto;
  }

  @Transactional
  public List<PostsReadDto> getAllPosts() {
    List<Posts> posts = postsRepository.findAll();

    ArrayList<PostsReadDto> postsReadDtos = new ArrayList<PostsReadDto>();

    // 게시물 조회시 Posts 테이블이 likes 컬럼 업데이트 및 유저의 게시물 좋아요 상태 변경
    for (Posts postList : posts) {
      PostsReadDto postsReadDto = new PostsReadDto(postList);
      postList.likeUpdate(postsReadDto.getLikes());
      postsReadDto.setLikeStatus(isLikeStatus(postList));

      // 변경된 데이터들을 postsReadDtos에 저장
      postsReadDtos.add(postsReadDto);
    }

    return postsReadDtos;
  }

  // 좋아요 여부 확인
  @Transactional
  public boolean isLikeStatus(Posts posts) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    List<PostsLikesResponseDto> likesList = posts.getLikeList().stream()
        .map(PostsLikesResponseDto::new).collect(Collectors.toList());

    for (PostsLikesResponseDto postsLikesResponseDto : likesList) {
      // PostsLikesResponseDto 내에 현재 로그인 한 유저의 닉네임이 포함되어 있으면 likeStatus의 값을 true로 변환
      if (postsLikesResponseDto.getNickname()
          .equals(user.getNickname())) {
        PostsReadDto postsReadDto = new PostsReadDto(posts);
        postsReadDto.setLikeStatus(true);
        return true;
      }
    }
    return false;
  }


  // 게시물 수정
  @Transactional
  public PostsReadDto update(Long postId, PostsDto postsDto) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Posts posts = postsRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    // 현재 로그인한 유저와 게시물 작성자의 이메일이 일치하지 않을 경우, 에러 발생
    if (!(posts.getUser().getEmail().equals(user.getEmail()))) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    posts.update(postsDto.getTitle(), postsDto.getContent(), postsDto.getPostCategory(),
        postsDto.getJobCategory());

    PostsReadDto postsReadDto = new PostsReadDto(posts);

    return postsReadDto;
  }

  // 게시물 삭제
  @Transactional
  public String delete(Long postId) {
    User user = userRepository.findOneWithAuthoritiesByEmail(
        SecurityUtil.getCurrentUsername().get()).get();

    Posts posts = postsRepository.findById(postId)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

    // 현재 로그인한 유저와 게시물 작성자의 이메일이 일치하지 않을 경우, 에러 발생
    if (!(posts.getUser().getEmail().equals(user.getEmail()))) {
      throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
    }

    postsRepository.delete(posts);

    return "posts-delete";
  }

}
