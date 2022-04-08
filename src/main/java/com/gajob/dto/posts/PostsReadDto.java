package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.enumtype.Category;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class PostsReadDto {
  private Long id;
  private String title;
  private String content;
  private Category category;
  private String writer;
  private int view;
  private LocalDateTime createdDate, modifiedDate;
  //   List 타입을 DTO 클래스로해서 엔티티간 무한 참조를 방지
  private List<CommentsResponseDto> comments;

  public PostsReadDto(Posts posts) {
    this.id = posts.getId();
    this.title = posts.getTitle();
    this.content = posts.getContent();
    this.category = posts.getCategory();
    this.writer = posts.getUser().getNickname();
    this.view = posts.getView();
    this.createdDate = posts.getCreatedDate();
    this.modifiedDate = posts.getModifiedDate();
    this.comments = posts.getCommentsList().stream().map(CommentsResponseDto::new)
        .collect(Collectors.toList());
  }

}