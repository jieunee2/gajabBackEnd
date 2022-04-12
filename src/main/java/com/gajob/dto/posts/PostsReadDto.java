package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.enumtype.PostCategory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PostsReadDto {
  private Long id;
  private String title;
  private String content;
  private PostCategory postCategory;
  private String writer;
  private int view;
  private LocalDateTime createdDate, modifiedDate;
  //   List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
  private List<PostsCommentsResponseDto> comments;

  public PostsReadDto(Posts posts) {
    this.id = posts.getId();
    this.title = posts.getTitle();
    this.content = posts.getContent();
    this.postCategory = posts.getPostCategory();
    this.writer = posts.getUser().getNickname();
    this.view = posts.getView();
    this.createdDate = posts.getCreatedDate();
    this.modifiedDate = posts.getModifiedDate();
    this.comments = posts.getPostsCommentsList().stream().map(PostsCommentsResponseDto::new)
        .collect(Collectors.toList());
  }

}
