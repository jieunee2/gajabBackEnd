package com.gajob.dto.posts;

import com.gajob.entity.posts.Posts;
import com.gajob.enumtype.PostCategory;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostsResponseDto {

  private Long id;
  private String title;
  private String content;
  private PostCategory postCategory;
  private String writer;
  private int view;
  private LocalDateTime createdDate, modifiedDate;

  public PostsResponseDto(Posts posts) {
    this.id = posts.getId();
    this.title = posts.getTitle();
    this.content = posts.getContent();
    this.postCategory = posts.getPostCategory();
    this.writer = posts.getUser().getNickname();
    this.view = posts.getView();
    this.createdDate = posts.getCreatedDate();
    this.modifiedDate = posts.getModifiedDate();
  }

}
