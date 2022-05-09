package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.enumtype.JobCategory;
import com.gajob.enumtype.PostCategory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PostsReadDto {

  private Long id;
  private String title;
  private String content;
  private PostCategory postCategory;
  private JobCategory jobCategory;
  private String writer;
  private int view;
  private String createdDate, modifiedDate;
  //   List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
  private List<PostsCommentsResponseDto> comments;
  private int commentsCnt; //댓글 개수
  private int likes; //좋아요 수
  private List<PostsLikesResponseDto> likesList; //좋아요를 누른 유저들 목록
  private int scrap; //스크랩 수

  public PostsReadDto(Posts posts) {
    this.id = posts.getId();
    this.title = posts.getTitle();
    this.content = posts.getContent();
    this.postCategory = posts.getPostCategory();
    this.jobCategory = posts.getJobCategory();
    this.writer = posts.getUser().getNickname();
    this.view = posts.getView();
    this.createdDate = posts.getCreatedDate();
    this.modifiedDate = posts.getModifiedDate();
    this.comments = posts.getPostsCommentsList().stream().map(PostsCommentsResponseDto::new)
        .collect(Collectors.toList());
    this.commentsCnt = comments.size();
    this.likes = posts.getLikeList().size();
    this.likesList = posts.getLikeList().stream().map(PostsLikesResponseDto::new).collect(
        Collectors.toList());
    this.scrap = posts.getPostsScrapList().size();
  }

}
