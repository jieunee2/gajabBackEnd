package com.gajob.dto.posts;

import com.gajob.entity.posts.PostsComments;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class CommentsResponseDto {

  private Long id;
  private String comment;
  private String createdDate = LocalDateTime.now().format(
      DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String modifiedDate = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
  private String nickname;
  private Long postsId;

  public CommentsResponseDto(PostsComments postsComments) {
    this.id = postsComments.getId();
    this.comment = postsComments.getComment();
    this.createdDate = postsComments.getCreatedDate();
    this.modifiedDate = postsComments.getModifiedDate();
    this.nickname = postsComments.getUser().getNickname();
    this.postsId = postsComments.getPosts().getId();
  }
}
